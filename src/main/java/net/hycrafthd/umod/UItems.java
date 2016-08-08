package net.hycrafthd.umod;

import net.hycrafthd.umod.item.*;
import net.hycrafthd.umod.utils.URegistryUtils;
import net.minecraft.item.Item;

public class UItems {
	
	// Ingot
	public static Item ingots;
	
	// Dust
	public static Item dusts;
	public static Item manganoxid;
	//magic crafter
	public static Item magic_diamond;
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
	}
	
	private void init() {
		
		// Ingot
		ingots = new ItemIngots().setUnlocalizedName("ingots");
		
		// Dust
		dusts = new ItemDusts().setUnlocalizedName("dusts");
		manganoxid = new ItemManganOxid().setUnlocalizedName("manganoxid");
		
		//magic crafter
		magic_diamond = new ItemMagicDiamond().setUnlocalizedName("magic_diamond");
		
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
		URegistryUtils.registerItem(ingots);
		
		// Dust
		URegistryUtils.registerItem(dusts);
		URegistryUtils.registerItem(manganoxid);
		
		//magic crafter
		URegistryUtils.registerItem(magic_diamond);
		
		// Cobble Dust
		URegistryUtils.registerItem(cdust);
		
		// Infected
		URegistryUtils.registerItem(infectedcrop);
		URegistryUtils.registerItem(infectedleather);
		URegistryUtils.registerItem(infectedbeef);
		URegistryUtils.registerItem(infectedmilk);
		URegistryUtils.registerItem(acid);
		URegistryUtils.registerItem(plastic);
		
		// Battery
		URegistryUtils.registerItem(tester);
		URegistryUtils.registerItem(battery);
		URegistryUtils.registerItem(energydisplay);
		URegistryUtils.registerItem(copper_wire);
		URegistryUtils.registerItem(copper_coil);
		URegistryUtils.registerItem(transformer);
		URegistryUtils.registerItem(thicker_copper_wire);
		URegistryUtils.registerItem(thicker_copper_coil);
		URegistryUtils.registerItem(solar_cell);
		URegistryUtils.registerItem(petrol);
		
		// Backpack
		URegistryUtils.registerItem(backpack);
		
		URegistryUtils.registerItem(railplacer);
		
		UMod.log.debug("Register Items");
	}
	
}
