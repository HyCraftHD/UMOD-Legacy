package net.hycrafthd.umod.utils;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.item.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonRegistryUtils {

	// Blocks
	public static void registerBlock(Block block) {
		GameRegistry.registerBlock(block, ItemBlockBase.class, block.getUnlocalizedName().substring(5));
	}

	public static void registerBlock(Block block, Class<? extends ItemBlockBase> itemclass) {
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName().substring(5));
	}

	public static void registerBlocks(Block block, Class<? extends ItemBlock> itemclass) {
		GameRegistry.registerBlock(block, itemclass, block.getUnlocalizedName().substring(5));
	}

	// Crafting
	public static void registerCraftingRecipe(ItemStack output, Object... obj) {
		GameRegistry.addRecipe(output, obj);
	}

	public static void registerShapedCraftingRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapedRecipe(output, obj);
	}

	public static void registerShaplessCraftingRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapelessRecipe(output, obj);
	}

	// Entity
	public static void registerEntity(Class entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int solidColor, int spotColor, boolean hasSpawnEgg) {
		int id = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
		EntityRegistry.registerModEntity(entityClass, entityName, id, UReference.instance, trackingRange, updateFrequency, sendsVelocityUpdates);

		if (hasSpawnEgg) {
			EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, solidColor, spotColor));
		}
	}

	// Gui Handler
	public static void registerGuiHandler(IGuiHandler handler) {
		NetworkRegistry.INSTANCE.registerGuiHandler(UReference.instance, handler);
	}

	// Items
	public static void registerItem(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	// Oredirectionary
	public static void registerOredirectionary(Block block) {
		OreDictionary.registerOre(block.getUnlocalizedName().substring(5), block);
	}

	public static void registerOredirectionary(Item item) {
		OreDictionary.registerOre(item.getUnlocalizedName().substring(5), item);
	}

	// Smelting
	public static void registerSmelting(Block input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void registerSmelting(Item input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	public static void registerSmelting(ItemStack input, ItemStack output, float xp) {
		GameRegistry.addSmelting(input, output, xp);
	}

	// TileEntity
	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}

}
