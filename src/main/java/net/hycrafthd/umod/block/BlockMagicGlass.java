package net.hycrafthd.umod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.*;

public class BlockMagicGlass extends BlockBase {
	
	public BlockMagicGlass() {
		super(Material.glass);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGlass);
		
	}
	
	public int quantityDropped(Random random) {
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean isFullCube() {
		return false;
	}
	
	@Override
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		
		if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate) {
			return true;
		}
		
		if (block == this) {
			return false;
		}
		
		return block == this ? false : super.shouldSideBeRendered(worldIn, pos, side);
	}
	
	@Override
	public boolean canSilkHarvest() {
		return true;
	}
	
}
