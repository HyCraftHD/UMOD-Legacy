package net.hycrafthd.umod.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInfectedFruit extends BlockBase {

	public BlockInfectedFruit() {
		super(Material.plants);
		this.setBlockBounds(5.5F/16F, 7.5F/16F, 5.5F/16F, 10.5F/16F, 16F/16F, 10.5F/16F);
		this.setBlockBoundsForItemRender();
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
	public int getRenderType() {
		return 3;
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

}
