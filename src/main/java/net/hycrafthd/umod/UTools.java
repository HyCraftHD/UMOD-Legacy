package net.hycrafthd.umod;

import net.hycrafthd.umod.item.tools.emerald.ItemEmeraldAxe;
import net.hycrafthd.umod.item.tools.emerald.ItemEmeraldHoe;
import net.hycrafthd.umod.item.tools.emerald.ItemEmeraldPickaxe;
import net.hycrafthd.umod.item.tools.emerald.ItemEmeraldSpade;
import net.hycrafthd.umod.item.tools.emerald.ItemEmeraldSword;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
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
		
		emeraldAxe = new ItemEmeraldAxe(emeraldToolMaterial).setUnlocalizedName("emeraldaxe");
		emeraldPickaxe = new ItemEmeraldPickaxe(emeraldToolMaterial).setUnlocalizedName("emeraldpickaxe");
		emeraldSword = new ItemEmeraldSword(emeraldToolMaterial).setUnlocalizedName("emeraldsword");
		emeraldSpade = new ItemEmeraldSpade(emeraldToolMaterial).setUnlocalizedName("emeraldspade");
		emeraldHoe = new ItemEmeraldHoe(emeraldToolMaterial).setUnlocalizedName("emeraldhoe");
		Logger.debug(UTools.class, "init()", "Init Tools");
	}

	private void register() {
		CommonRegistryUtils.registerItem(emeraldAxe);
		CommonRegistryUtils.registerItem(emeraldPickaxe);
		CommonRegistryUtils.registerItem(emeraldSword);
		CommonRegistryUtils.registerItem(emeraldSpade);
		CommonRegistryUtils.registerItem(emeraldHoe);
		Logger.debug(UTools.class, "register()", "Register Tools");
	}
	
}
