package net.hycrafthd.umod.block;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.tileentity.TileEntityItemPipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.relauncher.*;

public class BlockItemPipe extends BlockBaseMachine implements ISpiritProvider, IConduitBlock, IPlugabel,IBlockInformation {
	
	private String spi;
	
	public BlockItemPipe(String sp) {
		this.spi = sp;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemPipe();
	}
	
	@Override
	public String getSpirte() {
		return spi;
	}

	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		return w.getTileEntity(p) instanceof ISidedInventory || w.getTileEntity(p) instanceof TileEntityItemPipe;
	}
	
	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, @SuppressWarnings("rawtypes") List list, Entity collidingEntity) {
		this.setBlockBoundsBasedOnState(world, pos);
		super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
	}
	
	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	public boolean isFullCube() {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess w, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess w, BlockPos pos) {
		IPlugabel pip = (IPlugabel) w.getTileEntity(pos);
		if (pip != null) {
			boolean csouth = pip.canConnect(w, pos.south());
			boolean cnorth = pip.canConnect(w, pos.north());
			boolean cdown = pip.canConnect(w, pos.down());
			boolean cup = pip.canConnect(w, pos.up());
			boolean ceast = pip.canConnect(w, pos.east());
			boolean cwest = pip.canConnect(w, pos.west());
			float anfangunten = 0.3F;
			float anfnagoben = 0.7F;
			float anfangX = 0.3F;
			float endeX = 0.7F;
			float anfangZ = 0.3F;
			float endeZ = 0.7F;
			
			if (cup) {
				anfnagoben = 1;
			}
			if (cdown) {
				anfangunten = 0;
			}
			if (cwest) {
				anfangX = 0;
			}
			if (ceast) {
				endeX = 1;
			}
			if (cnorth) {
				anfangZ = 0;
			}
			if (csouth) {
				endeZ = 1;
			}
			
			this.setBlockBounds(anfangX, anfangunten, anfangZ, endeX, anfnagoben, endeZ);
			}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tip, boolean advanced) {
		   if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			   tip.add("DONT USE IN DEV");
		   }else{
			   tip.add(EnumChatFormatting.RED + "LSHIFT" + EnumChatFormatting.GRAY + " for more Information");
		   }
	}
	
}
