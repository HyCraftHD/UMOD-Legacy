package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.EntityTommahak;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBattery extends ItemBase {
	
	public ItemBattery() {
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
		setCreativeTab(UReference.maschines);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List tooltip, boolean advanced) {
	}
	
}
