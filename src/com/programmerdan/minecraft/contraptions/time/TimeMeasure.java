package com.programmerdan.minecraft.contraptions.time;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

/**
 * Represents a measure of a unit of time. This is an abstract concept, made concrete
 * by the utilizing context. Will typically represent either seconds, milliseconds, or ticks.
 * In configuration all rates should be tuned based on a unified understanding of the units.
 * 
 * @author ProgrammerDan
 * @since 1.0.0 September, 2015
 */
public class TimeMeasure implements Comparable<TimeMeasure>, ConfigurationSerializable{
	
	public static final TimeMeasure ONE = new TimeMeasure(1.0d);
	public static final TimeMeasure TWENTY = new TimeMeasure(20.0d);
	
	private double length;
	
	public TimeMeasure(double length) {
		this.length = length;
	}
	public TimeMeasure(Map<String, Object> serial) {
		this.length = (Double) serial.get("length");
	}
	public double getLength() {
		return this.length;
	}
	public long getWholeLength() {
		return Math.round(this.length);
	}
	public int compareTo(TimeMeasure tm) {
		return (tm.getLength() < this.length) ? 1 : (tm.getLength() > this.length) ? -1 : 0;
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		
		result.put("length", this.getLength());
		
		return result;
	}
	
	public static TimeMeasure deserialize(Map<String, Object> serial) {
		return new TimeMeasure(serial);
	}
	
	public static TimeMeasure valueOf(Map<String, Object> serial) {
		return TimeMeasure.deserialize(serial);
	}
}
