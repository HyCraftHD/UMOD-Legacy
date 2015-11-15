package net.hycrafthd.umod.api;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IPlugabel {
	
	public boolean canConnect(IBlockAccess w,BlockPos p);

}
