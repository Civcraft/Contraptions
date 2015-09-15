package com.programmerdan.minecraft.contraptions.time.unit;

import com.programmerdan.minecraft.contraptions.time.TimeUnit;

public final class Millisecond extends TimeUnit {

	private static final double[] toNanosecond = new double[]{1000.0, 1.0};
	private static final double[] toMillisecond = new double[]{1.0, 1.0};
	private static final double[] toMicrosecond = new double[]{1.0, 10.0};
	private static final double[] toSecond = new double[]{1.0, 1000.0};
	private static final double[] toTick = new double[]{20.0, 1000.0};
	private static final double[] toMinute = new double[]{0.001, 60.0};
	private static final double[] toHour = new double[]{0.001, 3600.0};
	private static final double[] toDay = new double[]{0.001, 86400.0};
	private static final double[] toYear = new double[]{0.001, 31557600.0};
	private static final double[] unknown = new double[]{0.0, 0.0};
	
	@Override
	public UnitType getUnit() {
		return TimeUnit.UnitType.Millisecond;
	}
	
	@Override
	public double[] getRatio(UnitType convert) {
		switch(convert) {
			case Nanosecond: return toNanosecond;
			case Millisecond: return toMillisecond;
			case Microsecond: return toMicrosecond;
			case Second: return toSecond;
			case Tick: return toTick;
			case Minute: return toMinute;
			case Hour: return toHour;
			case Day: return toDay;
			case Year: return toYear;
			default: return unknown; // inconvertible.
		}
	}
	
	public static final Millisecond instance = new Millisecond();
	
	private Millisecond(){}	

	@SuppressWarnings("unchecked")
	public static Millisecond instance() {
		return instance;
	}

}
