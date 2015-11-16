package net.hycrafthd.umod;

import net.hycrafthd.umod.api.IPowerProvieder;
import net.hycrafthd.umod.item.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class UUtils {

	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, ItemBlockBase.class, block.getUnlocalizedName().substring(5));
	}

	public static void registerBlock(Block block, Class<? extends ItemBlockBase> itemclass) {
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName().substring(5));
	}
	
	public static void registerBlocks(Block block, Class<? extends ItemBlock> itemclass) {
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName().substring(5));
	}

	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public static void registerOredirectionary(Block block) {
		OreDictionary.registerOre(block.getUnlocalizedName().substring(5), block);
	}

	public static void registerOredirectionary(Item item) {
		OreDictionary.registerOre(item.getUnlocalizedName().substring(5), item);
	}
	
	public static IPowerProvieder getNeighbourPowerProvider(BlockPos p,World w){
		if(w.getTileEntity(p.east()) instanceof IPowerProvieder){
			return (IPowerProvieder) w.getTileEntity(p.east()); 
		}
		if(w.getTileEntity(p.south()) instanceof IPowerProvieder){
			return (IPowerProvieder) w.getTileEntity(p.south()); 
		}
		if(w.getTileEntity(p.north()) instanceof IPowerProvieder){
			return (IPowerProvieder) w.getTileEntity(p.north()); 
		}
		if(w.getTileEntity(p.west()) instanceof IPowerProvieder){
			return (IPowerProvieder) w.getTileEntity(p.west()); 
		}
		if(w.getTileEntity(p.up()) instanceof IPowerProvieder){
			return (IPowerProvieder) w.getTileEntity(p.up()); 
		}
		if(w.getTileEntity(p.down()) instanceof IPowerProvieder){
			return (IPowerProvieder) w.getTileEntity(p.down()); 
		}
		return null;
	}
	
	public static EnumFacing getDirectory(BlockPos pos1,BlockPos pos2){
		if(pos1.getY() > pos2.getY()){
			return EnumFacing.DOWN;
		}else if(pos1.getY() < pos2.getY()){
			return EnumFacing.UP;
		}
		if(pos1.getX() > pos2.getX()){
			return EnumFacing.EAST;
		}else if(pos1.getX() < pos2.getX()){
			return EnumFacing.WEST;
		}
		if(pos1.getZ() > pos2.getZ()){
			return EnumFacing.SOUTH;
		}else if(pos1.getZ() < pos2.getZ()){
			return EnumFacing.NORTH;
		}
		return EnumFacing.EAST;
	}
	
	public static int inUE(int ip){
		return ip*5; 
	}

	public static String getBlockName(Block o) {
		return o.getUnlocalizedName().replace("tile.", "");
	}

}
