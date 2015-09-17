package com.programmerdan.minecraft.contraptions.rate;

import com.programmerdan.minecraft.contraptions.time.TimeMeasure;

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
	
	private T measure;
	
	public T getMeasure() {
		return measure;
	}
}
