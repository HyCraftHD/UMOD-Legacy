package net.hycrafthd.umod.api.energy;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IPowerProvieder extends IUpdatePlayerListBox{

	public double getStoredPower();
	
	public void addPower(double power);
	
	public double getPower(double powerneed);
	
	public boolean canGetPower(BlockPos pos,double power);
	
	public boolean canAddPower(BlockPos pos,double power);
	
	public double getMaximalPower();
	
	public boolean isWorking();
	
	public String getErrorMessage();
	
	public boolean hasPower();
	
	public double getPowerProducNeeds();
	
	public BlockPos getPos();
	
	public World getWorld();
	
	public void setEnergy(double coun);
	
	public String getEnergyClass();
	
	public boolean needsPower();
	
	public boolean productsPower();
}
