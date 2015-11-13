package net.hycrafthd.umod.biom;

import net.hycrafthd.umod.UBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class InfestedBiom extends BiomeGenBase{

	private final String name = "Infested Biom";
	
	public InfestedBiom(int id) {
		super(id);
		setBiomeName(name);
		theBiomeDecorator.deadBushPerChunk = 3;
		topBlock = UBlocks.infectedGrass.getDefaultState();
		fillerBlock = UBlocks.infectedDirt.getDefaultState();
		spawnableCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
		setHeight(new Height(0.3F, 0.3F));
	}

	@Override
	public int getSkyColorByTemp(float p_76731_1_) {
		return 0xA5F2CF;
	}
	
	@Override
	public int getWaterColorMultiplier() {
		return 0x52DB4D;
	}
	
}
