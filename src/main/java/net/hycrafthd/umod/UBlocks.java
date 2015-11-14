package net.hycrafthd.umod;

import net.hycrafthd.umod.block.BlockBlocks;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.block.BlockPulverizer;
import net.hycrafthd.umod.block.InfectedDirt;
import net.hycrafthd.umod.block.InfectedGrass;
import net.hycrafthd.umod.block.InfectedLog;
import net.hycrafthd.umod.item.ItemBlockBlocks;
import net.hycrafthd.umod.item.ItemBlockOres;
import net.minecraft.block.Block;

public class UBlocks {

	// Ores
	public static Block ores;
	// Blocks
	public static Block blocks;
	// Pulverizer
	public static Block pulver;
	// Infected
	public static Block infectedGrass;
	public static Block infectedDirt;
	public static Block infectedLog;

	public UBlocks() {
		init();
		register();
		oredirectionary();
	}

	private void init() {
		// Ore
		ores = new BlockOres().setUnlocalizedName("ores");
		// Blocks
		blocks = new BlockBlocks().setUnlocalizedName("blocks");
		// Pulverizer
		pulver = new BlockPulverizer().setUnlocalizedName("pulver");
		// Infected
		infectedGrass = new InfectedGrass().setUnlocalizedName("infectedgrass");
		infectedDirt = new InfectedDirt().setUnlocalizedName("infecteddirt");
		infectedLog = new InfectedLog().setUnlocalizedName("infectedlog");
	}

	private void register() {
		// Ore
		UUtils.registerBlock(ores, ItemBlockOres.class);
		// Blocks
		UUtils.registerBlock(blocks, ItemBlockBlocks.class);
		// Pulverizer
		UUtils.registerBlock(pulver);
		// Infected
		UUtils.registerBlock(infectedGrass);
		UUtils.registerBlock(infectedDirt);
		UUtils.registerBlock(infectedLog);
	}

	private void oredirectionary() {
		// Ore
		UUtils.registerOredirectionary(ores);
		// Blocks
		UUtils.registerOredirectionary(blocks);
		// Pulverizer
		UUtils.registerOredirectionary(pulver);
		// Infected
		UUtils.registerOredirectionary(infectedGrass);
		UUtils.registerOredirectionary(infectedDirt);
		UUtils.registerOredirectionary(infectedLog);
	}

}
