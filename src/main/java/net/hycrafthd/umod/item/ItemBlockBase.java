package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IBlockInformation;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;

public class ItemBlockBase extends ItemBlock {
	
	public ItemBlockBase(Block block) {
		super(block);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		if(this.block instanceof IBlockInformation){
			((IBlockInformation)this.block).addInformation(stack, player, tooltip, advanced);
			return;
		}
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
	
}
