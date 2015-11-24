package com.programmerdan.minecraft.contraptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Bukkit style configuration section as a serializable object.
 * 
 * @author ProgrammerDan
 *
 */
class DaoConfig implements ConfigurationSerializable{
	private boolean active;
	private String host;
	private String username;
	private transient String password;
	private String database;
	private String schema;
	private int port;
	private Driver driver;

	public DaoConfig() {
	}
	
	public DaoConfig(Map<String, Object> serial) {
		this.active = serial.containsKey("active") ? (Boolean) serial.get("active") : false;
		if (this.active) {
			this.host = serial.containsKey("host") ? (String) serial.get("host") : null;
			this.username = serial.containsKey("username") ? (String) serial.get("username") : null;
			this.password = serial.containsKey("password") ? (String) serial.get("password") : null;
			this.database = serial.containsKey("database") ? (String) serial.get("database") : null;
			this.schema = serial.containsKey("schema") ? (String) serial.get("schema") : null;
			this.port = serial.containsKey("port") ? (Integer) serial.get("port") : 0;
			this.driver = serial.containsKey("driver") ? Driver.valueOf((String) serial.get("driver")) : null;
		}
	}
	
	public static DaoConfig deserialize(Map<String, Object> serial) {
		return new DaoConfig(serial);
	}
	
	public static DaoConfig valueOf(Map<String, Object> serial) {
		return DaoConfig.deserialize(serial);
	}
	
	public Map<String, Object> serialize() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("active", this.active);
		if (this.active) {
			result.put("host", this.host);
			result.put("username", this.username);
			result.put("password", this.password);
			result.put("database", this.database);
			result.put("schema", this.schema);
			result.put("port", this.port);
			result.put("driver", this.driver.toString());
		}
		return result;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	public String getHost() {
		return this.host;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	public String getDatabase() {
		return this.database;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getSchema() {
		return this.schema;
	}

	public void setPort(int port) {
		this.port = port;
	}
	public int getPort() {
		return this.port;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Driver getDriver() {
		return this.driver;
	}
}