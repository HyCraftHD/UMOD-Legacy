package net.hycrafthd.umod;

import net.hycrafthd.umod.item.ItemDusts;
import net.hycrafthd.umod.item.ItemFundamental;
import net.hycrafthd.umod.item.ItemInfectedCrop;
import net.hycrafthd.umod.item.ItemIngots;
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
	}

}
