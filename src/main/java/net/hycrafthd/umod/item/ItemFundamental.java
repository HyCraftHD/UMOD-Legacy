package net.hycrafthd.umod.item;

import net.hycrafthd.umod.api.ProcessHandler;
import net.hycrafthd.umod.world.explosion.NuclearExplosion;
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
		// new SchematicInfestedPortal().generate(worldIn, pos.getX(),
		// pos.getY(), pos.getZ());
		
		float power = 2F + (((5000) / 10369F) * 18F);
		ProcessHandler.addProcess(new NuclearExplosion(worldIn, pos.getX(), pos.getY(), pos.getZ(), power));
		
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
}
