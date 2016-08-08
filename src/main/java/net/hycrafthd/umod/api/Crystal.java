package net.hycrafthd.umod.api;

import net.hycrafthd.umod.item.ItemBase;
import net.minecraft.item.ItemStack;

public abstract class Crystal extends ItemBase{
	
	public abstract int energyUnits();
	
	public static boolean isStackCrystal(ItemStack is){
		if(is != null && is.getItem() instanceof Crystal)return true;
		return false;
	}
	
}
