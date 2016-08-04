package net.hycrafthd.umod.block;

import net.hycrafthd.umod.api.IConduitBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public abstract class BlockBaseMachine extends BlockBase implements ITileEntityProvider ,IConduitBlock{

	private NBTTagCompound compound;

	public BlockBaseMachine() {
		super(Material.iron);
		this.isBlockContainer = true;
		this.setHarvestLevel("pickaxe", 3);
		this.setHardness(5);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	
	
	@Override
	public int getComparatorInputOverride(World world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos);
		return Container.calcRedstone(tileEntity);
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity ent = world.getTileEntity(pos);
		compound = new NBTTagCompound();
		if(ent == null)return;
		ent.writeToNBT(compound);
		world.removeTileEntity(pos);
	}

	@Override
	public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
		super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
	}

	public boolean canProvidePower() {
		return true;
	}

	@Override
	public int getRenderType() {
		return 3;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public boolean isFullCube() {
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
	public int isProvidingStrongPower(IBlockAccess w, BlockPos pos, IBlockState state, EnumFacing side) {
		return Container.calcRedstoneFromInventory((IInventory) w.getTileEntity(pos));
	}
	
	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		ItemStack stack = new ItemStack(state.getBlock(),1,state.getBlock().getMetaFromState(state));
		stack.setTagInfo("NBTS", compound);
        spawnAsEntity(worldIn, pos, stack);
	}
	
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		TileEntity ent = worldIn.getTileEntity(pos);
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("NBTS")){
			NBTTagCompound comp = stack.getTagCompound().getCompoundTag("NBTS");
			ent.readFromNBT(comp);
		}
		ent.setPos(pos);
		ent.setWorldObj(worldIn);
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
	
}
