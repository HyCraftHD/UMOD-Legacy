package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPlugabel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityPipe extends TileEntity implements IPlugabel{

	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		// TODO Auto-generated method stub
		return false;
	}

}
