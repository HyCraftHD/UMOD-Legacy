package net.hycrafthd.umod;

import java.util.ArrayList;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UGeneration {

	private GameRegistry gameRegistry;
	private ArrayList<UGenerator> generators;
	
	public UGeneration() {
		init();
	}

	private void init() {
		this.generators = new ArrayList<UGenerator>();
		this.gameRegistry = new GameRegistry();
	}
	
	public void register() {
		for(UGenerator generator : this.generators) gameRegistry.registerWorldGenerator((IWorldGenerator) generator.getGenerator(), generator.getModGenerationWeight());
	}
	
	public boolean addGenerator(Object generator, int modGenerationWeight){
		UGenerator generator2 = new UGenerator(generator, modGenerationWeight);
		if(generators.contains(generator2)) return false;
		generators.add(generator2);
		return true;
	}
	
	public boolean removeGenerator(UGenerator generator){
		if(!generators.contains(generator)) return false;
		generators.remove(generator);
		return true;
	}
	
	public void setGenerators(ArrayList<UGenerator> generators) {
		this.generators = generators;
	}
	
	public ArrayList<UGenerator> getGenerators() {
		return generators;
	}
	
	public class UGenerator{
		private Object generator;
		private int modGenerationWeight;
		
		public UGenerator(Object generator, int modGenerationWeight) {
			this.generator = generator;
			this.modGenerationWeight = modGenerationWeight;
		}
		
		public Object getGenerator() {
			return generator;
		}
		
		public int getModGenerationWeight() {
			return modGenerationWeight;
		}
		
	}
	
}
