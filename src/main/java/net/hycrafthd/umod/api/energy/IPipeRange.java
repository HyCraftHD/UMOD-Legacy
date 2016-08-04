package net.hycrafthd.umod.api.energy;

public interface IPipeRange {
	
	public int getPastPipeCount();
	
	public int addBlock(int count);
	
	public int getMaximalRange();
	
	public void remove(int count);
	
	public void clearPast();
	
}
