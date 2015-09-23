package com.programmerdan.minecraft.contraptions.rate;

import java.lang.reflect.InvocationTargetException;

import com.programmerdan.minecraft.contraptions.time.TickMeasure;
import com.programmerdan.minecraft.contraptions.time.TimeMeasure;
import com.programmerdan.minecraft.contraptions.time.TimeUnit;
import com.programmerdan.minecraft.contraptions.util.AdvItemStack;

public class RatePerTick extends PipedRate<TickMeasure> {

	private AdvItemStack resource;
	private TickMeasure overTime;
	
	@Override
	public AdvItemStack getResource() {
		return resource;
	}

	@Override
	public TickMeasure getTime() {
		return overTime;
	}

	@Override
	public AdvItemStack getResourceOverNewTime(TickMeasure newMeasure) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Z extends TimeMeasure<?>, R extends PipedRate<Z>> R reRate(
			Class<R> clazz, Z measure) {
		// Convert this to a new unit.
		Z newM = (Z) this.overTime.convertTo(measure.getUnit().getType().getClass());
		
		/**TimeUnit origin = this.overTime.getUnit();
		double[] convert = origin.getRatio(measure.getUnit().getType());
		double newUnitLength = (this.overTime.getLength() * convert[0]) / convert[1];**/
		
		// now determine ratio between current rate at this unit density and new unit rate.
		double ratio = measure.getLength() / newM.getLength();//newUnitLength;
		
		AdvItemStack adjResource = this.resource.clone();
		adjResource.setSize(ratio * this.resource.getSize());
		
		R ret;
		try {
			ret = (R) clazz.getConstructors()[0].newInstance(adjResource, measure);
			
			return ret;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Cannot convert units");
	}

}
