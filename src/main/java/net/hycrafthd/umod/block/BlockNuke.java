package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.EntityNukePrimed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.*;
import net.minecraft.world.*;

public class BlockNuke extends BlockBase {
	
	public static final PropertyBool EXPLODE = PropertyBool.create("explode");
	
	public BlockNuke() {
		super(Material.tnt);
		this.setDefaultState(this.blockState.getBaseState().withProperty(EXPLODE, Boolean.valueOf(false)));
		this.setCreativeTab(UReference.things);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		
		if (worldIn.isBlockPowered(pos)) {
			this.onBlockDestroyedByPlayer(worldIn, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
			worldIn.setBlockToAir(pos);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (worldIn.isBlockPowered(pos)) {
			this.onBlockDestroyedByPlayer(worldIn, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)));
			worldIn.setBlockToAir(pos);
		}
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		if (!worldIn.isRemote) {
			EntityNukePrimed entitytntprimed = new EntityNukePrimed(worldIn, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, explosionIn.getExplosivePlacedBy());
			entitytntprimed.fuse = worldIn.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
			worldIn.spawnEntityInWorld(entitytntprimed);
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		this.explode(worldIn, pos, state, (EntityLivingBase) null);
	}
	
	public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter) {
		if (!worldIn.isRemote) {
			if (((Boolean) state.getValue(EXPLODE)).booleanValue()) {
				// float power = 2F + (((5000) / 10369F) * 18F);
				// ProcessHandler.addProcess(new NuclearExplosion(worldIn, pos.getX(), pos.getY(), pos.getZ(), power));
				EntityNukePrimed entitytntprimed = new EntityNukePrimed(worldIn, pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, igniter);
				worldIn.spawnEntityInWorld(entitytntprimed);
				worldIn.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
			}
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (playerIn.getCurrentEquippedItem() != null) {
			Item item = playerIn.getCurrentEquippedItem().getItem();
			
			if (item == Items.flint_and_steel || item == Items.fire_charge) {
				this.explode(worldIn, pos, state.withProperty(EXPLODE, Boolean.valueOf(true)), playerIn);
				worldIn.setBlockToAir(pos);
				
				if (item == Items.flint_and_steel) {
					playerIn.getCurrentEquippedItem().damageItem(1, playerIn);
				} else if (!playerIn.capabilities.isCreativeMode) {
					--playerIn.getCurrentEquippedItem().stackSize;
				}
				
				return true;
			}
		}
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!worldIn.isRemote && entityIn instanceof EntityArrow) {
			EntityArrow entityarrow = (EntityArrow) entityIn;
			
			if (entityarrow.isBurning()) {
				this.explode(worldIn, pos, worldIn.getBlockState(pos).withProperty(EXPLODE, Boolean.valueOf(true)), entityarrow.shootingEntity instanceof EntityLivingBase ? (EntityLivingBase) entityarrow.shootingEntity : null);
				worldIn.setBlockToAir(pos);
			}
		}
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(EXPLODE, Boolean.valueOf((meta & 1) > 0));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Boolean) state.getValue(EXPLODE)).booleanValue() ? 1 : 0;
	}
	
	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { EXPLODE });
	}
	
}
