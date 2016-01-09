package net.hycrafthd.umod.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemBattery extends ItemBase {

	public ItemBattery() {
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
	}

}
