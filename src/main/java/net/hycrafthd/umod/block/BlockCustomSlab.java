package net.hycrafthd.umod.block;

import java.util.Random;

import net.hycrafthd.corelib.util.ItemUtil;
import net.hycrafthd.umod.UReference;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public abstract class BlockCustomSlab extends BlockSlab {
	
	private final Block modelBlock;
	private final IBlockState modelState;
	private final Block itemslab;
	
	BlockCustomSlab(IBlockState modelState, Block itemslab) {
		super(modelState.getBlock().getMaterial());
		this.modelBlock = modelState.getBlock();
		this.modelState = modelState;
		this.setHardness(this.modelBlock.getBlockHardness(null, null));
		this.setResistance(this.modelBlock.getExplosionResistance(null) / 3.0F);
		this.setStepSound(this.modelBlock.stepSound);
		
		IBlockState blockState = this.blockState.getBaseState();
		if (!this.isDouble()) {
			blockState = blockState.withProperty(HALF, EnumBlockHalf.BOTTOM);
		}
		this.setCreativeTab(UReference.blocks);
		this.setDefaultState(blockState);
		this.itemslab = itemslab;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (itemslab == null && !this.isDouble()) {
			return ItemUtil.from(this);
		}
		return ItemUtil.from(itemslab);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World worldIn, BlockPos pos) {
		if (itemslab == null && !this.isDouble()) {
			return ItemUtil.from(this);
		}
		return ItemUtil.from(itemslab);
	}
	
	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName();
	}
	
	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState iblockstate = this.getStateFromMeta(meta);
		if (!this.isDouble()) {
			iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}
		return this.isDouble() ? iblockstate : (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D) ? iblockstate : iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.TOP));
	}
	
	@Override
	public IProperty getVariantProperty() {
		return null;
	}
	
	@Override
	public Object getVariant(ItemStack stack) {
		return null;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState blockState = this.getDefaultState();
		if (!this.isDouble()) {
			EnumBlockHalf value = EnumBlockHalf.BOTTOM;
			if ((meta & 1) != 0) {
				value = EnumBlockHalf.TOP;
			}
			
			blockState = blockState.withProperty(HALF, value);
		}
		
		return blockState;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		if (this.isDouble()) {
			return 0;
		}
		
		if ((EnumBlockHalf) state.getValue(HALF) == EnumBlockHalf.TOP) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public BlockState createBlockState() {
		if (this.isDouble()) {
			return super.createBlockState();
		} else {
			return new BlockState(this, new IProperty[] { HALF });
		}
	}
	
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		this.modelBlock.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		this.modelBlock.randomDisplayTick(worldIn, pos, state, rand);
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		this.modelBlock.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	@SideOnly(Side.CLIENT)
	public int getMixedBrightnessForBlock(IBlockAccess worldIn, BlockPos pos) {
		return this.modelBlock.getMixedBrightnessForBlock(worldIn, pos);
	}
	
	public float getExplosionResistance(Entity exploder) {
		return this.modelBlock.getExplosionResistance(exploder);
	}
	
	public int tickRate(World worldIn) {
		return this.modelBlock.tickRate(worldIn);
	}
	
	public Vec3 modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3 motion) {
		return this.modelBlock.modifyAcceleration(worldIn, pos, entityIn, motion);
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer() {
		return this.modelBlock.getBlockLayer();
	}
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
		return this.modelBlock.getSelectedBoundingBox(worldIn, pos);
	}
	
	public boolean isCollidable() {
		return this.modelBlock.isCollidable();
	}
	
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return this.modelBlock.canCollideCheck(state, hitIfLiquid);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return this.modelBlock.canPlaceBlockAt(worldIn, pos);
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.onNeighborBlockChange(worldIn, pos, this.modelState, Blocks.air);
		this.modelBlock.onBlockAdded(worldIn, pos, this.modelState);
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		this.modelBlock.breakBlock(worldIn, pos, this.modelState);
	}
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, Entity entityIn) {
		this.modelBlock.onEntityCollidedWithBlock(worldIn, pos, entityIn);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		this.modelBlock.updateTick(worldIn, pos, state, rand);
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		return this.modelBlock.onBlockActivated(worldIn, pos, this.modelState, playerIn, EnumFacing.DOWN, 0.0F, 0.0F, 0.0F);
	}
	
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		this.modelBlock.onBlockDestroyedByExplosion(worldIn, pos, explosionIn);
	}
	
	public MapColor getMapColor(IBlockState state) {
		return this.modelBlock.getMapColor(this.modelState);
	}
	
}
