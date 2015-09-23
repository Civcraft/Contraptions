package com.programmerdan.minecraft.contraptions.time;

/**
 *  Represents a measureless unit of time. Enumerated here to illustrate all the
 *  types of time supported by Contraptions (potentially).
 *  
 *  @Author ProgrammerDan
 *  @Since 1.0.0 September 2015
 */
public abstract class TimeUnit {
	
	/**
	 * These should all be singletons. 
	 * 
	 * @return an instance of TimeUnit.
	 */
	public static <U extends TimeUnit> U instance() {
		throw new IllegalArgumentException();
	};
	
	public abstract UnitType getType();
	/**
	 * Returns a double array with idx 0 as numerator and idx 1 as denominator,
	 * where numerator is convert units, and denominator is this units.
	 * Example:
	 * 
	 * Millisecond.getRatio(Nanosecond) = [1000 (Nanoseconds), 1 (Milliseconds)]
	 * 
	 * however the values will actually be unitless doubles.
	 * 
	 * Switch that around however you want in your actual code.
	 * 
	 * @param convert the UnitType to return conversion factors for.
	 * @return array with numerator as convert Units and denominator as "this" Units.
	 */
	public abstract double[] getRatio(UnitType convert);
	
	public enum UnitType {
		Nanosecond,
		Millisecond,
		Microsecond,
		Tick,
		Second,
		Minute,
		Hour,
		Day,
		Week,
		Year,
		Decade
	}
}
