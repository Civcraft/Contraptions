package com.programmerdan.minecraft.contraptions.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Container for metadata that is associated with the items.
 * 
 * For example, which player introduced the items to the contraption,
 * the time it was placed, special naming overrides, etc.
 * 
 * @author ProgrammerDan
 * @since 1.0.0 October 2015
 */
public class AdvancedMeta implements Cloneable, ConfigurationSerializable {
	private String playerUUID;
	private long creationTime;
	private String nameOverride;
	private boolean retrievable;
	
	public AdvancedMeta() {
		playerUUID = null;
		creationTime = System.currentTimeMillis();
		nameOverride = null;
		retrievable = true;
	}
	
	public AdvancedMeta(Map<String, Object> serial) {
		AdvancedMeta a = AdvancedMeta.deserialize(serial);
		this.setCreationTime(a.getCreationTime());
		this.setPlayerUUID(a.getPlayerUUID());
		this.setRetrievable(a.getRetrievable());
		this.setNameOverride(a.getNameOverride());
		a = null;
	}
	
	/**
	 * Internal method, applies this meta to the underlying ItemStack.
	 * 
	 * @return an ItemStack with this meta applied.
	 */
	protected ItemStack apply(ItemStack is) {
		if (getNameOverride() != null) {
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(getNameOverride());
			is.setItemMeta(im);
		}
		return is;
	}
	
	public String getPlayerUUID() {
		return playerUUID;
	}
	
	public void setPlayerUUID(String playerUUID) {
		this.playerUUID = playerUUID;
	}
	
	public long getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}
	
	public String getNameOverride() {
		return nameOverride;
	}
	
	public void setNameOverride(String nameOverride) {
		this.nameOverride = nameOverride;
	}
	
	public boolean getRetrievable() {
		return retrievable;
	}
	
	public void setRetrievable(boolean retrievable) {
		this.retrievable = retrievable;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		if (getPlayerUUID() != null) {
			result.put("playerUUID", getPlayerUUID());
		}
		
		if (getNameOverride() != null) {
			result.put("nameOverride", getNameOverride());
		}
		
		result.put("creationTime", getCreationTime());
		
		result.put("retrievable", getRetrievable());
		
		return result;
	}
	
	public static AdvancedMeta deserialize(Map<String, Object> serial) {
		AdvancedMeta result = new AdvancedMeta();
		
		if (serial.containsKey("playerUUID")) {
			result.setPlayerUUID((String)serial.get("playerUUID"));
		}
		
		if (serial.containsKey("nameOverride")) {
			result.setNameOverride((String)serial.get("nameOverride"));
		}
		
		if (serial.containsKey("creationTime")) {
			result.setCreationTime( ( (Number) serial.get("creationTime")).longValue());
		}
		
		if (serial.containsKey("retrievable")) {
			result.setRetrievable( (Boolean) serial.get("retrievable"));
		}
		
		return result;
	}
	
	public static AdvancedMeta valueOf(Map<String, Object> serial) {
		return AdvancedMeta.deserialize(serial);
	}
	
	@Override
	public Object clone() {
		AdvancedMeta cl = new AdvancedMeta();
		
		cl.setCreationTime(this.getCreationTime());
		cl.setNameOverride(this.getNameOverride());
		cl.setPlayerUUID(this.getPlayerUUID());
		cl.setRetrievable(this.getRetrievable());
		
		return cl;
	}
}
