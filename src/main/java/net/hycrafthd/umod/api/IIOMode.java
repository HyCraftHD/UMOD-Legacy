package net.hycrafthd.umod.api;

import net.minecraft.util.EnumFacing;

public interface IIOMode {
	
	public EnumFacing getFacing(int i);
	
	public void setFacing(int i, EnumFacing face);
	
	public int hasSomefacing(EnumFacing i);
	
	public int getModeCount();
	
	public void setEnumInput(EnumFacing fac);
	
	public void setEnumOutput(EnumFacing fac);
	
}
