package net.hycrafthd.umod.api;

public interface IPowerProvieder {

	public int getStoredPower();
	
	public void addPower(int power);
	
	public int getPower(int powerneed);
	
	public boolean canGetPower(int power);
	
	public boolean canAddPower(int power);
}
