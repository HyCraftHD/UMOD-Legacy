package net.hycrafthd.umod.block.rail;

import java.util.List;

import net.hycrafthd.umod.*;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.tileentity.rail.TileEntityRail;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class BlockExtendedRail extends Block implements ITileEntityProvider {
	
	public BlockExtendedRail() {
		super(Material.iron);
		this.isBlockContainer = true;
		this.setBlockBounds(0F, 0F, 0.3F, 1F, 0.2F, 0.7F);
		this.setCreativeTab(UReference.tab);
	}
	
	private NBTTagCompound compound;
	
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
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityRail();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		entityClear(world, pos);
		TileEntity ent = world.getTileEntity(pos);
		compound = new NBTTagCompound();
		if (ent == null)
			return;
		ent.writeToNBT(compound);
		if (hasTileEntity(state)) {
			world.removeTileEntity(pos);
		}
	}
	
	@Override
	public int getRenderType() {
		return 3;
	}
	
	@Override
	public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
		super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		ItemStack stack = new ItemStack(UItems.railplacer);
		stack.setTagInfo("NBTS", compound);
		spawnAsEntity(worldIn, pos, stack);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity ent = worldIn.getTileEntity(pos);
		if (stack.getTagCompound() != null && stack.getTagCompound().hasKey("NBTS")) {
			NBTTagCompound comp = stack.getTagCompound().getCompoundTag("NBTS");
			ent.readFromNBT(comp);
		}
		ent.setPos(pos);
		ent.setWorldObj(worldIn);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
	private void entityClear(World worldIn, BlockPos pos) {
		@SuppressWarnings("unchecked")
		List<EntityRailFX> p = worldIn.getEntitiesWithinAABB(EntityRailFX.class, new AxisAlignedBB(pos, pos.add(2, 1, 1)));
		for (EntityRailFX fx : p) {
			fx.setDead();
		}
	}
}
