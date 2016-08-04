package net.hycrafthd.umod.block;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.tileentity.TileEntityItemPipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockItemPipe extends BlockBaseMachine implements ISpiritProvider , IConduitBlock{

	public BlockItemPipe() {
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityItemPipe();
	}

	@Override
	public String getSpirte() {
		return null;
	}

}
