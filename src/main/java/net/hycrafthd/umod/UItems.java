package net.hycrafthd.umod;

import net.hycrafthd.corelib.registry.OreDictionaryRegistry;
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
import net.hycrafthd.umod.utils.Utils;
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
		UMod.log.debug("Init Items");
	}

	private void register() {
		// Ingot
		Utils.registerItem(ingots);
		// Dust
		Utils.registerItem(dusts);
		// Cobble Dust
		Utils.registerItem(cdust);
		// Infected
		Utils.registerItem(infectedcrop);
		Utils.registerItem(infectedleather);
		Utils.registerItem(infectedbeef);
		Utils.registerItem(infectedmilk);
		// Battery
		Utils.registerItem(tester);
		Utils.registerItem(battery);
		Utils.registerItem(energydisplay);
		// Backpack
		Utils.registerItem(backpack);
		
		Utils.registerItem(railplacer);
		UMod.log.debug("Register Items");
	}

	private void oredirectionary() {
		// Ingot
		OreDictionaryRegistry.register(ingots);
		// Dust
		OreDictionaryRegistry.register(dusts);
		// Cobble Dust
		OreDictionaryRegistry.register(cdust);
		// Infected
		OreDictionaryRegistry.register(infectedcrop);
		OreDictionaryRegistry.register(infectedleather);
		OreDictionaryRegistry.register(infectedbeef);
		OreDictionaryRegistry.register(infectedmilk);
		// Battery
		OreDictionaryRegistry.register(tester);
		OreDictionaryRegistry.register(battery);
		OreDictionaryRegistry.register(energydisplay);
		// Backpack
		OreDictionaryRegistry.register(backpack);
		
		OreDictionaryRegistry.register(railplacer);
		UMod.log.debug("Oredirectionary");
	}

}
