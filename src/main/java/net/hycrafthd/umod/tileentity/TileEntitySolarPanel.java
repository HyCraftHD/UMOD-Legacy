package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySolarPanel extends TileEntity implements IPowerProvieder{
	
	public int storedpower;
	public final int MAXIMUM_POWER = 3000;

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
		if(storedpower  - power >= 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean canAddPower(int power) {
		if(power + storedpower >= MAXIMUM_POWER){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		if(canAddPower(3)){
			addPower(3);
		}
	}

}
