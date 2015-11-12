package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySolarPanel extends TileEntity implements IPowerProvieder{
	
	public int storedpower;

	@Override
	public int getStoredPower() {
		return storedpower;
	}

	@Override
	public void addPower(int power) {
		storedpower += power;
	}

	@Override
	public int getPower(int powerneed) {
		return (storedpower -= powerneed);
	}

	@Override
	public boolean canGetPower(int power) {
		
		return false;
	}

	@Override
	public boolean canAddPower(int power) {
		
		return false;
	}

}
