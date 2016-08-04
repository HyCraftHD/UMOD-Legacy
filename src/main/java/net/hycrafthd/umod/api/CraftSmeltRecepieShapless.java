package net.hycrafthd.umod.api;

import net.minecraft.item.ItemStack;

public class CraftSmeltRecepieShapless {
	
	private ItemStack[] arr;
	private ItemStack output;
	
	public CraftSmeltRecepieShapless(ItemStack[] arr, ItemStack output) {
		this.arr = arr;
		this.output = output;
	}
	
	public ItemStack[] getItemsRequied() {
		return arr;
	}
	
	public ItemStack getOutput() {
		return output;
	}
}
