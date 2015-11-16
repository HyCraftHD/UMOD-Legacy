package net.hycrafthd.umod;

import net.hycrafthd.umod.armor.ArmorRadiation;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class UArmor {

	// Radiation Suit
	public static ArmorMaterial radiationSuitMaterial;

	public static Item radiationSuitHelmet;
	public static Item radiationSuitChestplate;
	public static Item radiationSuitLeggings;
	public static Item radiationSuitBoots;

	public UArmor() {
		init();
		register();
	}

	private void init() {
		// Radiation Suit
		radiationSuitMaterial = EnumHelper.addArmorMaterial("RADIATIONARMOR", "", 10, new int[] { 1, 3, 1, 1 }, 1);

		radiationSuitHelmet = new ArmorRadiation(radiationSuitMaterial, 0).setUnlocalizedName("radiationsuithelmet");
		radiationSuitChestplate = new ArmorRadiation(radiationSuitMaterial, 1).setUnlocalizedName("radiationsuitchestplate");
		radiationSuitLeggings = new ArmorRadiation(radiationSuitMaterial, 2).setUnlocalizedName("radiationsuitleggings");
		radiationSuitBoots = new ArmorRadiation(radiationSuitMaterial, 3).setUnlocalizedName("radiationsuitboots");
	}

	private void register() {
		// Radiation Suit
		CommonRegistryUtils.registerItem(radiationSuitHelmet);
		CommonRegistryUtils.registerItem(radiationSuitChestplate);
		CommonRegistryUtils.registerItem(radiationSuitLeggings);
		CommonRegistryUtils.registerItem(radiationSuitBoots);
	}

}
