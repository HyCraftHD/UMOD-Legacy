package net.hycrafthd.umod.block;

import net.hycrafthd.umod.item.ItemEnergyDisplay;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEnergyPanel extends BlockBaseMachine{

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if(playerIn.getCurrentEquippedItem() != null && playerIn.getCurrentEquippedItem().getItem() instanceof ItemEnergyDisplay && playerIn.getCurrentEquippedItem().getTagCompound() != null && playerIn.getCurrentEquippedItem().getTagCompound().hasKey(ItemEnergyDisplay.NBT_TAG)){
			TileEntityEnergyPannel pan = (TileEntityEnergyPannel) worldIn.getTileEntity(pos);
			pan.setStack(playerIn, playerIn.getCurrentEquippedItem());
			return true;
		}
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEnergyPannel();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(placer instanceof EntityPlayer && worldIn.isRemote){
			TileEntityEnergyPannel p = (TileEntityEnergyPannel) worldIn.getTileEntity(pos);
			p.setView(-Minecraft.getMinecraft().getRenderManager().playerViewY);
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

}
