package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class Itemanitatomteil extends BlockBaseMachine {

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityChest();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		playerIn.openGui(UReference.modid, EnumTypeGui.BARREL.getID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
		return false;
	}
}
