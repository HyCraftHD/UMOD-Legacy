package net.hycrafthd.umod.item;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemCabletester extends ItemBase {
	
	public ItemCabletester() {
		this.setMaxDamage(400);
		this.setCreativeTab(UReference.maschines);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity ent = worldIn.getTileEntity(pos);
		if (ent != null && ent instanceof TileEntityCable) {
			IPowerProvieder pro = (IPowerProvieder) ent;
			playerIn.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN.toString() + pro.getStoredPower() + EnumChatFormatting.RESET + "/" + EnumChatFormatting.BLUE + pro.getMaximalPower()));
			if (!playerIn.capabilities.isCreativeMode) {
				stack.setItemDamage(stack.getItemDamage() + 1);
			}
		}
		return true;
	}
}
