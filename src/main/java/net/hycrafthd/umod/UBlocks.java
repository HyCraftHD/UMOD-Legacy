package net.hycrafthd.umod;

import net.hycrafthd.umod.block.*;
import net.hycrafthd.umod.block.rail.*;
import net.hycrafthd.umod.item.*;
import net.hycrafthd.umod.utils.URegistryUtils;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;

public class UBlocks {
	
	// Ores
	public static Block ores, netherores;
	// Blocks
	public static Block blocks;
	// SolarPanel
	public static Block solarpanel;
	// Machinery
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
	public static Block oilsand;
	public static Block oilglass;
	
	public static Block infestedCleaner;
	// Pipes
	public static Block silver_cable;
	public static Block alu_cable;
	public static Block zin_cable;
	//magic crafter
	public static Block magic_crafter;
	// Normal Blocks
	public static Block nuke;
	public static Block conduit;
	
	public static Block barrels;
	
	public static Block rail;
	public static Block rail2;
	
	// Stairs
	public static BlockStairCreator[] stonestairs;
	public static BlockStairCreator[] woolstairs;
	public static BlockStairCreator[] claystairs;
	// Slabs
	public static BlockSlabCreator[] stoneslabs;
	
	public UBlocks() {
		init();
		register();
	}
	
	private void init() {
		//magic crafter
		magic_crafter = new BlockMagicCrafter().setUnlocalizedName("magic_crafter");
		// Ore
		ores = new BlockOres().setUnlocalizedName("ores");
		netherores = new BlockNetherOres().setUnlocalizedName("netherores");
		// Blocks
		blocks = new BlockBlocks().setUnlocalizedName("blocks");
		// Machinery
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
		
		oilglass = new BlockOilGlass().setUnlocalizedName("oilglass");
		oilsand = new BlockOilSand().setUnlocalizedName("oilsand");
		
		infestedCleaner = new BlockInfestedCleaner().setUnlocalizedName("cleaner");
		// Cable
		silver_cable = new BlockCable("silvercable", 62, 62, false, "Cable");
		alu_cable = new BlockCable("aluminiumcable", 38, 38, false, "Cable");
		zin_cable = new BlockCable("zincable", 9, 9, false, "Cable");
		// Normal Blocks
		nuke = new BlockNuke().setUnlocalizedName("nuke");
		conduit = new BlockConduit().setUnlocalizedName("conduit");
		
		barrels = new BlockBarrels().setUnlocalizedName("barrels");
		
		rail = new BlockExtendedRail().setUnlocalizedName("ExRail");
		rail2 = new Block2rail().setUnlocalizedName("railhelp");
		
		// Stairs
		stonestairs = new BlockStairCreator[BlockStone.EnumType.values().length];
		for (int i = 0; i < BlockStone.EnumType.values().length; i++) {
			stonestairs[i] = new BlockStairCreator(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.byMetadata(i)), "stone_" + BlockStone.EnumType.byMetadata(i).getName());
		}
		
		woolstairs = new BlockStairCreator[EnumDyeColor.values().length];
		for (int i = 0; i < EnumDyeColor.values().length; i++) {
			woolstairs[i] = new BlockStairCreator(Blocks.wool.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(i)), "wool_" + EnumDyeColor.byDyeDamage(i).getName());
		}
		
		claystairs = new BlockStairCreator[EnumDyeColor.values().length + 1];
		for (int i = 0; i < EnumDyeColor.values().length; i++) {
			claystairs[i] = new BlockStairCreator(Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.byMetadata(i)), "clay_stained_" + EnumDyeColor.byDyeDamage(i).getName());
		}
		claystairs[EnumDyeColor.values().length] = new BlockStairCreator(Blocks.hardened_clay.getDefaultState(), "clay_hardened");
		
		// Slabs
		stoneslabs = new BlockSlabCreator[BlockStone.EnumType.values().length];
		for (int i = 0; i < BlockStone.EnumType.values().length; i++) {
			stoneslabs[i] = new BlockSlabCreator(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.byMetadata(i)), "stone_" + BlockStone.EnumType.byMetadata(i).getName());
		}
		UMod.log.debug("Init Blocks");
	}
	
	private void register() {
		// Ore
		URegistryUtils.registerBlock(ores, ItemBlockOres.class);
		URegistryUtils.registerBlock(netherores, ItemBlockOres.class);
		// Blocks
		URegistryUtils.registerBlock(blocks, ItemBlockBlocks.class);
		//Magic crfafter
		URegistryUtils.registerBlock(magic_crafter);
		// Machinery
		URegistryUtils.registerBlock(craftfurnance, ItemBlockEnergy.class);
		URegistryUtils.registerBlock(pulver, ItemBlockEnergy.class);
		URegistryUtils.registerBlock(energyMonitor);
		URegistryUtils.registerBlock(painter);
		// SolarPanel
		URegistryUtils.registerBlock(solarpanel, ItemBlockSolarPanel.class);
		URegistryUtils.registerBlock(charge);
		// Infected
		URegistryUtils.registerBlock(infectedGrass);
		URegistryUtils.registerBlock(infectedDirt);
		URegistryUtils.registerBlock(infectedLog);
		URegistryUtils.registerBlock(infectedLeave);
		URegistryUtils.registerBlock(infectedSapling);
		URegistryUtils.registerBlock(infectedPlank);
		URegistryUtils.registerBlock(infectedFruit);
		
		URegistryUtils.registerBlock(oilglass);
		URegistryUtils.registerBlock(oilsand);
		
		URegistryUtils.registerBlock(infestedCleaner);
		
		// Pipes
		URegistryUtils.registerBlock(alu_cable, ItemBlockEnergy.class);
		URegistryUtils.registerBlock(silver_cable, ItemBlockEnergy.class);
		URegistryUtils.registerBlock(zin_cable, ItemBlockEnergy.class);
		// Normal Block
		URegistryUtils.registerBlock(nuke);
		URegistryUtils.registerBlock(conduit, ItemBlockConduit.class);
		
		URegistryUtils.registerBlock(barrels, ItemBlockBarrels.class);
		
		URegistryUtils.registerBlock(rail);
		URegistryUtils.registerBlock(rail2);
		
		// Stairs
		for (BlockStairCreator creator : stonestairs) {
			URegistryUtils.registerBlock(creator.getStair());
		}
		for (BlockStairCreator creator : woolstairs) {
			URegistryUtils.registerBlock(creator.getStair());
		}
		for (BlockStairCreator creator : claystairs) {
			URegistryUtils.registerBlock(creator.getStair());
		}
		// Slabs
		for (BlockSlabCreator creator : stoneslabs) {
			URegistryUtils.registerHalfSlabs(creator);
		}
		UMod.log.debug("Register Blocks");
	}
	
}
