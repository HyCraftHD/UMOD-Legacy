package net.hycrafthd.umod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class UUtils {
	
	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}
	
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemclass) {
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName().substring(5));
	}
	
	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
	
	public static void registerTextures(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(UReference.modid + ":items/armor/" + item.getUnlocalizedName(), "inventory"));
	}
	
	public static void registerOredirectionary(Block block) {
		OreDictionary.registerOre(block.getUnlocalizedName().substring(5), block);
	}
	
	public static void registerOredirectionary(Item item) {
		OreDictionary.registerOre(item.getUnlocalizedName().substring(5), item);
	}
	
}
