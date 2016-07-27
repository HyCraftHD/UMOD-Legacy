package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPlugabel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileEntityItemPipe extends TileEntity implements IPlugabel{

	public TileEntityItemPipe() {
		
	}

	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		return false;
	}
	
}
