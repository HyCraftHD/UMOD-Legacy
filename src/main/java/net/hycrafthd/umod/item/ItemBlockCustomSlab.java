package net.hycrafthd.umod.item;

import net.hycrafthd.umod.block.*;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

public class ItemBlockCustomSlab extends ItemBlockBase {
	
	protected final BlockSlab singleSlab;
	protected final BlockSlab doubleSlab;
	
	public ItemBlockCustomSlab(Block block, BlockHalfCustomSlab singleSlab, BlockDoubleCustomSlab doubleSlab) {
		super(block);
		this.singleSlab = singleSlab;
		this.doubleSlab = doubleSlab;
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.singleSlab.getUnlocalizedName(stack.getMetadata());
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (stack.stackSize == 0) {
			return false;
		} else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
			return false;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			
			if (iblockstate.getBlock() == this.singleSlab) {
				BlockSlab.EnumBlockHalf enumblockhalf = (BlockSlab.EnumBlockHalf) iblockstate.getValue(BlockSlab.HALF);
				
				if ((side == EnumFacing.UP && enumblockhalf == BlockSlab.EnumBlockHalf.BOTTOM || side == EnumFacing.DOWN && enumblockhalf == BlockSlab.EnumBlockHalf.TOP)) {
					IBlockState iblockstate1 = this.doubleSlab.getDefaultState();
					
					if (worldIn.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBox(worldIn, pos, iblockstate1)) && worldIn.setBlockState(pos, iblockstate1, 3)) {
						worldIn.playSoundEffect((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), this.doubleSlab.stepSound.getPlaceSound(), (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.doubleSlab.stepSound.getFrequency() * 0.8F);
						--stack.stackSize;
					}
					
					return true;
				}
			}
			
			return tryPlace(stack, worldIn, pos.offset(side)) ? true : super.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side, EntityPlayer player, ItemStack stack) {
		BlockPos blockpos1 = pos;
		IBlockState iblockstate = worldIn.getBlockState(pos);
		
		if (iblockstate.getBlock() == this.singleSlab) {
			boolean flag = iblockstate.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP;
			
			if ((side == EnumFacing.UP && !flag || side == EnumFacing.DOWN && flag)) {
				return true;
			}
		}
		
		pos = pos.offset(side);
		IBlockState iblockstate1 = worldIn.getBlockState(pos);
		return iblockstate1.getBlock() == this.singleSlab ? true : super.canPlaceBlockOnSide(worldIn, blockpos1, side, player, stack);
	}
	
	private boolean tryPlace(ItemStack stack, World world, BlockPos pos) {
		IBlockState iblockstate = world.getBlockState(pos);
		
		if (iblockstate.getBlock() == this.singleSlab) {
			IBlockState iblockstate1 = this.doubleSlab.getDefaultState();
			if (world.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBox(world, pos, iblockstate1)) && world.setBlockState(pos, iblockstate1, 3)) {
				world.playSoundEffect((double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), this.doubleSlab.stepSound.getPlaceSound(), (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.doubleSlab.stepSound.getFrequency() * 0.8F);
				--stack.stackSize;
			}
			
			return true;
		}
		
		return false;
	}
	
}
