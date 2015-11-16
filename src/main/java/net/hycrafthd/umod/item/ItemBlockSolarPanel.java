package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.api.IEnergyMessage;
import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockSolarPanel extends ItemBlockSubBase {

	public ItemBlockSolarPanel(Block block) {
		super(block);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced) {
		if (this.block instanceof IEnergyMessage) {
			tip.add(EnumChatFormatting.BLUE + ((IEnergyMessage) this.block).getMessage());
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(stack.getMetadata());
		return "tile.solarpanel" + type.getName();
	}

}
