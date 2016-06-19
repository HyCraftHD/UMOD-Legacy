package net.hycrafthd.umod;

import net.hycrafthd.umod.item.ItemBackPack;
import net.hycrafthd.umod.item.ItemBattery;
import net.hycrafthd.umod.item.ItemCabletester;
import net.hycrafthd.umod.item.ItemDusts;
import net.hycrafthd.umod.item.ItemEnergyDisplay;
import net.hycrafthd.umod.item.ItemFundamental;
import net.hycrafthd.umod.item.ItemInfectedBeef;
import net.hycrafthd.umod.item.ItemInfectedCrop;
import net.hycrafthd.umod.item.ItemInfectedMilk;
import net.hycrafthd.umod.item.ItemIngots;
import net.hycrafthd.umod.item.ItemSwellPlacer;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraft.item.Item;

public class UItems {

	// Ingot
	public static Item ingots;

	// Dust
	public static Item dusts;

	// Cobble Dust
	public static Item cdust;

	// Infected
	public static Item infectedcrop;
	public static Item infectedleather;
	public static Item infectedbeef;
	public static Item infectedmilk;

	// Battery
	public static Item battery;
	public static Item tester;
    public static Item energydisplay;
	// Backpack
	public static Item backpack;

	public static Item railplacer;
	
	public UItems() {
		init();
		register();
		oredirectionary();
	}

	private void init() {
		// Ingot
		ingots = new ItemIngots().setUnlocalizedName("ingots");
		// Dust
		dusts = new ItemDusts().setUnlocalizedName("dusts");
		// Cobble Dust
		cdust = new ItemFundamental().setUnlocalizedName("cdust");
		// Infected
		infectedcrop = new ItemInfectedCrop().setUnlocalizedName("infectedcrop");
		infectedleather = new ItemFundamental().setUnlocalizedName("infectedleather");
		infectedbeef = new ItemInfectedBeef().setUnlocalizedName("infectedbeef");
		infectedmilk = new ItemInfectedMilk().setUnlocalizedName("infectedmilk");
		// Battery
		tester = new ItemCabletester().setUnlocalizedName("tester");
		battery = new ItemBattery().setUnlocalizedName("battery");
        energydisplay = new ItemEnergyDisplay().setUnlocalizedName("energy");
		
		// Backpack
		backpack = new ItemBackPack().setUnlocalizedName("backpack");
		
		railplacer = new ItemSwellPlacer().setUnlocalizedName("railpl");
		Logger.debug(UItems.class, "init()", "Init Items");
	}

	private void register() {
		// Ingot
		CommonRegistryUtils.registerItem(ingots);
		// Dust
		CommonRegistryUtils.registerItem(dusts);
		// Cobble Dust
		CommonRegistryUtils.registerItem(cdust);
		// Infected
		CommonRegistryUtils.registerItem(infectedcrop);
		CommonRegistryUtils.registerItem(infectedleather);
		CommonRegistryUtils.registerItem(infectedbeef);
		CommonRegistryUtils.registerItem(infectedmilk);
		// Battery
		CommonRegistryUtils.registerItem(tester);
		CommonRegistryUtils.registerItem(battery);
		CommonRegistryUtils.registerItem(energydisplay);
		// Backpack
		CommonRegistryUtils.registerItem(backpack);
		
		CommonRegistryUtils.registerItem(railplacer);
		Logger.debug(UItems.class, "register()", "Register Items");
	}

	private void oredirectionary() {
		// Ingot
		CommonRegistryUtils.registerOredirectionary(ingots);
		// Dust
		CommonRegistryUtils.registerOredirectionary(dusts);
		// Cobble Dust
		CommonRegistryUtils.registerOredirectionary(cdust);
		// Infected
		CommonRegistryUtils.registerOredirectionary(infectedcrop);
		CommonRegistryUtils.registerOredirectionary(infectedleather);
		CommonRegistryUtils.registerOredirectionary(infectedbeef);
		CommonRegistryUtils.registerOredirectionary(infectedmilk);
		// Battery
		CommonRegistryUtils.registerOredirectionary(tester);
		CommonRegistryUtils.registerOredirectionary(battery);
		CommonRegistryUtils.registerOredirectionary(energydisplay);
		// Backpack
		CommonRegistryUtils.registerOredirectionary(backpack);
		
		CommonRegistryUtils.registerOredirectionary(railplacer);
		Logger.debug(UItems.class, "oredirectionary()", "Oredirectionary");
	}

}
