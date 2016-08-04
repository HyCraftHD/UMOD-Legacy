package net.hycrafthd.umod.block;

import java.util.*;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.*;
import scala.actors.threadpool.Arrays;

public class BlockInfectedFruit extends BlockBase implements IInfectedBlock {
	
	public BlockInfectedFruit() {
		super(Material.plants);
		this.setBlockBounds(5.5F / 16F, 7.5F / 16F, 5.5F / 16F, 10.5F / 16F, 16F / 16F, 10.5F / 16F);
		this.setBlockBoundsForItemRender();
	}
	
	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (world.isAirBlock(pos.up())) {
			this.dropBlockAsItem(world, pos, state, 0);
			world.setBlockToAir(pos);
		}
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		return new ArrayList<ItemStack>(Arrays.asList(new ItemStack[] { new ItemStack(UItems.infectedcrop, MathHelper.getRandomIntegerInRange(new Random(), 1, 4)) }));
	}
	
	@Override
	public Block getNormalBlock() {
		return null;
	}
	
}
