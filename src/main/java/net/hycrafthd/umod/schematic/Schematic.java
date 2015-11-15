package net.hycrafthd.umod.schematic;

import java.io.InputStream;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Schematic {
	
	private short width;
	private short height;
	private short length;
	private int size;
	private BlockObject[] blockObjects;
	
	public Schematic(String fileName) {
		try {
			InputStream is = Schematic.class.getResourceAsStream("/assets/umod/schematics/" + fileName);
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
		int blocks = width*length;
		for (BlockObject obj : blockObjects) {
			BlockPos pos = obj.getPositionWithOffset(x, y, z);
			world.setBlockState(pos, obj.getState());
			if(blocks != 0){
			pos = pos.add(0, -1, 0);
				for(int posy = pos.getY(); posy>0; posy--){
					IBlockState stats = world.getBlockState(pos);
					Block block = stats.getBlock();
					if(!block.isSolidFullCube()){
						IBlockState state = Blocks.cobblestone.getDefaultState();
						int ran = new Random().nextInt(7);
						if(ran == 0 || ran == 5){
							state = Blocks.mossy_cobblestone.getDefaultState();
						}
						world.setBlockState(pos, state);
						pos = pos.add(0, -1, 0);
					}
				}
				blocks--;
			}
		}
	}
	
}