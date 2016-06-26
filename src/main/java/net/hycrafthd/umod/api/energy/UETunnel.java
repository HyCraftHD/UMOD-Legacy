package net.hycrafthd.umod.api.energy;

import java.util.ArrayList;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class UETunnel extends ArrayList<BlockPos> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6142151165474828749L;
	private int id = -1;
	private World w;
	
	public UETunnel(World w) {
		this.w = w;
	}
	
	public boolean add(ICabel e) {
		if(this.id >= -1){throw new IllegalArgumentException("Not Init error");}
		e.setTunnelID(this.id);
		return super.add(e.getPos());
	}
	
	public ICabel[] getOutput() {
		ArrayList<ICabel> cabs = new ArrayList<ICabel>();
		for(BlockPos pos : this){
			ICabel cab = (ICabel) w.getTileEntity(pos);
			if(cab.isOutput())
			cabs.add(cab);
		}
		ICabel[] outputs = new ICabel[cabs.size()];
		int i = 0;
		for(ICabel cab : cabs){
			outputs[i] = cab;
			i++;
		}
		return outputs;
	}

	public ICabel[] getInput() {
		ArrayList<ICabel> cabs = new ArrayList<ICabel>();
		for(BlockPos pos : this){
			ICabel cab = (ICabel) w.getTileEntity(pos);
			if(cab.isOutput())
			cabs.add(cab);
		}
		ICabel[] inputs = new ICabel[cabs.size()];
		int i = 0;
		for(ICabel cab : cabs){
			inputs[i] = cab;
			i++;
		}
		return inputs;
	}
	
	public void setID(int id){
		this.id = id;
		for(BlockPos pos : this){
			ICabel cab = (ICabel) w.getTileEntity(pos);
			if(cab != null){
			cab.setTunnelID(id);
			}
		}
	}
	
	public int getID(){
		return id;
	}
	
	public World getWorld(){
		return w;
	}
	
	public void transfare(){
		ICabel[] out = getOutput();
		ICabel[] in = getInput();
		double d = 0;
        for (ICabel cab : out) {
			d = d + cab.needsEnergy();
		}
		for (ICabel cab : in) {
			
		}
	}
}
