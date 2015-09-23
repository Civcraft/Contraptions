package com.programmerdan.minecraft.contraptions.util;

/**
 * Flyweight wrapper for Bukkit ItemStack allowing for lightweight resource and item
 * representation. Allows for virtual resources to be realized and for real resources
 * to be virtualized.
 *  
 * @author ProgrammerDan
 * @since 1.0.0 September 2015
 */
public class AdvItemStack implements Cloneable{
	private double size;
	
	public AdvItemStack(double size) {
		this.size = size;
	}
	
	public double getSize() {
		return size;
	}
	
	@Override
	public AdvItemStack clone() {
		return new AdvItemStack(this.size);
	}

	public void setSize(double size) {
		this.size = size;
	}
}
