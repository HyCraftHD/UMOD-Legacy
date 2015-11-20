package net.hycrafthd.umod.schematic;

import java.io.IOException;
import java.io.InputStream;

import net.hycrafthd.umod.utils.SchematicUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class Schematic {

	protected int width;
	protected int height;
	protected int length;
	protected int size;
	protected BlockObject[] blockObjects;

	public Schematic(String fileName) {
		try {
			NBTTagCompound nbtdata = SchematicUtils.getTagCompound(fileName);

			width = SchematicUtils.getWidth(nbtdata);
			height = SchematicUtils.getHeight(nbtdata);
			length = SchematicUtils.getLength(nbtdata);
			size = width * height * length;

			blockObjects = SchematicUtils.readSchematic(nbtdata);
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