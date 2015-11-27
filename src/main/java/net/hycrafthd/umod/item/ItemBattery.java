package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IGuiProvider;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBattery extends ItemBase{

	public ItemBattery() {
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {}
	
	
}
