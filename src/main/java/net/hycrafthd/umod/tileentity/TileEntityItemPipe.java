package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.entity.EntityFX;
import net.minecraft.item.ItemStack;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileEntityItemPipe extends TileEntity implements IPlugabel,IConduitProvider,IUpdatePlayerListBox{
	
	public boolean isin = false;
	public ItemStack cond = null;
	
	public TileEntityItemPipe() {
		
	}
	
	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		return ((IPlugabel)w.getBlockState(getPos()).getBlock()).canConnect(w, p);
	}

	@Override
	public ItemStack getConduit() {
		return cond;
	}

	@Override
	public boolean hasConduit() {
		return cond != null;
	}

	@Override
	public void update() {
		if(isin)return;
		this.worldObj.spawnEntityInWorld(new EntityFX(worldObj, pos));
		isin = true;
	}

	@Override
	public void setConduit(ItemStack b) {
		this.cond = b;
	}
	
}
