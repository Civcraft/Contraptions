package com.programmerdan.minecraft.contraptions.time;

import com.programmerdan.minecraft.contraptions.time.unit.Tick;

public class TickMeasure implements TimeMeasure<Tick> {
	public static final TickMeasure ONE = new TickMeasure(1.0d);
	public static final TickMeasure TWENTY = new TickMeasure(20.0d);
	
	private double length;
	
	public TickMeasure(double length) {
		this.length = length;
	}
	
	@Override
	public Tick getUnit() {
		return Tick.instance();
	}
	
	@Override
	public double getLength() {
		return length;
	}

	@Override
	public int compareTo(TimeMeasure<Tick> tm) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <U extends TimeUnit> TimeMeasure<U> convertTo(Class<U> clazz) {
		
		return null;
	}

}
