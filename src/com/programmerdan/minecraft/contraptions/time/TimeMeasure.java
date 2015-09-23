package com.programmerdan.minecraft.contraptions.time;

/**
 * Represents a measure of a unit of time, indicating length of time within that unit.
 * Subclasses should offer length retrieval, comparison, and conversion facilities.
 * 
 * @author ProgrammerDan
 * @since 1.0.0 September, 2015
 */
public interface TimeMeasure<T extends TimeUnit> extends Comparable<TimeMeasure<T>>{
	
	public double getLength();
	public T getUnit();
	public int compareTo(TimeMeasure<T> tm);
	public <U extends TimeUnit> TimeMeasure<U> convertTo(Class<U> clazz, TimeUnit.UnitType unit);
}
