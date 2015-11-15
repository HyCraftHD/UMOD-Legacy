package net.hycrafthd.umod;

import java.util.Random;

import net.hycrafthd.umod.schematic.SchematicGenerationBase;
import net.hycrafthd.umod.schematic.SchematicInfestedRuin1;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class USchematicGeneration extends SchematicGenerationBase {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		this.generateInfestedRuin1(random, chunkZ, chunkZ, world);
	}

	private void generateInfestedRuin1(Random random, int chunkX, int chunkZ, World world) {
		if (random.nextInt(25) == 0) {
			int x = chunkX * 16 + random.nextInt(16);
			int z = chunkZ * 16 + random.nextInt(16);
			int y = getWorldHeightAt(world, x, z);
			if (world.getBiomeGenForCoords(new BlockPos(x, y, z)) == UBiome.infestedBiomBase) {
				new SchematicInfestedRuin1().generate(world, x, y, z);
				System.out.println("Schematic generiert: " + x + " " + y + " " + z);
			}
		}
	}

}
