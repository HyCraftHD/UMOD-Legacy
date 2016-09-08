package net.hycrafthd.umod.api;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public interface IWorldView {

	public boolean showPower();
	
	public String[] textToAdd();	
	
	public BlockPos getPos();
	
	public World getWorld();
}
