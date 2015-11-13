package net.hycrafthd.umod;

import net.hycrafthd.umod.armor.RadiationArmor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class UArmor {

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
		radiationSuitMaterial = EnumHelper.addArmorMaterial("RADIATIONARMOR", "", 5, new int[] {1,3,1,1}, 1);
		
		radiationSuitHelmet = new RadiationArmor(radiationSuitMaterial, 0).setUnlocalizedName("radiationsuithelmet");
		radiationSuitChestplate = new RadiationArmor(radiationSuitMaterial, 1).setUnlocalizedName("radiationsuitchestplate");
		radiationSuitLeggings = new RadiationArmor(radiationSuitMaterial, 2).setUnlocalizedName("radiationsuitleggings");
		radiationSuitBoots = new RadiationArmor(radiationSuitMaterial, 3).setUnlocalizedName("radiationsuitboots");
	}

	private void register() {
		UUtils.registerItem(radiationSuitHelmet);
		UUtils.registerItem(radiationSuitChestplate);
		UUtils.registerItem(radiationSuitLeggings);
		UUtils.registerItem(radiationSuitBoots);
	}
	
}
