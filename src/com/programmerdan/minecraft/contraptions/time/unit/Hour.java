package com.programmerdan.minecraft.contraptions.time.unit;

import com.programmerdan.minecraft.contraptions.time.TimeUnit;

public final class Hour extends TimeUnit {
	private static final double[] toNanosecond = new double[]{36000000.0, 0.01};
	private static final double[] toMillisecond = new double[]{36000.0, 0.01};
	private static final double[] toMicrosecond = new double[]{3600.0, 0.01};
	private static final double[] toSecond = new double[]{36.0, 0.01};
	private static final double[] toTick = new double[]{720.0, 0.01};
	private static final double[] toMinute = new double[]{60.0, 1.0};
	private static final double[] toHour = new double[]{1.0, 1.0};
	private static final double[] toDay = new double[]{1.0, 24.0};
	private static final double[] toYear = new double[]{1.0, 8766.0};
	private static final double[] unknown = new double[]{0.0, 0.0};
	
	@Override
	public UnitType getUnit() {
		return TimeUnit.UnitType.Hour;
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
	
	public static final Hour instance = new Hour();
	
	private Hour(){}	

	@SuppressWarnings("unchecked")
	public static Hour instance() {
		return instance;
	}

}
