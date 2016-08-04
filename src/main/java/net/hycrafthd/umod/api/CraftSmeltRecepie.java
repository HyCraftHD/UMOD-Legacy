package net.hycrafthd.umod.api;

import net.minecraft.item.ItemStack;

public class CraftSmeltRecepie {
	
	public ItemStack[] line1;
	public ItemStack[] line2;
	public ItemStack[] line3;
	public ItemStack output;
	
	public CraftSmeltRecepie(ItemStack[] line1, ItemStack[] line2, ItemStack[] line3, ItemStack output) {
		this.line1 = line1;
		this.line2 = line2;
		this.line3 = line3;
		this.output = output;
	}
	
	public ItemStack[] getLine1() {
		return line1;
	}
	
	public ItemStack[] getLine2() {
		return line2;
	}
	
	public ItemStack[] getLine3() {
		return line3;
	}
	
	public ItemStack getOutput() {
		return output;
	}
}
