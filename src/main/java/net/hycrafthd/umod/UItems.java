package net.hycrafthd.umod;

import net.hycrafthd.umod.item.ItemDusts;
import net.hycrafthd.umod.item.ItemFundamental;
import net.hycrafthd.umod.item.ItemInfectedBeef;
import net.hycrafthd.umod.item.ItemInfectedCrop;
import net.hycrafthd.umod.item.ItemInfectedMilk;
import net.hycrafthd.umod.item.ItemIngots;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraft.init.Items;
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
	}

}
