package net.hycrafthd.umod.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.hycrafthd.umod.uschematic.BlockObject;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class USchematicUtils {

	public static File getSaveDirectionary() {
		return new File(System.getProperty("user.dir"), "uschematic/");
	}

	public static String[] getFileNameList() throws Exception {
		return FileUtils.getFileNamesInDirectionary(USchematicUtils.getSaveDirectionary(), ".uschematic", "");
	}

	public static NBTTagCompound getTagCompound(String name) throws Exception {
		File file = new File(USchematicUtils.getSaveDirectionary(), name + ".uschematic");
		if (file.exists()) {
			InputStream stream = new FileInputStream(file);
			NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(stream);
			stream.close();
			return nbtdata;
		} else {
			return null;
		}
	}

	public static NBTTagCompound getTagComoundForAssets(String name) throws Exception {
		InputStream stream = USchematicUtils.class.getResourceAsStream("/assets/umod/uschematic/" + name + ".uschematic");
		NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(stream);
		stream.close();
		return nbtdata;
	}

	public static int getWidth(NBTTagCompound compound) throws Exception {
		return compound.getInteger("width");
	}

	public static int getHeight(NBTTagCompound compound) throws Exception {
		return compound.getInteger("height");
	}

	public static int getLength(NBTTagCompound compound) throws Exception {
		return compound.getInteger("length");
	}

	public static BlockObject[] readSchematic(NBTTagCompound nbtdata) throws Exception {

		int width = nbtdata.getInteger("width");
		int height = nbtdata.getInteger("height");
		int length = nbtdata.getInteger("length");
		int size = width * height * length;
		BlockObject[] blockObjects = new BlockObject[size];

		NBTTagList list = nbtdata.getTagList("blocks", 10);

		int counter = 0;
		for (int posx = 0; posx < width; posx++) {
			for (int posy = 0; posy < height; posy++) {
				for (int posz = 0; posz < length; posz++) {
					BlockPos pos = new BlockPos(posx, posy, posz);
					NBTTagCompound nbt = list.getCompoundTagAt(counter);
					IBlockState state = ((Block) Block.blockRegistry.getObject(nbt.getString("block"))).getStateFromMeta(nbt.getInteger("meta"));
					
					if (nbt.hasKey("tile")) {
						blockObjects[counter] = new BlockObject(pos, state, nbt.getCompoundTag("tile"));
					} else {
						blockObjects[counter] = new BlockObject(pos, state, null);
					}

					counter++;
				}
			}
		}

		return blockObjects;

	}

	public static void saveSchematic(World world, String name, int x, int y, int z, int x1, int y1, int z1) throws Exception {

		BlockPos min = new BlockPos(Math.min(x, x1), Math.min(y, y1), Math.min(z, z1));
		BlockPos max = new BlockPos(Math.max(x, x1) + 1, Math.max(y, y1) + 1, Math.max(z, z1) + 1);

		NBTTagList list = new NBTTagList();

		int counter = 0;
		for (int posx = min.getX(); posx < max.getX(); posx++) {
			for (int posy = min.getY(); posy < max.getY(); posy++) {
				for (int posz = min.getZ(); posz < max.getZ(); posz++) {
					BlockPos pos = new BlockPos(posx, posy, posz);
					IBlockState state = world.getBlockState(pos);
					Block block = state.getBlock();
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setString("block", String.valueOf(Block.blockRegistry.getNameForObject(block)));
					nbt.setInteger("meta", block.getMetaFromState(state));
					if (world.getTileEntity(pos) != null) {
						TileEntity tile = world.getTileEntity(pos);
						NBTTagCompound tilenbt = new NBTTagCompound();
						tile.writeToNBT(tilenbt);
						tilenbt.removeTag("x");
						tilenbt.removeTag("y");
						tilenbt.removeTag("z");
						nbt.setTag("tile", tilenbt);
					}
					list.appendTag(nbt);
					counter++;
				}
			}
		}

		NBTTagCompound nbtdata = new NBTTagCompound();

		nbtdata.setInteger("width", Math.abs(max.getX() - min.getX()));
		nbtdata.setInteger("height", Math.abs(max.getY() - min.getY()));
		nbtdata.setInteger("length", Math.abs(max.getZ() - min.getZ()));

		nbtdata.setTag("blocks", list);

		USchematicUtils.getSaveDirectionary().mkdir();

		File file = new File(USchematicUtils.getSaveDirectionary(), name + ".uschematic");

		OutputStream stream = new FileOutputStream(file);

		CompressedStreamTools.writeCompressed(nbtdata, stream);

		stream.close();
		file.createNewFile();

	}

}
