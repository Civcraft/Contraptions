package com.programmerdan.minecraft.contraptions.time.unit;

import com.programmerdan.minecraft.contraptions.time.TimeUnit;

public final class Minute extends TimeUnit {
	private static final double[] toNanosecond = new double[]{60000000.0, 1.0};
	private static final double[] toMillisecond = new double[]{60000.0, 1.0};
	private static final double[] toMicrosecond = new double[]{6000.0, 1.0};
	private static final double[] toSecond = new double[]{60.0, 1.0};
	private static final double[] toTick = new double[]{1200.0, 1.0};
	private static final double[] toMinute = new double[]{1.0, 1.0};
	private static final double[] toHour = new double[]{1.0, 60.0};
	private static final double[] toDay = new double[]{1.0, 1440.0};
	private static final double[] toYear = new double[]{1.0, 525960.0};
	private static final double[] unknown = new double[]{0.0, 0.0};
	
	@Override
	public UnitType getUnit() {
		return TimeUnit.UnitType.Minute;
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
	
	public static final Minute instance = new Minute();
	
	private Minute(){}	

	@SuppressWarnings("unchecked")
	public static Minute instance() {
		return instance;
	}

}
