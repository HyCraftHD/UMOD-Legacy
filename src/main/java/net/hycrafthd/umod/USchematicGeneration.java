package net.hycrafthd.umod;

import java.util.Random;

import net.hycrafthd.umod.schematic.SchematicGenerationBase;
import net.hycrafthd.umod.schematic.SchematicInfestedRuin1;
import net.hycrafthd.umod.utils.GenerationUtils;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class USchematicGeneration extends SchematicGenerationBase {

	private void nether(Random random, int x, int z, World world) {

	}

	private void overworld(Random random, int x, int z, World world) {
		GenerationUtils.generateSchematic(SchematicInfestedRuin1.class, random, x, z, world, 25, UBiome.infestedBiomBase);
	}

	private void end(Random random, int x, int z, World world) {

	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int x = chunkX * 16;
		int z = chunkZ * 16;
		switch (world.provider.getDimensionId()) {
		case -1:
			nether(random, x, z, world);
			break;
		case 0:
			overworld(random, x, z, world);
			break;
		case 1:
			end(random, x, z, world);
			break;

		}
	}

	/*private void generateInfestedRuin1(Random random, int x, int z, World world) {
		if (random.nextInt(25) == 0) {
			int posX = x + random.nextInt(16);
			int posZ = z + random.nextInt(16);
			int posY = getWorldHeightAt(world, x, z);

			if (world.getBiomeGenForCoords(new BlockPos(posX, posY, posZ)) == UBiome.infestedBiomBase) {
				new SchematicInfestedRuin1().generate(world, posX, posY, posZ);
				System.out.println("Schematic generiert: " + posX + " " + posY + " " + posZ);
			}
		}
	}*/

}
