package com.programmerdan.minecraft.contraptions.rate;

import com.programmerdan.minecraft.contraptions.time.TimeMeasure;
import com.programmerdan.minecraft.contraptions.util.AdvItemStack;

/**
 * Represents a movable quantity that has an item stack (either real or
 *   imaginary) and a time measure, indicating how many of the item
 *   can be moved within the time measure.
 *   
 * @author ProgrammerDan
 * @since 1.0.0 September 2015
 * @param <T extends TimeMeasure<?>> The actual time measure of this rate.
 */
public abstract class PipedRate<T extends TimeMeasure<?>> {
	
	public abstract AdvItemStack getResource();
	
	public abstract T getTime();
	
	/**
	 * Remap this rate into a new time measure. Does not change <i>this</i> but returns a 
	 *   new PipedRate.
	 *   
	 * @param clazz
	 * @param measure
	 * @return
	 */
	public abstract <Z extends TimeMeasure<?>, R extends PipedRate<Z>> R reRate(Class<R> clazz, Z measure);
	
	/**
	 * Gets this resource count as if over a new time; doesn't change <i>this</i> but returns
	 *   an AdvItemStack remapped either larger or smaller based on newMeasure. The new measure
	 *   must be of the same type as the old measure.
	 *   
	 * @param newMeasure
	 * @return
	 */
	public abstract AdvItemStack getResourceOverNewTime(T newMeasure);
}
