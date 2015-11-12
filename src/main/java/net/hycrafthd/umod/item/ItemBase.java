package net.hycrafthd.umod.item;

import net.hycrafthd.umod.UReference;
import net.minecraft.item.Item;

public class ItemBase extends Item{

	public ItemBase(String name) {
		this.setCreativeTab(UReference.tab);
		this.setUnlocalizedName(name);
	}
	
}
