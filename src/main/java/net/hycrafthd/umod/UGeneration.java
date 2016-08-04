package net.hycrafthd.umod;

import java.util.ArrayList;

import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UGeneration {
	
	private ArrayList<UGenerator> generators;
	
	public UGeneration() {
		init();
	}
	
	private void init() {
		this.generators = new ArrayList<UGenerator>();
	}
	
	public void register() {
		for (UGenerator generator : this.generators)
			GameRegistry.registerWorldGenerator((IWorldGenerator) generator.getGenerator(), generator.getModGenerationWeight());
		UMod.log.debug("All generators registered");
	}
	
	public boolean addGenerator(Object generator, int modGenerationWeight) {
		UGenerator generator2 = new UGenerator(generator, modGenerationWeight);
		if (generators.contains(generator2))
			return false;
		generators.add(generator2);
		UMod.log.debug("Add Generator to List");
		return true;
	}
	
	public boolean removeGenerator(UGenerator generator) {
		if (!generators.contains(generator))
			return false;
		generators.remove(generator);
		UMod.log.debug("Remove Generator from List");
		return true;
	}
	
	public void setGenerators(ArrayList<UGenerator> generators) {
		this.generators = generators;
	}
	
	public ArrayList<UGenerator> getGenerators() {
		return generators;
	}
	
	public class UGenerator {
		
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
