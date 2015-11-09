package net.hycrafthd.umod;

import net.hycrafthd.umod.item.ItemDusts;
import net.hycrafthd.umod.item.ItemIngots;
import net.minecraft.item.Item;

public class UItems {

	// Ingot
	public static Item ingots;

	// Dust
	public static Item dusts;

	public UItems() {
		init();
		register();
		oredirectionary();
	}

	private void init() {
		// Ingot
		ingots = new ItemIngots().setUnlocalizedName("ingots").setCreativeTab(UReference.tab);
		// Dust
		dusts = new ItemDusts().setUnlocalizedName("dusts").setCreativeTab(UReference.tab);
	}

	private void register() {
		// Ingot
		UUtils.registerItem(ingots);
		// Dust
		UUtils.registerItem(dusts);
	}

	private void oredirectionary() {
		// Ingot
		UUtils.registerOredirectionary(ingots);
		// Dust
		UUtils.registerOredirectionary(dusts);
	}

}
