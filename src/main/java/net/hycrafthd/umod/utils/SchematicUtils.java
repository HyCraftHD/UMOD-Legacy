package net.hycrafthd.umod.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.schematic.BlockObject;
import net.hycrafthd.umod.schematic.Schematic;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SchematicUtils {

	public static NBTTagCompound getTagCompound(String fileName) throws Exception {
		InputStream is = Schematic.class.getResourceAsStream("/assets/umod/uschematics/" + fileName + ".uschematic");
		NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);
		is.close();
		return nbtdata;
	}

	public static int getWidth(NBTTagCompound compound) throws Exception {
		return compound.getInteger("Width");
	}

	public static int getHeight(NBTTagCompound compound) throws Exception {
		return compound.getInteger("Height");
	}

	public static int getLength(NBTTagCompound compound) throws Exception {
		return compound.getInteger("Length");
	}

	public static BlockObject[] readSchematic(NBTTagCompound nbtdata) throws Exception {

		int width = nbtdata.getInteger("Width");
		int height = nbtdata.getInteger("Height");
		int length = nbtdata.getInteger("Length");
		int size = width * height * length;
		BlockObject[] blockObjects = new BlockObject[size];

		NBTTagList list = nbtdata.getTagList("Blocks", 10);

		int counter = 0;
		for (int posx = 0; posx < width; posx++) {
			for (int posy = 0; posy < height; posy++) {
				for (int posz = 0; posz < length; posz++) {
					BlockPos pos = new BlockPos(posx, posy, posz);
					NBTTagCompound nbt = list.getCompoundTagAt(counter);
					IBlockState state = ((Block) Block.blockRegistry.getObject(nbt.getString("Block"))).getStateFromMeta(nbt.getInteger("Metadata"));
					blockObjects[counter] = new BlockObject(pos, state);
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

		UMod.log.info("Schematic min point:" + min);
		UMod.log.info("Schematic max point:" + max);

		int counter = 0;
		for (int posx = min.getX(); posx < max.getX(); posx++) {
			for (int posy = min.getY(); posy < max.getY(); posy++) {
				for (int posz = min.getZ(); posz < max.getZ(); posz++) {
					BlockPos pos = new BlockPos(posx, posy, posz);
					IBlockState state = world.getBlockState(pos);
					Block block = state.getBlock();
					NBTTagCompound nbt = new NBTTagCompound();
					nbt.setString("Block", String.valueOf(Block.blockRegistry.getNameForObject(block)));
					nbt.setInteger("Metadata", block.getMetaFromState(state));
					list.appendTag(nbt);
					counter++;
				}
			}
		}

		NBTTagCompound nbtdata = new NBTTagCompound();

		nbtdata.setInteger("Width", Math.abs(max.getX() - min.getX()));
		nbtdata.setInteger("Height", Math.abs(max.getY() - min.getY()));
		nbtdata.setInteger("Length", Math.abs(max.getZ() - min.getZ()));

		nbtdata.setTag("Blocks", list);

		File maindir = world.getSaveHandler().getWorldDirectory().getParentFile().getParentFile();
		File schematics = new File(maindir, "uschematics/");
		schematics.mkdir();
		File save = new File(schematics, name + ".uschematic");

		OutputStream stream = new FileOutputStream(save);
		CompressedStreamTools.writeCompressed(nbtdata, stream);
		save.createNewFile();
	}

}
