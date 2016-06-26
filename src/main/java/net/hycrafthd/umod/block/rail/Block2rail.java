package net.hycrafthd.umod.block.rail;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Block2rail extends Block{

	public Block2rail() {
		super(Material.iron);
		this.setBlockBounds(0F, 0F, 0.3F, 1F, 0.2F, 0.7F);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
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
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
    	return false;
    }

    @Override
    public boolean isVisuallyOpaque() {
    	return false;
    }
    
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		worldIn.getBlockState(pos).getBlock().breakBlock(worldIn, pos, state);
		worldIn.destroyBlock(pos.add(-1,0,0), true);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if(!(worldIn.getBlockState(pos.add(-1,0,0)).getBlock() instanceof BlockExtendedRail)){
			worldIn.destroyBlock(pos, false);
		}
		super.updateTick(worldIn, pos, state, rand);
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {}
}
