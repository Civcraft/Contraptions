package com.programmerdan.minecraft.contraptions.time.unit;

import com.programmerdan.minecraft.contraptions.time.TimeUnit;

public class Tick extends TimeUnit {
	private static final double[] toNanosecond = new double[]{1000000.0, 20.0};
	private static final double[] toMillisecond = new double[]{1000.0, 20.0};
	private static final double[] toMicrosecond = new double[]{100.0, 20.0};
	private static final double[] toSecond = new double[]{1.0, 20.0};
	private static final double[] toTick = new double[]{1.0, 1.0};
	private static final double[] toMinute = new double[]{1.0, 1200.0};
	private static final double[] toHour = new double[]{1.0, 72000.0};
	private static final double[] toDay = new double[]{1.0, 1728000.0};
	private static final double[] toYear = new double[]{1.0, 631152000.0};
	private static final double[] unknown = new double[]{0.0, 0.0};
	
	@Override
	public UnitType getUnit() {
		return UnitType.Tick;
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

	public static final Tick instance = new Tick();
	
	private Tick(){}	

	@SuppressWarnings("unchecked")
	public static Tick instance() {
		return instance;
	}
}