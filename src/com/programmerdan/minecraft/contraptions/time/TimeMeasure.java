package com.programmerdan.minecraft.contraptions.time;

/**
 * Represents a measure of a unit of time. This is an abstract concept, made concrete
 * by the utilizing context. Will typically represent either seconds, milliseconds, or ticks.
 * In configuration all rates should be tuned based on a unified understanding of the units.
 * 
 * @author ProgrammerDan
 * @since 1.0.0 September, 2015
 */
public class TimeMeasure implements Comparable<TimeMeasure>{
	
	public static final TimeMeasure ONE = new TimeMeasure(1.0d);
	public static final TimeMeasure TWENTY = new TimeMeasure(20.0d);
	
	private double length;
	
	public TimeMeasure(double length) {
		this.length = length;
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
}
