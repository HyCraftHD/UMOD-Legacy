package net.hycrafthd.umod;

import net.hycrafthd.corelib.registry.OreDictionaryRegistry;
import net.hycrafthd.umod.block.BlockBarrels;
import net.hycrafthd.umod.block.BlockBlocks;
import net.hycrafthd.umod.block.BlockCable;
import net.hycrafthd.umod.block.BlockChargeStation;
import net.hycrafthd.umod.block.BlockConduit;
import net.hycrafthd.umod.block.BlockCraftFurnance;
import net.hycrafthd.umod.block.BlockEnergyPanel;
import net.hycrafthd.umod.block.BlockInfectedDirt;
import net.hycrafthd.umod.block.BlockInfectedFruit;
import net.hycrafthd.umod.block.BlockInfectedGrass;
import net.hycrafthd.umod.block.BlockInfectedLeave;
import net.hycrafthd.umod.block.BlockInfectedLog;
import net.hycrafthd.umod.block.BlockInfectedPlank;
import net.hycrafthd.umod.block.BlockInfectedSapling;
import net.hycrafthd.umod.block.BlockInfestedCleaner;
import net.hycrafthd.umod.block.BlockNetherOres;
import net.hycrafthd.umod.block.BlockNuke;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.block.BlockPainter;
import net.hycrafthd.umod.block.BlockPulverizer;
import net.hycrafthd.umod.block.BlockSolarPanel;
import net.hycrafthd.umod.block.rail.Block2rail;
import net.hycrafthd.umod.block.rail.BlockExtendedRail;
import net.hycrafthd.umod.item.ItemBlockBarrels;
import net.hycrafthd.umod.item.ItemBlockBlocks;
import net.hycrafthd.umod.item.ItemBlockConduit;
import net.hycrafthd.umod.item.ItemBlockEnergy;
import net.hycrafthd.umod.item.ItemBlockOres;
import net.hycrafthd.umod.item.ItemBlockSolarPanel;
import net.hycrafthd.umod.utils.Utils;
import net.minecraft.block.Block;

public class UBlocks {

	// Ores
	public static Block ores, netherores;
	// Blocks
	public static Block blocks;
	// SolarPanel
	public static Block solarpanel;
	// Mashiens
	public static Block pulver;
	public static Block charge;
	public static Block painter;
	public static Block craftfurnance;
	public static Block energyMonitor;
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
	public static Block conduit;

	public static Block barrels;
	
	public static Block rail;
    public static Block rail2;
	
	public UBlocks() {
		init();
		register();
		oredirectionary();
	}

	private void init() {
		// Ore
		ores = new BlockOres().setUnlocalizedName("ores");
		netherores = new BlockNetherOres().setUnlocalizedName("netherores");
		// Blocks
		blocks = new BlockBlocks().setUnlocalizedName("blocks");
		// Pulverizer
		pulver = new BlockPulverizer().setUnlocalizedName("pulver");
		craftfurnance = new BlockCraftFurnance().setUnlocalizedName("craftfurn");
		energyMonitor = new BlockEnergyPanel().setUnlocalizedName("energymonitor");
		painter = new BlockPainter().setUnlocalizedName("painter");
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

		infestedCleaner = new BlockInfestedCleaner().setUnlocalizedName("cleaner");
		// Cable
		silver_cable = new BlockCable("silvercable", 62, 62, false, "silver");
		alu_cable = new BlockCable("aluminiumcable", 38, 38, false, "aluminium");
		zin_cable = new BlockCable("zincable", 9, 9, false, "Cable");
		// Normal Blocks
		nuke = new BlockNuke().setUnlocalizedName("nuke");
		conduit = new BlockConduit().setUnlocalizedName("conduit");

		barrels = new BlockBarrels().setUnlocalizedName("barrels");
		
		rail = new BlockExtendedRail().setUnlocalizedName("ExRail");
		rail2 = new Block2rail().setUnlocalizedName("railhelp");
		UMod.log.debug("Init Blocks");
	}

	private void register() {
		// Ore
		Utils.registerBlock(ores, ItemBlockOres.class);
		Utils.registerBlock(netherores, ItemBlockOres.class);
		// Blocks
		Utils.registerBlock(blocks, ItemBlockBlocks.class);
		// Maschinen
		Utils.registerBlock(craftfurnance, ItemBlockEnergy.class);
		Utils.registerBlock(pulver, ItemBlockEnergy.class);
		Utils.registerBlock(energyMonitor);
		Utils.registerBlock(painter);
		// SolarPanel
		Utils.registerBlock(solarpanel, ItemBlockSolarPanel.class);
		Utils.registerBlock(charge);
		// Infected
		Utils.registerBlock(infectedGrass);
		Utils.registerBlock(infectedDirt);
		Utils.registerBlock(infectedLog);
		Utils.registerBlock(infectedLeave);
		Utils.registerBlock(infectedSapling);
		Utils.registerBlock(infectedPlank);
		Utils.registerBlock(infectedFruit);

		Utils.registerBlock(infestedCleaner);

		// Pipes
		Utils.registerBlock(alu_cable, ItemBlockEnergy.class);
		Utils.registerBlock(silver_cable, ItemBlockEnergy.class);
		Utils.registerBlock(zin_cable, ItemBlockEnergy.class);
		// Normal Block
		Utils.registerBlock(nuke);
		Utils.registerBlock(conduit, ItemBlockConduit.class);

		Utils.registerBlock(barrels, ItemBlockBarrels.class);
		
		Utils.registerBlock(rail);
		Utils.registerBlock(rail2);
		UMod.log.debug("Register Blocks");
	}

	private void oredirectionary() {
		// Ore
		OreDictionaryRegistry.register(ores);
		OreDictionaryRegistry.register(netherores);
		// Blocks
		OreDictionaryRegistry.register(blocks);
		// Pulverizer
		OreDictionaryRegistry.register(craftfurnance);
		OreDictionaryRegistry.register(pulver);
		OreDictionaryRegistry.register(energyMonitor);
		OreDictionaryRegistry.register(painter);
		// SolarPanel
		OreDictionaryRegistry.register(charge);
		OreDictionaryRegistry.register(solarpanel);
		// Infected
		OreDictionaryRegistry.register(infectedGrass);
		OreDictionaryRegistry.register(infectedDirt);
		OreDictionaryRegistry.register(infectedLog);
		OreDictionaryRegistry.register(infectedLeave);
		OreDictionaryRegistry.register(infectedSapling);
		OreDictionaryRegistry.register(infectedPlank);
		OreDictionaryRegistry.register(infectedFruit);

		OreDictionaryRegistry.register(infestedCleaner);

		// Pipes
		OreDictionaryRegistry.register(alu_cable);
		OreDictionaryRegistry.register(silver_cable);
		OreDictionaryRegistry.register(zin_cable);
		// Normal Block
		OreDictionaryRegistry.register(nuke);
		OreDictionaryRegistry.register(conduit);
		
		OreDictionaryRegistry.register(rail);
		OreDictionaryRegistry.register(rail2);
		UMod.log.debug("Oredirectionary");
	}

}
