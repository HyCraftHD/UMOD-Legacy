package net.hycrafthd.umod;

import net.hycrafthd.umod.item.tools.emerald.*;
import net.hycrafthd.umod.item.tools.magic.*;
import net.hycrafthd.umod.utils.URegistryUtils;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class UTools {
	
	public static ToolMaterial emeraldToolMaterial;
	public static ToolMaterial magicToolMaterial;
	
	public static Item emeraldAxe;
	public static Item emeraldPickaxe;
	public static Item emeraldSword;
	public static Item emeraldSpade;
	public static Item emeraldHoe;
	
	public static Item magicAxe;
	public static Item magicPickaxe;
	public static Item magicSword;
	public static Item magicShovel;
	public static Item magicHoe;
	
	public UTools() {
		init();
		register();
	}
	
	private void init() {
		emeraldToolMaterial = EnumHelper.addToolMaterial("EMERALDTOOLS", 3, 1000, 8.0F, 2.5F, 11);
		magicToolMaterial = EnumHelper.addToolMaterial("MAGICMATERIAL", 4, 300, 15.0F, 3.5F, 30);
		
		emeraldAxe = new ItemEmeraldAxe(emeraldToolMaterial).setUnlocalizedName("emerald_axe");
		emeraldPickaxe = new ItemEmeraldPickaxe(emeraldToolMaterial).setUnlocalizedName("emerald_pickaxe");
		emeraldSword = new ItemEmeraldSword(emeraldToolMaterial).setUnlocalizedName("emerald_sword");
		emeraldSpade = new ItemEmeraldSpade(emeraldToolMaterial).setUnlocalizedName("emerald_shovel");
		emeraldHoe = new ItemEmeraldHoe(emeraldToolMaterial).setUnlocalizedName("emerald_hoe");
		
		magicAxe = new ItemMagicAxe(magicToolMaterial).setUnlocalizedName("magic_axe");
		magicPickaxe = new ItemMagicPickaxe(magicToolMaterial).setUnlocalizedName("magic_pickaxe");
		magicSword = new ItemMagicSword(magicToolMaterial).setUnlocalizedName("magic_sword");
		magicHoe = new ItemMagicHoe(magicToolMaterial).setUnlocalizedName("magic_hoe");
		magicShovel = new ItemMagicShovel(magicToolMaterial).setUnlocalizedName("magic_shovel");

		UMod.log.debug("Init Tools");
	}
	
	private void register() {
		URegistryUtils.registerItem(emeraldAxe);
		URegistryUtils.registerItem(emeraldPickaxe);
		URegistryUtils.registerItem(emeraldSword);
		URegistryUtils.registerItem(emeraldSpade);
		URegistryUtils.registerItem(emeraldHoe);
		URegistryUtils.registerItem(magicAxe);
		URegistryUtils.registerItem(magicPickaxe);
		URegistryUtils.registerItem(magicSword);
		URegistryUtils.registerItem(magicShovel);
		URegistryUtils.registerItem(magicHoe);

		UMod.log.debug("Register Tools");
	}
	
}
