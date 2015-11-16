package net.hycrafthd.umod;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraft.item.ItemStack;

public class URecipes {

	public URecipes() {
		registerCraftingRecipes();
		registerFurnaceRecipes();
	}

	private void registerCraftingRecipes() {
		// Ingots (Sulphur Chunk) -> Blocks
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			CommonRegistryUtils.registerShapedCraftingRecipe(new ItemStack(UBlocks.blocks, 1, i), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(UItems.ingots, 1, i) });
		}

		// Blocks -> Ingots (Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			CommonRegistryUtils.registerShaplessCraftingRecipe(new ItemStack(UItems.ingots, 9, i), new ItemStack(UBlocks.blocks, 1, i));
		}

	}

	private void registerFurnaceRecipes() {
		// Ores -> Ingots (without Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			if (EnumTypeBaseStuff.byMetadata(i).getName() != "sulphur") {
				CommonRegistryUtils.registerSmelting(new ItemStack(UBlocks.ores, 1, i), new ItemStack(UItems.ingots, 1, i), 0.7F);
			}
		}
	}

}
