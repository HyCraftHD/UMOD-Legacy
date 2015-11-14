package net.hycrafthd.umod;

import net.hycrafthd.umod.block.BlockBlocks;
import net.hycrafthd.umod.block.BlockInfectedDirt;
import net.hycrafthd.umod.block.BlockInfectedGrass;
import net.hycrafthd.umod.block.BlockInfectedLeave;
import net.hycrafthd.umod.block.BlockInfectedLog;
import net.hycrafthd.umod.block.BlockInfectedPlank;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.block.BlockPulverizer;
import net.hycrafthd.umod.block.BlockSolarPanel;
import net.hycrafthd.umod.item.ItemBlockBlocks;
import net.hycrafthd.umod.item.ItemBlockOres;
import net.minecraft.block.Block;

public class UBlocks {

	// Ores
	public static Block ores;
	// Blocks
	public static Block blocks;
	// SolarPanel
	public static Block solar;
	// Pulverizer
	public static Block pulver;
	// Infected
	public static Block infectedGrass;
	public static Block infectedDirt;
	public static Block infectedLog;
	public static Block infectedLeave;
	public static Block infectedPlank;

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
		// SolarPanel
		solar = new BlockSolarPanel().setUnlocalizedName("solar");
		// Infected
		infectedGrass = new BlockInfectedGrass().setUnlocalizedName("infectedgrass");
		infectedDirt = new BlockInfectedDirt().setUnlocalizedName("infecteddirt");
		infectedLog = new BlockInfectedLog().setUnlocalizedName("infectedlog");
		infectedLeave = new BlockInfectedLeave().setUnlocalizedName("infectedleave");
		infectedPlank = new BlockInfectedPlank().setUnlocalizedName("infectedplank");
	}

	private void register() {
		// Ore
		UUtils.registerBlock(ores, ItemBlockOres.class);
		// Blocks
		UUtils.registerBlock(blocks, ItemBlockBlocks.class);
		// Pulverizer
		UUtils.registerBlock(pulver);
		// SolarPanel
		UUtils.registerBlock(solar);
		// Infected
		UUtils.registerBlock(infectedGrass);
		UUtils.registerBlock(infectedDirt);
		UUtils.registerBlock(infectedLog);
		UUtils.registerBlock(infectedLeave);
		UUtils.registerBlock(infectedPlank);
	}

	private void oredirectionary() {
		// Ore
		UUtils.registerOredirectionary(ores);
		// Blocks
		UUtils.registerOredirectionary(blocks);
		// Pulverizer
		UUtils.registerOredirectionary(pulver);
		// SolarPanel
		UUtils.registerOredirectionary(solar);
		// Infected
		UUtils.registerOredirectionary(infectedGrass);
		UUtils.registerOredirectionary(infectedDirt);
		UUtils.registerOredirectionary(infectedLog);
		UUtils.registerOredirectionary(infectedLeave);
		UUtils.registerOredirectionary(infectedPlank);
	}

}
