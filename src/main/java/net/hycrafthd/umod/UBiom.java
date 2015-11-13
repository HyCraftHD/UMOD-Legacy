package net.hycrafthd.umod;

import net.hycrafthd.umod.biom.InfestedBiom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class UBiom {

	public static BiomeGenBase infestedBiomBase;
	public static int infestedBiomId;
	
	public UBiom() {
		init();
		register();
	}

	private void init() {
		infestedBiomId = 80;
		infestedBiomBase = new InfestedBiom(infestedBiomId);
	}
	
	private void register() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(infestedBiomBase, 8));
	}
}
