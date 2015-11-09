package net.hycrafthd.umod;

import net.hycrafthd.umod.block.BlockBlocks;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.item.ItemBlockBlocks;
import net.hycrafthd.umod.item.ItemBlockOres;
import net.minecraft.block.Block;

public class UBlocks {

	// Ores
	public static Block ores;
	// Blocks
	public static Block blocks;

	public UBlocks() {
		init();
		register();
		oredirectionary();
	}

	private void init() {
		// Ore
		ores = new BlockOres().setUnlocalizedName("ores").setCreativeTab(UReference.tab);
		// Blocks
		blocks = new BlockBlocks().setUnlocalizedName("blocks").setCreativeTab(UReference.tab);
	}

	private void register() {
		// Ore
		UUtils.registerBlock(ores, ItemBlockOres.class);
		// Blocks
		UUtils.registerBlock(blocks, ItemBlockBlocks.class);
	}

	private void oredirectionary() {
		// Ore
		UUtils.registerOredirectionary(ores);
		// Blocks
		UUtils.registerOredirectionary(blocks);
	}

}
