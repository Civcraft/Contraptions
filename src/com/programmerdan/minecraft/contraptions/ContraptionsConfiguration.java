package com.programmerdan.minecraft.contraptions;

public class ContraptionsConfiguration {
	
	public ContraptionsConfiguration() {
	}
	
	private boolean debug = false;
	public static final double CONFIG_VERSION = 1.0d;
	
	private MonitorBase monitors = new MonitorBase();
	private DaoConfig dao = new DaoConfig();
	
	public static boolean check_version(final double version) {
		return CONFIG_VERSION == version;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public boolean isDebug() {
		return this.debug;
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
