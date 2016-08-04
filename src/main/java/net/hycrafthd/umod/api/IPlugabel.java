package net.hycrafthd.umod.api;

import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IPlugabel {
	
	public boolean canConnect(IBlockAccess w, BlockPos p);
	
}
