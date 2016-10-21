package net.hycrafthd.umod.item;

import net.hycrafthd.umod.UReference;

public class ItemBattery extends ItemBase {
	
	public ItemBattery() {
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
		setCreativeTab(UReference.maschines);
	}
	
}
