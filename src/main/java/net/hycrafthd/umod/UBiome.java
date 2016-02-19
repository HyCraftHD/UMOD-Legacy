package net.hycrafthd.umod;

import net.hycrafthd.umod.biome.BiomeInfected;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class UBiome {

	public static BiomeGenBase infectedBiomBase;
	public static int infectedBiomId;
	
	public UBiome() {
		init();
		register();
	}

	private void init() {
		infectedBiomId = 80;
		infectedBiomBase = new BiomeInfected(infectedBiomId);
		Logger.debug(UBiome.class, "init()", "Init Bioms");
	}
	
	private void register() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(infectedBiomBase, 8));
		Logger.debug(UBiome.class, "register()", "Register Bioms");
	}
}
