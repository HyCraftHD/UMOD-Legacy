package net.hycrafthd.umod.api;

import net.minecraft.util.EnumFacing;

public class SideBoolSet {

	private boolean bool;
	private EnumFacing face;
	
	public SideBoolSet(boolean bool,EnumFacing face) {
		this.bool = bool;
		this.face = face;
	}
	
	public boolean getBoolean(){
		return bool;
	}
	
	public EnumFacing getFacing(){
		return face;
	}
	
}
