package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPlugabel;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityPipe extends TileEntity implements IPlugabel,IPowerProvieder{

	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		TileEntity et = w.getTileEntity(p);
		if(et instanceof IPowerProvieder){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStoredPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addPower(int power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPower(int powerneed) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canGetPower(int power) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAddPower(int power) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaximalPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isWorking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPower() {
		// TODO Auto-generated method stub
		return false;
	}

}
