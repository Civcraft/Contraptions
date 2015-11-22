package com.programmerdan.minecraft.contraptions;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationReader {
	public static boolean readConfig() {
		Logger log = Contraptions.logger();

		// Note: savedefaultconfig only writes data if config.yml doesn't exist.
		Contraptions.instance().saveDefaultConfig();
		Contraptions.instance().reloadConfig();
		FileConfiguration conf = Contraptions.instance().getConfig();

		if (ContraptionsConfiguration.check_version(conf.getDouble("configuration_file_version"))) {
			try {
				ContraptionsConfiguration config = new ContraptionsConfiguration();
	
				config.setDebug( conf.getBoolean("debug", false ) );
				config.setGadgetTemplateFolder( conf.getString("gadget_template_folder") );
				
				// validate template folder
				if (!(new File(config.getGadgetTemplateFolder())).isDirectory()) {
					log.log(Level.SEVERE, "Gadget template folder is not a folder: {0}", config.getGadgetTemplateFolder());
					return false;
				}
				
				ConfigurationSection monitorConf = conf.getConfigurationSection("monitor");
				
				if (monitorConf == null) {
					log.log(Level.SEVERE, "Monitor section not found in config");
					return false;
				}
				
				ContraptionsConfiguration.MonitorBase monitor = config.getMonitor();
				
				monitor.setCount(monitorConf.getInt("count", 1));
				monitor.setUpdateFrequency(monitorConf.getLong("update_frequency", 10));
				monitor.setRollingUpdates(monitorConf.getBoolean("rolling_updates", true));
				monitor.setRunUnloaded(monitorConf.getBoolean("run_unloaded", true));
				monitor.setStrategy(MonitorStrategy.valueOf(monitorConf.getString("strategy", "random")));
				
				ConfigurationSection daoConf = conf.getConfigurationSection("dao");
				
				ConfigurationSection cacheConf = daoConf.getConfigurationSection("cache");
				
				if (cacheConf == null) {
					log.log(Level.SEVERE, "Cache section not found in config");
					return false;
				}
				
				ContraptionsConfiguration.CacheConfig cache = config.getCacheConfig();
				
				cache.setGadgetCacheSize(cacheConf.getLong("gadgets", 1000L));
				cache.setContraptionStateCacheSize(cacheConf.getLong("states", 100L));
				cache.setEvictionStrategy(EvictionStrategy.valueOf(cacheConf.getString("strategy", "lru")));
				
				ConfigurationSection databaseConf = daoConf.getConfigurationSection("database");
				
				ContraptionsConfiguration.DaoConfig dao = config.getDaoConfig();
				
				if (databaseConf == null) {
					log.log(Level.WARNING, "Database section not found in config, disabling");
					dao.setActive(false);
				} else {
					dao.setActive(databaseConf.getBoolean("active", false));
				}
				
				if (dao.isActive()) {
					// only load if active.
					dao.setHost(databaseConf.getString("host"));
					dao.setPort(databaseConf.getInt("port"));
					dao.setDriver(Driver.valueOf(databaseConf.getString("driver")));
					dao.setDatabase(databaseConf.getString("database"));
					dao.setSchema(databaseConf.getString("schema"));
					dao.setUsername(databaseConf.getString("username"));
					dao.setPassword(databaseConf.getString("password"));
				}
		
				return true;
			} catch (IllegalArgumentException iae) {
				log.log(Level.SEVERE, "Failed to load configuration!", iae);
			} catch (NullPointerException npe) {
				log.log(Level.SEVERE, "Null Pointer while loading config.", npe);
			}
		} else {
			log.log(Level.SEVERE, "Failed to load configuration, expecting file version {0} but found {1}", 
					new Object[]{ContraptionsConfiguration.CONFIG_VERSION, 
					conf.getDouble("configuration_file_version")} );
		}
		return false;
	}
}
