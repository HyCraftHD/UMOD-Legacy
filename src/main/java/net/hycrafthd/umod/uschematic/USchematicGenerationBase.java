package net.hycrafthd.umod.uschematic;

import java.util.Random;

import net.hycrafthd.umod.utils.GenerationUtils;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public abstract class USchematicGenerationBase implements IWorldGenerator {

	public abstract void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider);

	protected int getWorldHeightAt(World world, int x, int z) {
		return GenerationUtils.getWorldHeightAt(world, x, z);
	}

}
