package net.hycrafthd.umod.api.energy;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface ICabel {

	public double getEnergy();
	
	public void setEnergy(double i);
	
	public void searchForInput(ICabel cab);
	
	public void tranferTo(ICabel cab);
	
	public BlockPos getPos();
	
	public World getWorld();
	
	public boolean isInput();
	
	public boolean isOutput();
	
	public double getMaxEnergy();
	
	public String getEnergyClass();
	
	public void addToTunnel(ICabel cab);
	
	public ICabel[] getOutputsFromTunnel();
	
	public ICabel[] getInputsFromTunnel();
	
	public int getTunnelIDofCabel();
	
	public boolean hasConnectedOutput();
	
	public UETunnel getTunnel();
	
	public void setTunnelID(int i);
	
	public double needsEnergy();
	
	public double addPowerToOutput(double i);
	
	public double removeFromInput(double i);
	
	public double hasEnergy(double i);
	
	public double getMaxEnergyOut();
}
