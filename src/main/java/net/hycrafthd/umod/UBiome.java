package net.hycrafthd.umod;

import net.hycrafthd.umod.biome.BiomeInfested;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class UBiome {

	public static BiomeGenBase infestedBiomBase;
	public static int infestedBiomId;
	
	public UBiome() {
		init();
		register();
	}

	private void init() {
		infestedBiomId = 80;
		infestedBiomBase = new BiomeInfested(infestedBiomId);
		Logger.debug(UBiome.class, "init()", "Init Bioms");
	}
	
	private void register() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(infestedBiomBase, 8));
		Logger.debug(UBiome.class, "register()", "Register Bioms");
	}
}
