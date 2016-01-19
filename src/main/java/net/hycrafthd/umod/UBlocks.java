package net.hycrafthd.umod;

import net.hycrafthd.umod.block.BlockBlocks;
import net.hycrafthd.umod.block.BlockChargeStation;
import net.hycrafthd.umod.block.BlockCraftFurnance;
import net.hycrafthd.umod.block.BlockInfectedDirt;
import net.hycrafthd.umod.block.BlockInfectedFruit;
import net.hycrafthd.umod.block.BlockInfectedGrass;
import net.hycrafthd.umod.block.BlockInfectedLeave;
import net.hycrafthd.umod.block.BlockInfectedLog;
import net.hycrafthd.umod.block.BlockInfectedPlank;
import net.hycrafthd.umod.block.BlockInfectedSapling;
import net.hycrafthd.umod.block.BlockInfestedCleaner;
import net.hycrafthd.umod.block.BlockNuke;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.block.BlockCable;
import net.hycrafthd.umod.block.BlockPulverizer;
import net.hycrafthd.umod.block.BlockSolarPanel;
import net.hycrafthd.umod.item.ItemBlockBlocks;
import net.hycrafthd.umod.item.ItemBlockEnergy;
import net.hycrafthd.umod.item.ItemBlockOres;
import net.hycrafthd.umod.item.ItemBlockSolarPanel;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraft.block.Block;

public class UBlocks {

	// Ores
	public static Block ores;
	// Blocks
	public static Block blocks;
	// SolarPanel
	public static Block solarpanel;
	// Maschinen
	public static Block pulver;
	public static Block charge;
	public static Block craftfurnance;
	// Infected
	public static Block infectedGrass;
	public static Block infectedDirt;
	public static Block infectedLog;
	public static Block infectedLeave;
	public static Block infectedSapling;
	public static Block infectedPlank;
	public static Block infectedFruit;
	
	public static Block infestedCleaner;
	// Pipes
	public static Block silver_cable;
	public static Block alu_cable;
	public static Block zin_cable;
	// Normal Blocks
	public static Block nuke;

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
		craftfurnance = new BlockCraftFurnance().setUnlocalizedName("craftfurn");
		// SolarPanel
		solarpanel = new BlockSolarPanel().setUnlocalizedName("solarpanel");
		charge = new BlockChargeStation().setUnlocalizedName("charge");
		// Infected
		infectedGrass = new BlockInfectedGrass().setUnlocalizedName("infectedgrass");
		infectedDirt = new BlockInfectedDirt().setUnlocalizedName("infecteddirt");
		infectedLog = new BlockInfectedLog().setUnlocalizedName("infectedlog");
		infectedLeave = new BlockInfectedLeave().setUnlocalizedName("infectedleave");
		infectedSapling = new BlockInfectedSapling().setUnlocalizedName("infectedsapling");
		infectedPlank = new BlockInfectedPlank().setUnlocalizedName("infectedplank");
		infectedFruit = new BlockInfectedFruit().setUnlocalizedName("infectedfruit");
		
		infestedCleaner = new BlockInfestedCleaner().setUnlocalizedName("infestedcleaner");
		// Cable
		silver_cable = new BlockCable("silvercable", 62, 62, false,"silver");
		alu_cable = new BlockCable("aluminiumcable", 38, 38, false,"aluminium");
		zin_cable = new BlockCable("zincable", 9, 9, false,"tin");
		// Normal Blocks
		nuke = new BlockNuke().setUnlocalizedName("nuke");
		Logger.debug(UBlocks.class, "init()", "Init Blocks");
	}

	private void register() {
		// Ore
		CommonRegistryUtils.registerBlock(ores, ItemBlockOres.class);
		// Blocks
		CommonRegistryUtils.registerBlock(blocks, ItemBlockBlocks.class);
		// Pulverizer
		CommonRegistryUtils.registerBlocks(craftfurnance, ItemBlockEnergy.class);
		CommonRegistryUtils.registerBlocks(pulver, ItemBlockEnergy.class);
		// SolarPanel
		CommonRegistryUtils.registerBlocks(solarpanel, ItemBlockSolarPanel.class);
		CommonRegistryUtils.registerBlock(charge);
		// Infected
		CommonRegistryUtils.registerBlock(infectedGrass);
		CommonRegistryUtils.registerBlock(infectedDirt);
		CommonRegistryUtils.registerBlock(infectedLog);
		CommonRegistryUtils.registerBlock(infectedLeave);
		CommonRegistryUtils.registerBlock(infectedSapling);
		CommonRegistryUtils.registerBlock(infectedPlank);
		CommonRegistryUtils.registerBlock(infectedFruit);
		
		CommonRegistryUtils.registerBlock(infestedCleaner);
		
		// Pipes
		CommonRegistryUtils.registerBlocks(alu_cable, ItemBlockEnergy.class);
		CommonRegistryUtils.registerBlocks(silver_cable, ItemBlockEnergy.class);
		CommonRegistryUtils.registerBlocks(zin_cable, ItemBlockEnergy.class);
		// Normal Block
		CommonRegistryUtils.registerBlock(nuke);
		Logger.debug(UBlocks.class, "register()", "Register Blocks");
	}

	private void oredirectionary() {
		// Ore
		CommonRegistryUtils.registerOredirectionary(ores);
		// Blocks
		CommonRegistryUtils.registerOredirectionary(blocks);
		// Pulverizer
		CommonRegistryUtils.registerOredirectionary(craftfurnance);
		CommonRegistryUtils.registerOredirectionary(pulver);
		// SolarPanel
		CommonRegistryUtils.registerOredirectionary(charge);
		CommonRegistryUtils.registerOredirectionary(solarpanel);
		// Infected
		CommonRegistryUtils.registerOredirectionary(infectedGrass);
		CommonRegistryUtils.registerOredirectionary(infectedDirt);
		CommonRegistryUtils.registerOredirectionary(infectedLog);
		CommonRegistryUtils.registerOredirectionary(infectedLeave);
		CommonRegistryUtils.registerOredirectionary(infectedSapling);
		CommonRegistryUtils.registerOredirectionary(infectedPlank);
		CommonRegistryUtils.registerOredirectionary(infectedFruit);
		
		CommonRegistryUtils.registerOredirectionary(infestedCleaner);
		
		// Pipes
		CommonRegistryUtils.registerOredirectionary(alu_cable);
		CommonRegistryUtils.registerOredirectionary(silver_cable);
		CommonRegistryUtils.registerOredirectionary(zin_cable);
		// Normal Block
		CommonRegistryUtils.registerOredirectionary(nuke);
		Logger.debug(UBlocks.class, "oredirectionary()", "Oredirectionary");
	}

}
