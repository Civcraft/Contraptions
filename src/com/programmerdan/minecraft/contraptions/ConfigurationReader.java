package com.programmerdan.minecraft.contraptions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationReader {
	public static boolean readConfig() {

		// Note: savedefaultconfig only writes data if config.yml doesn't exist.
		Contraptions.instance().saveDefaultConfig();
		Contraptions.instance().reloadConfig();
		FileConfiguration conf = Contraptions.instance().getConfig();

		if (ContraptionsConfiguration.check_version(conf.getDouble("configuration_file_version"))) {
			ContraptionsConfiguration config = new ContraptionsConfiguration();

			config.setDebug( conf.getBoolean("debug", false ) );
		
			return true;
		}
		return false;
	}
}
