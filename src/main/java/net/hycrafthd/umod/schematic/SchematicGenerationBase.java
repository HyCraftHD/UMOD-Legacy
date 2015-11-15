package net.hycrafthd.umod.schematic;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public abstract class SchematicGenerationBase implements IWorldGenerator {

	public abstract void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider);

	protected int getWorldHeightAt(World world, int x, int z) {
		int height = 0;
		for (int i = (int) (world.getHorizon() - 20); i < 255; i++) {
			if (world.getBlockState(new BlockPos(x, i, z)).getBlock().isSolidFullCube()) {
				height = i;
			}
		}
		return height;
	}

}
