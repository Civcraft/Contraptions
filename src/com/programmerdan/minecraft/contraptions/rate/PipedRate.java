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
 */
public abstract class PipedRate {
	
	public abstract AdvItemStack getResource();
	
	public abstract TimeMeasure getTime();
	
	/**
	 * Gets this resource count as if over a new time; doesn't change <i>this</i> but returns
	 *   an AdvItemStack remapped either larger or smaller based on newMeasure. The new measure
	 *   must be of the same type as the old measure.
	 *   
	 * @param newTime the new time length
	 * @return the new ItemStack resized to fit the time, based on underlying rate.
	 */
	public abstract AdvItemStack getResourceOverTime(TimeMeasure newTime);
	
	/**
	 * Remaps this rate's ratio onto a new time.
	 * @param newTime the new TimeMeasure to map towards
	 * @return a new PipedRate having the same ratio as the original, but against the new time.
	 */
	public abstract PipedRate reRate(TimeMeasure newTime);
}
