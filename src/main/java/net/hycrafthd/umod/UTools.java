package net.hycrafthd.umod;

import net.hycrafthd.umod.item.tools.emerald.*;
import net.hycrafthd.umod.utils.URegistryUtils;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class UTools {
	
	public static ToolMaterial emeraldToolMaterial;
	
	public static Item emeraldAxe;
	public static Item emeraldPickaxe;
	public static Item emeraldSword;
	public static Item emeraldSpade;
	public static Item emeraldHoe;
	
	public UTools() {
		init();
		register();
	}
	
	private void init() {
		emeraldToolMaterial = EnumHelper.addToolMaterial("EMERALDTOOLS", 3, 1000, 8.0F, 2.5F, 11);
		
		emeraldAxe = new ItemEmeraldAxe(emeraldToolMaterial).setUnlocalizedName("emerald_axe");
		emeraldPickaxe = new ItemEmeraldPickaxe(emeraldToolMaterial).setUnlocalizedName("emerald_pickaxe");
		emeraldSword = new ItemEmeraldSword(emeraldToolMaterial).setUnlocalizedName("emerald_sword");
		emeraldSpade = new ItemEmeraldSpade(emeraldToolMaterial).setUnlocalizedName("emerald_shovel");
		emeraldHoe = new ItemEmeraldHoe(emeraldToolMaterial).setUnlocalizedName("emerald_hoe");
		UMod.log.debug("Init Tools");
	}
	
	private void register() {
		URegistryUtils.registerItem(emeraldAxe);
		URegistryUtils.registerItem(emeraldPickaxe);
		URegistryUtils.registerItem(emeraldSword);
		URegistryUtils.registerItem(emeraldSpade);
		URegistryUtils.registerItem(emeraldHoe);
		UMod.log.debug("Register Tools");
	}
	
}
