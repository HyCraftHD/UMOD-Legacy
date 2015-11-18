package net.hycrafthd.umod.block;

import java.util.Random;

import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockInfectedGrass extends BlockBase implements IInfectedBlock {

	public BlockInfectedGrass() {
		super(Material.grass);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGrass);
		this.setTickRandomly(true);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			BlockPos BlockPos2 = pos.add(0, 1, 0);
			if (worldIn.getBlockState(BlockPos2).getBlock() != Blocks.air) {
				worldIn.setBlockState(pos, UBlocks.infectedDirt.getDefaultState());
			} else {
				if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
					for (int i = 0; i < 4; i++) {
						BlockPos blockPos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
						Block block = worldIn.getBlockState(blockPos1.up()).getBlock();
						IBlockState iBlockState1 = worldIn.getBlockState(blockPos1);
						if (iBlockState1.getBlock() == UBlocks.infectedDirt && worldIn.getLightFromNeighbors(blockPos1.up()) >= 4 && block.getLightOpacity(worldIn, blockPos1.up()) <= 2) {
							BlockPos pBlockPos2 = blockPos1.add(0, 1, 0);
							if (worldIn.getBlockState(pBlockPos2).getBlock() == Blocks.air) {
								worldIn.setBlockState(blockPos1, UBlocks.infectedGrass.getDefaultState());
							}
						}
					}
				}
			}
		}
		super.updateTick(worldIn, pos, state, rand);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return UBlocks.infectedDirt.getItemDropped(Blocks.dirt.getDefaultState(), rand, fortune);
	}

}
