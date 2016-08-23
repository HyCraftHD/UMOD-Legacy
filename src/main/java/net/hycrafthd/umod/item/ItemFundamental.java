package net.hycrafthd.umod.item;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFundamental extends ItemBase {
	
	public ItemFundamental() {
		super();
		setCreativeTab(UReference.infected);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		// BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);
		// new USchematicInfectedRuin().generate(worldIn, pos.getX(), pos.getY(), pos.getZ());
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
