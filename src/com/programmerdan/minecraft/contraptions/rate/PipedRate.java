package com.programmerdan.minecraft.contraptions.rate;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.programmerdan.minecraft.contraptions.time.TimeMeasure;
import com.programmerdan.minecraft.contraptions.util.AdvItemStack;

/**
 * Represents a movable quantity that has an item stack (either real or
 *   imaginary) and a time measure, indicating how many of the item
 *   can be moved within the time measure.
 *   
 * @author ProgrammerDan
 * @since 1.0.0 September 2015
 */
public class PipedRate implements ConfigurationSerializable {

	/**
	 * Constructs a new PipedRate.
	 * 
	 * @param resource The item stack resource.
	 * @param time the TimeMeasure.
	 */
	public PipedRate(AdvItemStack resource, TimeMeasure time) {
		this.resource = resource;
		this.time = time;
	}
	
	public PipedRate(Map<String, Object> serial) {
		this.resource = (AdvItemStack) serial.get("resource");
		this.time = (TimeMeasure) serial.get("time");
	}
	
	private AdvItemStack resource;
	private TimeMeasure time;
	
	/**
	 * Gets the total resources piped in TimeMeasure time as represented by this rate.
	 * 
	 * @return An Advanced Item Stack containing an appropriate number of items 
	 */
	public AdvItemStack getResource() {
		return resource;
	}
	
	/**
	 * Gets the length of time over which the resource flow
	 * 
	 * @return the TimeMeasure length of time
	 */
	public TimeMeasure getTime() {
		return time;
	}
	
	/**
	 * Gets this resource count as if over a new time; doesn't change <i>this</i> but returns
	 *   an AdvItemStack remapped either larger or smaller based on newMeasure. The new measure
	 *   must be of the same type as the old measure.
	 *   
	 * @param newTime the new time length
	 * @return the new ItemStack resized to fit the time, based on underlying rate.
	 */
	public AdvItemStack getResourceOverTime(TimeMeasure newTime) {
		double ratio = (time.getLength() == 0 || newTime.getLength() == 0) ? 0.0 : newTime.getLength() / time.getLength();
		
		AdvItemStack advNew = resource.clone();
		advNew.setSize(ratio * resource.getSize());
		return advNew;
	}
	
	/**
	 * Remaps this rate's ratio onto a new time.
	 * @param newTime the new TimeMeasure to map towards
	 * @return a new PipedRate having the same ratio as the original, but against the new time.
	 */
	public PipedRate reRate(TimeMeasure newTime) {
		AdvItemStack newStack = this.getResourceOverTime(newTime);
		
		return new PipedRate(newStack, newTime);
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		result.put("resource", this.getResource());
		result.put("time", this.getTime());
		
		return result;
	}
	
	public static PipedRate deserialize(Map<String, Object> serial) {
		return new PipedRate(serial);
	}
	
	public static PipedRate valueOf(Map<String, Object> serial) {
		return PipedRate.deserialize(serial);
	}
}
