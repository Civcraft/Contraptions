package com.programmerdan.minecraft.contraptions.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.material.MaterialData;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

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
	private Material type;
	private short damage;
	private Map<Enchantment, Integer> safeEnch;
	private Map<Enchantment, Integer> unsafeEnch;
	private ItemMeta meta;
	private MaterialData data;
	
	public AdvItemStack(Material type, double size, short damage,
			Map<Enchantment, Integer> optionalEnchantments,
			Map<Enchantment, Integer> optionalUnsafeEnchantments,
			ItemMeta optionalMeta, MaterialData optionalData) {
		setup(type, size, damage, optionalEnchantments, optionalUnsafeEnchantments,
				optionalMeta, optionalData);
	}
	
	private void setup(Material type, double size, short damage,
			Map<Enchantment, Integer> optionalEnchantments,
			Map<Enchantment, Integer> optionalUnsafeEnchantments,
			ItemMeta optionalMeta, MaterialData optionalData) {
		this.type = type;
		this.size = size;
		this.damage = damage;
		this.safeEnch = (optionalEnchantments == null) ? new HashMap<Enchantment, Integer>() : optionalEnchantments;
		this.unsafeEnch = (optionalUnsafeEnchantments == null) ? new HashMap<Enchantment, Integer>() : optionalUnsafeEnchantments;
		this.meta = optionalMeta;
		this.data = optionalData;
	}
	
	public AdvItemStack(Material type, double size, short damage) {
		setup(type, size, damage, null, null,
				null, null);		
	}
	
	public AdvItemStack(Material type, double size) {
		setup(type, size, (short) 0, null, null,
				null, null);
	}
	
	public double getSize() {
		return size;
	}
	
	@Override
	public AdvItemStack clone() {
		return new AdvItemStack(this.type, this.size, this.damage, this.safeEnch, this.unsafeEnch, 
				this.meta, this.data);
	}

	public void setSize(double size) {
		this.size = size;
	}
}
