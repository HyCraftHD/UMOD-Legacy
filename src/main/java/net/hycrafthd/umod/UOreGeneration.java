package net.hycrafthd.umod;

import java.util.Random;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class UOreGeneration implements IWorldGenerator {

	private void nether(Random random, int x, int y, World world) {
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.SULPHUR.getMetadata()), Blocks.netherrack, random, x, y, world, 10, 0, 128, 3, 14);
	}

	private void overworld(Random random, int x, int y, World world) {
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.ALUMINIUM.getMetadata()), random, x, y, world, 14, 0, 64, 2, 8);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.COPPER.getMetadata()), random, x, y, world, 14, 0, 64, 2, 8);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.LEAD.getMetadata()), random, x, y, world, 8, 0, 22, 2, 6);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.MANGAN.getMetadata()), random, x, y, world, 10, 0, 64, 2, 8);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.MERCURY.getMetadata()), random, x, y, world, 8, 0, 22, 2, 6);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.NICKEL.getMetadata()), random, x, y, world, 8, 0, 30, 2, 8);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.PLATINUM.getMetadata()), random, x, y, world, 4, 4, 22, 1, 8);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.SILICIUM.getMetadata()), random, x, y, world, 8, 0, 30, 2, 6);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.SILVER.getMetadata()), random, x, y, world, 10, 0, 30, 2, 6);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.TIN.getMetadata()), random, x, y, world, 14, 0, 64, 2, 8);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.URAN.getMetadata()), random, x, y, world, 4, 4, 22, 1, 4);
		gen(UBlocks.ores.getStateFromMeta(EnumTypeBaseStuff.ZINC.getMetadata()), random, x, y, world, 8, 0, 30, 2, 8);
	}

	private void end(Random random, int x, int y, World world) {

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

	private void gen(IBlockState state, Random random, int chunkx, int chunky, World world, int chance, int minY, int maxY, int minVienSize, int maxVienSize) {
		gen(state, Blocks.stone, random, chunkx, chunky, world, chance, minY, maxY, minVienSize, maxVienSize);
	}

	private void gen(IBlockState state, Block blockin, Random random, int chunkx, int chunky, World world, int chance, int minY, int maxY, int minVienSize, int maxVienSize) {

		int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int hightRange = maxY - minY;

		for (int i = 0; i < chance; i++) {
			int posX = chunkx + random.nextInt(16);
			int posY = random.nextInt(hightRange) + minY;
			int posZ = chunky + random.nextInt(16);
			new WorldGenMinable(state, vienSize, BlockHelper.forBlock(blockin)).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}

}
