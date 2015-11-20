package net.hycrafthd.umod.schematic;

import java.io.InputStream;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class Schematic {

	protected short width;
	protected short height;
	protected short length;
	protected int size;
	protected BlockObject[] blockObjects;

	public Schematic(String fileName) {
		try {
			InputStream is = Schematic.class.getResourceAsStream("/assets/umod/schematics/" + fileName + ".schematic");
			NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);

			is.close();

			width = nbtdata.getShort("Width");
			height = nbtdata.getShort("Height");
			length = nbtdata.getShort("Length");
			size = width * height * length;
			blockObjects = new BlockObject[size];

			byte[] blockIDs = nbtdata.getByteArray("Blocks");
			byte[] metadata = nbtdata.getByteArray("Data");

			int counter = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < length; j++) {
					for (int k = 0; k < width; k++) {
						BlockPos pos = new BlockPos(k, i, j);
						IBlockState state = Block.getBlockById(blockIDs[counter]).getStateFromMeta(metadata[counter]);
						blockObjects[counter] = new BlockObject(pos, state);
						counter++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generate(World world, int x, int y, int z) {

		for (BlockObject obj : blockObjects) {
			BlockPos pos = obj.getPositionWithOffset(x, y, z);
			world.setBlockState(pos, obj.getState());

		}
	}

}