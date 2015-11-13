package net.hycrafthd.umod.biom;

import net.minecraft.world.biome.BiomeGenBase;

public class InfestedBiom extends BiomeGenBase{

	private final String name = "Infested Biom";
	
	public InfestedBiom(int id) {
		super(id);
		setBiomeName(name);
	}

}
