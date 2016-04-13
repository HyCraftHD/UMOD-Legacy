package net.hycrafthd.umod.api.energy;

import java.util.ArrayList;

public class UETunnel extends ArrayList<ICabel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6142151165474828749L;
	private int id = -1;
	
	@Override
	public boolean add(ICabel e) {
		if(this.id == -1){throw new IllegalArgumentException("Not Init error");}
		e.setTunnelID(this.id);
		return super.add(e);
	}
	
	public ICabel[] getOutput() {
		ArrayList<ICabel> cabs = new ArrayList<ICabel>();
		for(ICabel cab : this){
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
		for(ICabel cab : this){
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
	}
	
	public int getID(){
		return id;
	}
}
