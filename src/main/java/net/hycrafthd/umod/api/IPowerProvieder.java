package net.hycrafthd.umod.api;

import net.minecraft.server.gui.IUpdatePlayerListBox;

public interface IPowerProvieder extends IUpdatePlayerListBox{

	public int getStoredPower();
	
	public void addPower(int power);
	
	public int getPower(int powerneed);
	
	public boolean canGetPower(int power);
	
	public boolean canAddPower(int power);
	
	public int getMaximalPower();
	
	public boolean isWorking();
	
	public String getErrorMessage();
	
	public boolean hasPower();
	
	public int getPowerProducNeeds();
}
