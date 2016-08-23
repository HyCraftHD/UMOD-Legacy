package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraftforge.fml.relauncher.*;

public abstract class ItemBase extends Item {
	
	public ItemBase() {
		this.setCreativeTab(UReference.things);
	}
	
	@SuppressWarnings("rawtypes")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
	
}
