package net.hycrafthd.umod.uschematic;

import net.hycrafthd.umod.utils.USchematicUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class USchematic {

	protected int width;
	protected int height;
	protected int length;
	protected int size;
	protected BlockObject[] blockObjects;

	public USchematic(String fileName) {
		try {
			NBTTagCompound nbtdata = USchematicUtils.getTagComoundForAssets(fileName);

			width = USchematicUtils.getWidth(nbtdata);
			height = USchematicUtils.getHeight(nbtdata);
			length = USchematicUtils.getLength(nbtdata);
			size = width * height * length;

			blockObjects = USchematicUtils.readSchematic(nbtdata);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generate(World world, int x, int y, int z) {
		for (BlockObject obj : blockObjects) {
			BlockPos pos = obj.getPositionWithOffset(x, y, z);
			world.setBlockState(pos, obj.getState());
			if (obj.getTileEntityData() != null && world.getTileEntity(pos) != null) {
				TileEntity tile = world.getTileEntity(pos);
				tile.readFromNBT(obj.getTileEntityData());
				tile.setPos(pos);
				world.setTileEntity(pos, tile);
			}
		}
	}

}