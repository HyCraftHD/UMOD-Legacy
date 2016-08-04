package net.hycrafthd.umod.block;

import java.util.Random;

import net.hycrafthd.umod.*;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.hycrafthd.umod.world.GenInfectedTree;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class BlockInfectedSapling extends BlockBush implements IGrowable, IInfectedBlock {
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	public BlockInfectedSapling() {
		super();
		this.setCreativeTab(UReference.tab);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		this.setDefaultState(this.getDefaultState().withProperty(STAGE, Integer.valueOf(0)));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
				this.grow(worldIn, pos, state, rand);
			}
		}
	}

	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((Integer) state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World world, BlockPos pos, IBlockState state, Random rand) {
		new GenInfectedTree(true, MathHelper.getRandomIntegerInRange(rand, 4, 6), 0, 0, false).generate(world, rand, pos);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return this.canPlaceBlockOn(world.getBlockState(pos.down()).getBlock());
	}

	@Override
	public boolean canPlaceBlockOn(Block ground) {
		return ground == UBlocks.infectedDirt || ground == UBlocks.infectedGrass;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return worldIn.rand.nextFloat() < 0.75D;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		this.grow(worldIn, pos, state, rand);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i |= ((Integer) state.getValue(STAGE)).intValue() << 3;
		return i;
	}

	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { STAGE });
	}

	@Override
	public Block getNormalBlock() {
		return Blocks.sapling;
	}

}