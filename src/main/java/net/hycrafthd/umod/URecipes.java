package net.hycrafthd.umod;

import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.init.*;
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
			RecipeRegistry.registerShaped(new ItemStack(UBlocks.blocks, 1, i), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(UItems.ingots, 1, i) });
		}
		
		// Blocks -> Ingots (Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			RecipeRegistry.registerShapeless(new ItemStack(UItems.ingots, 9, i), new ItemStack(UBlocks.blocks, 1, i));
		}
		
		// Infeced
		RecipeRegistry.registerShaped(new ItemStack(UItems.acid), new Object[] { "FFF", "FFF", "FBF", 'F', new ItemStack(UItems.infectedcrop), 'B', new ItemStack(Items.glass_bottle) });
		RecipeRegistry.registerShapeless(new ItemStack(UItems.plastic, 2), new Object[] { new ItemStack(UItems.infectedmilk), new ItemStack(UItems.acid) });
		;
		
		// Battery
		RecipeRegistry.registerShaped(new ItemStack(UItems.copper_wire), new Object[] { "AAA", "AAA", "CCC", 'C', new ItemStack(UItems.ingots, 1, 1) });
		RecipeRegistry.registerShaped(new ItemStack(UItems.copper_coil), new Object[] { "CIC", "CIC", "AAA", 'C', new ItemStack(UItems.copper_wire), 'I', new ItemStack(Items.iron_ingot) });
		RecipeRegistry.registerShaped(new ItemStack(UItems.copper_coil), new Object[] { "AAA", "CIC", "CIC", 'C', new ItemStack(UItems.copper_wire), 'I', new ItemStack(Items.iron_ingot) });
		RecipeRegistry.registerShaped(new ItemStack(UItems.transformer), new Object[] { "SQS", "CQC", "SQS", 'S', new ItemStack(UBlocks.silver_cable), 'Q', new ItemStack(Blocks.quartz_block), 'C', new ItemStack(UItems.copper_coil) });
		RecipeRegistry.registerShaped(new ItemStack(UBlocks.charge), new Object[] { "OPO", "STS", "OPO", 'O', new ItemStack(Blocks.obsidian), 'P', new ItemStack(UItems.plastic), 'S', new ItemStack(UBlocks.silver_cable), 'T', new ItemStack(UItems.transformer) });
		RecipeRegistry.registerShaped(new ItemStack(UBlocks.alu_cable, 4), new Object[] { "PPP", "AAA", "PPP", 'A', new ItemStack(UItems.ingots, 1, 0), 'P', new ItemStack(UItems.plastic) });
		RecipeRegistry.registerShaped(new ItemStack(UBlocks.silver_cable, 4), new Object[] { "PPP", "AAA", "PPP", 'A', new ItemStack(UItems.ingots, 1, 8), 'P', new ItemStack(UItems.plastic) });
		RecipeRegistry.registerShaped(new ItemStack(UBlocks.zin_cable, 4), new Object[] { "PPP", "AAA", "PPP", 'A', new ItemStack(UItems.ingots, 1, 10), 'P', new ItemStack(UItems.plastic) });
		RecipeRegistry.registerShaped(new ItemStack(UItems.battery), new Object[] { "BCB", "IMI", "ASA", 'B', new ItemStack(Blocks.iron_block), 'C', new ItemStack(Blocks.coal_block), 'I', new ItemStack(Items.iron_ingot), 'M', new ItemStack(UItems.manganoxid), 'A', new ItemStack(UItems.ingots, 1, 0), 'S', new ItemStack(UItems.acid) });
		UMod.log.debug("registerCraftingRecipes");
		RecipeRegistry.registerShaped(new ItemStack(UItems.petrol), new Object[] { "SSS", "SSS", "SBS", 'S', new ItemStack(UBlocks.oilsand), 'B', new ItemStack(Items.glass_bottle) });
		
	}
	
	private void registerFurnaceRecipes() {
		
		// Ores -> Ingots (without Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			if (EnumTypeBaseStuff.byMetadata(i).getName() != "sulphur") {
				SmeltingRegistry.register(new ItemStack(UItems.ingots, 1, i), new ItemStack(UBlocks.ores, 1, i), 1);
			}
		}
		
		// Infeced
		SmeltingRegistry.register(new ItemStack(UItems.manganoxid), new ItemStack(UItems.dusts, 1, 3), 1);
		
		UMod.log.debug("registerFurnaceRecipes");
	}
	
	private void registerPulverizerRecipes() {
		// Ores -> Dust (without Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			int b = i + 1;
			if (i + 1 >= EnumTypeBaseStuff.values().length) {
				b = 0;
			}
			ModRegistryUtils.addPulverRiecepie(new PulverizerRecepie(new ItemStack(UBlocks.ores, 1, i), new ItemStack(UItems.dusts, 1, i), new ItemStack(UItems.dusts, 1, b), 5, true));
		}
		
		ModRegistryUtils.addPulverRiecepie(new PulverizerRecepie(new ItemStack(UBlocks.oilsand), new ItemStack(Blocks.sand), new ItemStack(UItems.petrol), 0, false));
		
		ModRegistryUtils.addCraftSmeltRecepieShapless(new CraftSmeltRecepieShapless(new ItemStack[] { new ItemStack(Items.iron_pickaxe) }, new ItemStack(Items.iron_ingot, 3)));
		UMod.log.debug("registerPulverizerRecipes");
		
	}
	
}
