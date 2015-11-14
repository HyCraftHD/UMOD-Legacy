package net.hycrafthd.umod.item;

import java.util.Random;

import net.hycrafthd.umod.world.InfectedTreeGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemFundamental extends ItemBase {

	public ItemFundamental() {
		super();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		BlockPos pos = new BlockPos(playerIn.posX, playerIn.posY, playerIn.posZ);
		new InfectedTreeGen(false, 5, 0, 0, false).generate(worldIn, new Random(), pos);
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
