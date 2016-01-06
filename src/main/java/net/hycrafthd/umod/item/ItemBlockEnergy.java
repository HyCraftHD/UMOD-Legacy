package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.api.energy.IEnergyMessage;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockEnergy extends ItemBlock{

	public ItemBlockEnergy(Block block) {
		super(block);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced) {
		if (this.block instanceof IEnergyMessage) {
			tip.add(EnumChatFormatting.BLUE + ((IEnergyMessage) this.block).getMessage(0));
		}
	}
}
