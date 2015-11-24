package com.programmerdan.minecraft.contraptions;


public class ContraptionsConfiguration {
	

	public ContraptionsConfiguration() {
		debug = false;
		setMonitor(new MonitorBase());
		setDaoConfig(new DaoConfig());
		setCacheConfig(new CacheConfig());
	}
	
	public static final double CONFIG_VERSION = 1.0d;
	
	private boolean debug;
	private String gadgetTemplateFolder;

	private MonitorBase monitor;
	private DaoConfig dao;
	private CacheConfig cache;

	public MonitorBase getMonitor() {
		return monitor;
	}
	public void setMonitor(MonitorBase monitor) {
		this.monitor = monitor;
	}

	public DaoConfig getDaoConfig() {
		return dao;
	}
	public void setDaoConfig(DaoConfig dao) {
		this.dao = dao;
	}

	public CacheConfig getCacheConfig() {
		return cache;
	}
	public void setCacheConfig(CacheConfig cache) {
		this.cache = cache;
	}

	public static boolean check_version(final double version) {
		return CONFIG_VERSION == version;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public boolean isDebug() {
		return this.debug;
	}

	public void setGadgetTemplateFolder(String gadgetTemplateFolder) {
		this.gadgetTemplateFolder = gadgetTemplateFolder;
	}
	public String getGadgetTemplateFolder() {
		return this.gadgetTemplateFolder;
	}
	
	class CacheConfig {
		private long gadgetCacheSize;
		private long contraptionStateCacheSize;
		private EvictionStrategy evictionStrategy;

		public void setGadgetCacheSize(long gadgetCacheSize) {
			this.gadgetCacheSize = gadgetCacheSize;
		}
		public long getGadgetCacheSize() {
			return this.gadgetCacheSize;
		}
		public void setContraptionStateCacheSize(long contraptionStateCacheSize) {
			this.contraptionStateCacheSize = contraptionStateCacheSize;
		}
		public long getContraptionStateCacheSize() {
			return this.contraptionStateCacheSize;
		}
		public void setEvictionStrategy(EvictionStrategy evictionStrategy) {
			this.evictionStrategy = evictionStrategy;
		}
		public EvictionStrategy getEvictionStrategy() {
			return this.evictionStrategy;
		}
	}	

	class MonitorBase {
		private int count;
		private MonitorStrategy strategy;
		private long updateFrequency;
		private boolean runUnloaded;
		private boolean rollingUpdates;
		
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public MonitorStrategy getStrategy() {
			return strategy;
		}
		public void setStrategy(MonitorStrategy strategy) {
			this.strategy = strategy;
		}
		public long getUpdateFrequency() {
			return updateFrequency;
		}
		public void setUpdateFrequency(long updateFrequency) {
			this.updateFrequency = updateFrequency;
		}
		public boolean isRunUnloaded() {
			return runUnloaded;
		}
		public void setRunUnloaded(boolean runUnloaded) {
			this.runUnloaded = runUnloaded;
		}
		public boolean isRollingUpdates() {
			return rollingUpdates;
		}
		public void setRollingUpdates(boolean rollingUpdates) {
			this.rollingUpdates = rollingUpdates;
		}
	}
}
