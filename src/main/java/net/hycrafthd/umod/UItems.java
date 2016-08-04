package net.hycrafthd.umod;

import net.hycrafthd.corelib.registry.OreDictionaryRegistry;
import net.hycrafthd.umod.item.*;
import net.hycrafthd.umod.utils.Utils;
import net.minecraft.item.Item;

public class UItems {
	
	// Ingot
	public static Item ingots;
	
	// Dust
	public static Item dusts;
	public static Item manganoxid;
	
	// Cobble Dust
	public static Item cdust;
	
	// Infected
	public static Item infectedcrop;
	public static Item infectedleather;
	public static Item infectedbeef;
	public static Item infectedmilk;
	public static Item acid;
	public static Item plastic;
	
	// Battery
	public static Item battery;
	public static Item tester;
	public static Item energydisplay;
	public static Item copper_coil;
	public static Item copper_wire;
	public static Item transformer;
	public static Item thicker_copper_wire;
	public static Item thicker_copper_coil;
	public static Item solar_cell;
	public static Item petrol;
	
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
		manganoxid = new ItemManganOxid().setUnlocalizedName("manganoxid");
		
		// Cobble Dust
		cdust = new ItemFundamental().setUnlocalizedName("cdust");
		
		// Infected
		infectedcrop = new ItemInfectedCrop().setUnlocalizedName("infectedcrop");
		infectedleather = new ItemFundamental().setUnlocalizedName("infectedleather");
		infectedbeef = new ItemInfectedBeef().setUnlocalizedName("infectedbeef");
		infectedmilk = new ItemInfectedMilk().setUnlocalizedName("infectedmilk");
		acid = new ItemAcid().setUnlocalizedName("acid");
		plastic = new ItemPlastic().setUnlocalizedName("plastic");
		
		// Battery
		tester = new ItemCabletester().setUnlocalizedName("tester");
		battery = new ItemBattery().setUnlocalizedName("battery");
		energydisplay = new ItemEnergyDisplay().setUnlocalizedName("energy");
		copper_wire = new ItemCopperWire().setUnlocalizedName("copper_wire");
		copper_coil = new ItemCopperCoil().setUnlocalizedName("copper_coil");
		transformer = new ItemTransformer().setUnlocalizedName("transformer");
		thicker_copper_wire = new ItemThickerCopperWire().setUnlocalizedName("thicker_copper_wire");
		solar_cell = new ItemSolarCell().setUnlocalizedName("solar_cell");
		thicker_copper_coil = new ItemThickerCopperCoil().setUnlocalizedName("thicker_copper_coil");
		petrol = new ItemPetrol().setUnlocalizedName("petrol");
		
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
		Utils.registerItem(manganoxid);
		
		// Cobble Dust
		Utils.registerItem(cdust);
		
		// Infected
		Utils.registerItem(infectedcrop);
		Utils.registerItem(infectedleather);
		Utils.registerItem(infectedbeef);
		Utils.registerItem(infectedmilk);
		Utils.registerItem(acid);
		Utils.registerItem(plastic);
		
		// Battery
		Utils.registerItem(tester);
		Utils.registerItem(battery);
		Utils.registerItem(energydisplay);
		Utils.registerItem(copper_wire);
		Utils.registerItem(copper_coil);
		Utils.registerItem(transformer);
		Utils.registerItem(thicker_copper_wire);
		Utils.registerItem(thicker_copper_coil);
		Utils.registerItem(solar_cell);
		Utils.registerItem(petrol);
		
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
		OreDictionaryRegistry.register(manganoxid);
		
		// Cobble Dust
		OreDictionaryRegistry.register(cdust);
		
		// Infected
		OreDictionaryRegistry.register(infectedcrop);
		OreDictionaryRegistry.register(infectedleather);
		OreDictionaryRegistry.register(infectedbeef);
		OreDictionaryRegistry.register(infectedmilk);
		OreDictionaryRegistry.register(plastic);
		OreDictionaryRegistry.register(acid);
		
		// Battery
		OreDictionaryRegistry.register(tester);
		OreDictionaryRegistry.register(battery);
		OreDictionaryRegistry.register(energydisplay);
		OreDictionaryRegistry.register(copper_wire);
		OreDictionaryRegistry.register(copper_coil);
		OreDictionaryRegistry.register(transformer);
		OreDictionaryRegistry.register(thicker_copper_wire);
		OreDictionaryRegistry.register(thicker_copper_coil);
		OreDictionaryRegistry.register(solar_cell);
		OreDictionaryRegistry.register(petrol);
		
		// Backpack
		OreDictionaryRegistry.register(backpack);
		
		OreDictionaryRegistry.register(railplacer);
		UMod.log.debug("Oredirectionary");
	}
	
}
