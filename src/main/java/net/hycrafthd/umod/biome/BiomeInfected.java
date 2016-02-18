package net.hycrafthd.umod.biome;

import java.util.Random;

import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.world.GenInfectedTree;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeInfected extends BiomeGenBase {

	private final String name = "Infected Biome";
	private GenInfectedTree tree;

	public BiomeInfected(int id) {
		super(id);
		this.setBiomeName(name);
		this.tree = new GenInfectedTree(false);
		this.topBlock = UBlocks.infectedGrass.getDefaultState();
		this.fillerBlock = UBlocks.infectedDirt.getDefaultState();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.theBiomeDecorator.treesPerChunk = 4;
		this.spawnableMonsterList.add(new SpawnListEntry(EntityInfectedCow.class, 10, 5, 20));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityInfectedCreeper.class, 10, 5, 10));
		this.setHeight(new Height(0.2F, 0.3F));
	}

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return rand.nextInt(3) > 0 ? this.tree : super.genBigTreeChance(rand);
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
