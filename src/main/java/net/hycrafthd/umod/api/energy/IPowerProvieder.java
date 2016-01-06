package net.hycrafthd.umod.api.energy;

import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IPowerProvieder extends IUpdatePlayerListBox{

	public int getStoredPower();
	
	public void addPower(int power);
	
	public int getPower(int powerneed);
	
	public boolean canGetPower(BlockPos pos,int power);
	
	public boolean canAddPower(BlockPos pos,int power);
	
	public int getMaximalPower();
	
	public boolean isWorking();
	
	public String getErrorMessage();
	
	public boolean hasPower();
	
	public int getPowerProducNeeds();
	
	public BlockPos getPos();
	
	public World getWorld();
}
