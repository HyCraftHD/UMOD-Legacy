package net.hycrafthd.umod;

import net.hycrafthd.umod.api.CraftSmeltRecepieShapless;
import net.hycrafthd.umod.api.PulverizerRecepie;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class URecipes {

	public URecipes() {
		registerCraftingRecipes();
		registerFurnaceRecipes();
		registerPulverizerRecipes();
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
	
	private void registerPulverizerRecipes() {
		// Ores -> Dust (without Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
				int b = i + 1;
				if(i + 1 >= EnumTypeBaseStuff.values().length){
					b = 0;
				}
				ModRegistryUtils.addPulverRiecepie(new PulverizerRecepie(new ItemStack(UBlocks.ores, 1, i), new ItemStack(UItems.dusts, 1, i), new ItemStack(UItems.dusts, 1, b)));
		}
		
		ModRegistryUtils.addCraftSmeltRecepieShapless(new CraftSmeltRecepieShapless(new ItemStack[] {new ItemStack(Items.iron_pickaxe)}, new ItemStack(Items.iron_ingot, 3)));
	}

}
