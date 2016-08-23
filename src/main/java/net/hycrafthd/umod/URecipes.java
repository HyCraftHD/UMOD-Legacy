package net.hycrafthd.umod;

import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.init.*;
import net.minecraft.item.ItemStack;

public class URecipes {
	
	public URecipes() {
		registerMagicCrafterRecipes();
		registerCraftingRecipes();
		registerFurnaceRecipes();
		registerPulverizerRecipes();
	}
	
	private void registerMagicCrafterRecipes(){
		
		//magic brew
		ModRegistryUtils.addMagicCrafterRecipe(new MagicCrafterRecipe(0, new ItemStack(Items.potionitem, 1, 8227), 
				new ItemStack(UItems.acid), new ItemStack(UItems.magic_brew)));
		//magic ingot
		ModRegistryUtils.addMagicCrafterRecipe(new MagicCrafterRecipe(0, new ItemStack(Items.iron_ingot), 
				new ItemStack(UItems.magic_brew), new ItemStack(UItems.magic_ingot)));
		//magic quartz
		ModRegistryUtils.addMagicCrafterRecipe(new MagicCrafterRecipe(0, new ItemStack(Items.quartz), 
				new ItemStack(UItems.magic_brew), new ItemStack(UItems.charged_quartz)));
		//magic glass
		ModRegistryUtils.addMagicCrafterRecipe(new MagicCrafterRecipe(0, new ItemStack(UBlocks.oilglass), 
				new ItemStack(UItems.charged_quartz), new ItemStack(UBlocks.magic_glass)));
		//magic bottle
		ModRegistryUtils.addMagicCrafterRecipe(new MagicCrafterRecipe(0, new ItemStack(Items.glass_bottle), 
				new ItemStack(UBlocks.magic_glass), new ItemStack(UItems.magic_bottle)));
		
	}
	
	private void registerCraftingRecipes() {
		
		//magic armor and tools
		RecipeRegistry.registerShaped(new ItemStack(UArmor.magicBoots), new Object[] {"AAA", "MAM", "MAM", 'M', new ItemStack(UItems.magic_ingot)});
		RecipeRegistry.registerShaped(new ItemStack(UArmor.magicChestplate), new Object[] {"MAM", "MMM", "MMM", 'M', new ItemStack(UItems.magic_ingot)});
		RecipeRegistry.registerShaped(new ItemStack(UArmor.magicHelmet), new Object[] {"MMM", "MAM", "AAA", 'M', new ItemStack(UItems.magic_ingot)});
		RecipeRegistry.registerShaped(new ItemStack(UArmor.magicLeggings), new Object[] {"MMM", "MAM", "MAM", 'M', new ItemStack(UItems.magic_ingot)});
		
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicAxe), new Object[]{"MMA", "MWA", "AWA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicAxe), new Object[]{"AMM", "MWA", "AWA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicPickaxe), new Object[]{"MMM", "AWA", "AWA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicShovel), new Object[]{"AMA", "AWA", "AWA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicShovel), new Object[]{"MAA", "WAA", "WAA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicShovel), new Object[]{"AAM", "AAW", "AAW", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicSword), new Object[]{"AMA", "AMA", "AWA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicSword), new Object[]{"AAM", "AAM", "AAW", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicSword), new Object[]{"MAA", "MAA", "WAA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicHoe), new Object[]{"MMA", "AWA", "AWA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});
		RecipeRegistry.registerShaped(new ItemStack(UTools.magicHoe), new Object[]{"AMM", "AWA", "AWA", 'M', new ItemStack(UItems.magic_ingot), 'W', new ItemStack(Items.stick)});

		// Ingots (Sulphur Chunk) -> Blocks
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			RecipeRegistry.registerShaped(new ItemStack(UBlocks.blocks, 1, i), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(UItems.ingots, 1, i) });
		}
		
		// Blocks -> Ingots (Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			RecipeRegistry.registerShapeless(new ItemStack(UItems.ingots, 9, i), new ItemStack(UBlocks.blocks, 1, i));
		}
		
		// Infeced
		RecipeRegistry.registerShaped(new ItemStack(UItems.acid), new Object[] { "FFF", "FFF", "FBF", 'F', new ItemStack(UItems.infectedcrop), 'B', new ItemStack(UItems.magic_bottle) });
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
		RecipeRegistry.registerShaped(new ItemStack(UBlocks.lowvoltage_cable, 4), new Object[] { "PPP", "AAA", "PPP", 'A', new ItemStack(UItems.ingots, 1, 10), 'P', new ItemStack(UItems.plastic) });
		RecipeRegistry.registerShaped(new ItemStack(UItems.battery), new Object[] { "BCB", "IMI", "ASA", 'B', new ItemStack(Blocks.iron_block), 'C', new ItemStack(Blocks.coal_block), 'I', new ItemStack(Items.iron_ingot), 'M', new ItemStack(UItems.manganoxid), 'A', new ItemStack(UItems.ingots, 1, 0), 'S', new ItemStack(UItems.acid) });
		UMod.log.debug("registerCraftingRecipes");
		RecipeRegistry.registerShaped(new ItemStack(UItems.petrol), new Object[] { "SSS", "SSS", "SBS", 'S', new ItemStack(UBlocks.oilsand), 'B', new ItemStack(UItems.magic_bottle) });
		
		//stairs
//		for (int i = 0; i < BlockStone.EnumType.values().length; i++) {
//			//UBlocks.stonestairs[i] = new BlockStoneStairs(BlockStone.EnumType.byMetadata(i));
//			RecipeRegistry.registerShaped(new ItemStack(UBlocks.stonestairs[i]), new Object[]{"BAA", "BBA", "BBB", 'B', 
//				new ItemStack(BlockStone.EnumType.byMetadata(i))}); //dERROR
//		}		
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
