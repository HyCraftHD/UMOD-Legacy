package net.hycrafthd.umod;

import net.hycrafthd.umod.armor.*;
import net.hycrafthd.umod.item.tools.energy.ItemEnergyGlasses;
import net.hycrafthd.umod.utils.URegistryUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class UArmor {
	
	// Radiation Suit
	public static ArmorMaterial radiationSuitMaterial;
	// Energy
	public static ArmorMaterial enrgy;
	// Emerald
	public static ArmorMaterial emeraldMaterial;
	
	// Radiation Suit
	public static Item radiationSuitHelmet;
	public static Item radiationSuitChestplate;
	public static Item radiationSuitLeggings;
	public static Item radiationSuitBoots;
	// Energy
	public static Item energyglasses;
	// Emerald
	public static Item emeraldHelmet;
	public static Item emeraldChestplate;
	public static Item emeraldLeggings;
	public static Item emeraldBoots;
	
	public UArmor() {
		init();
		register();
	}
	
	private void init() {
		// Radiation Suit
		radiationSuitMaterial = EnumHelper.addArmorMaterial("RADIATIONARMOR", "", 10, new int[] { 1, 3, 1, 1 }, 1);
		// Energy
		enrgy = EnumHelper.addArmorMaterial("Energy", "", 5, new int[] { 1, 3, 1, 1 }, 1);
		// Emerald
		emeraldMaterial = EnumHelper.addArmorMaterial("EMERALDARMOR", "", 28, new int[] { 3, 7, 5, 3 }, 20);
		
		// Radiation Suit
		radiationSuitHelmet = new ArmorRadiation(radiationSuitMaterial, 0).setUnlocalizedName("radiationsuithelmet");
		radiationSuitChestplate = new ArmorRadiation(radiationSuitMaterial, 1).setUnlocalizedName("radiationsuitchestplate");
		radiationSuitLeggings = new ArmorRadiation(radiationSuitMaterial, 2).setUnlocalizedName("radiationsuitleggings");
		radiationSuitBoots = new ArmorRadiation(radiationSuitMaterial, 3).setUnlocalizedName("radiationsuitboots");
		
		// Energy
		energyglasses = new ItemEnergyGlasses(enrgy).setUnlocalizedName("energyglasses");
		
		// Emerald
		emeraldHelmet = new ArmorEmerald(emeraldMaterial, 0).setUnlocalizedName("emeraldhelmet");
		emeraldChestplate = new ArmorEmerald(emeraldMaterial, 1).setUnlocalizedName("emeraldchestplate");
		emeraldLeggings = new ArmorEmerald(emeraldMaterial, 2).setUnlocalizedName("emeraldleggings");
		emeraldBoots = new ArmorEmerald(emeraldMaterial, 3).setUnlocalizedName("emeraldboots");
		
		UMod.log.debug("Init Armor");
	}
	
	private void register() {
		// Radiation Suit
		URegistryUtils.registerItem(radiationSuitHelmet);
		URegistryUtils.registerItem(radiationSuitChestplate);
		URegistryUtils.registerItem(radiationSuitLeggings);
		URegistryUtils.registerItem(radiationSuitBoots);
		
		// Energy
		URegistryUtils.registerItem(energyglasses);
		
		// Emerald
		URegistryUtils.registerItem(emeraldHelmet);
		URegistryUtils.registerItem(emeraldChestplate);
		URegistryUtils.registerItem(emeraldLeggings);
		URegistryUtils.registerItem(emeraldBoots);
		
		UMod.log.debug("Register Armor");
	}
	
}
