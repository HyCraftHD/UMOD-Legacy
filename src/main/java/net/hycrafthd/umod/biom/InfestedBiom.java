package net.hycrafthd.umod.biom;

import java.util.Random;

import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.UReference;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class InfestedBiom extends BiomeGenBase{

	private final String name = "Infested Biome";
	
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
		this.theBiomeDecorator.treesPerChunk = 1;
		setHeight(new Height(0.3F, 0.3F));
	}
	
	@Override
	public void decorate(World worldIn, Random random, BlockPos pos) {
			UReference.infectedTreeGen.generate(worldIn, random, pos);
		super.decorate(worldIn, random, pos);
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
