package net.hycrafthd.umod;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.corelib.util.ItemUtil;
import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.hycrafthd.umod.entity.*;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.entity.render.*;
import net.hycrafthd.umod.entity.render.rail.RenderRailFX;
import net.hycrafthd.umod.enumtype.*;
import net.hycrafthd.umod.render.*;
import net.hycrafthd.umod.tileentity.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.*;

public class ClientProxy extends CommonProxy {
	
	public static KeyBinding info = new KeyBinding("Infromation", Keyboard.KEY_I, "UMod");
	@SideOnly(Side.CLIENT)
	public static EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	
	@Override
	public void registerModels() {
		
		// Ore and NetherOre
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.ores), UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.ores), i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.netherores), UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.netherores), i, new ModelResourceLocation(UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		// Blocks
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.blocks), UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.blocks), i, new ModelResourceLocation(UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		// Ingot (and Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelRegistry.registerVariants(UItems.ingots, UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(UItems.ingots, i, new ModelResourceLocation(UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		// Transformer
		for (int i = 0; i < EnumTypeTransformer.values().length; i++) {
			ModelRegistry.registerVariants(UItems.transformer, UReference.resource + "transformer" + EnumTypeTransformer.byMetadata(i).getName());
			ModelRegistry.register(UItems.transformer, i, new ModelResourceLocation(UReference.resource + "transformer" + EnumTypeTransformer.byMetadata(i).getName(), "inventory"));
		}
		
		// Dust
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelRegistry.registerVariants(UItems.dusts, UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(UItems.dusts, i, new ModelResourceLocation(UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}
		
		ModelRegistry.register(UItems.manganoxid);
		
		// Pulverizer
		ModelRegistry.register(UBlocks.charge);
		ModelRegistry.register(UBlocks.pulver);
		ModelRegistry.register(UBlocks.craftfurnance);
		ModelRegistry.register(UBlocks.painter);
		
		// cbbl Dust
		ModelRegistry.register(UItems.cdust);
		
		// battery
		ModelRegistry.register(UItems.battery);
		ModelRegistry.register(UItems.copper_wire);
		ModelRegistry.register(UItems.copper_coil);
		ModelRegistry.register(UItems.transformer);
		ModelRegistry.register(UItems.thicker_copper_wire);
		ModelRegistry.register(UItems.petrol);
		
		// SolarPanel
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			ModelRegistry.registerVariants(Item.getItemFromBlock(UBlocks.solarpanel), UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.solarpanel), i, new ModelResourceLocation(UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName(), "inventory"));
		}
		
		// Armor
		ModelRegistry.register(UArmor.radiationSuitHelmet);
		ModelRegistry.register(UArmor.radiationSuitChestplate);
		ModelRegistry.register(UArmor.radiationSuitLeggings);
		ModelRegistry.register(UArmor.radiationSuitBoots);
		
		ModelRegistry.register(UArmor.emeraldHelmet);
		ModelRegistry.register(UArmor.emeraldChestplate);
		ModelRegistry.register(UArmor.emeraldLeggings);
		ModelRegistry.register(UArmor.emeraldBoots);
		
		// Infected
		ModelRegistry.register(UBlocks.infectedGrass);
		ModelRegistry.register(UBlocks.infectedDirt);
		ModelRegistry.register(UBlocks.infectedLog);
		ModelRegistry.register(UBlocks.infectedLeave);
		ModelRegistry.register(UBlocks.infectedPlank);
		ModelRegistry.register(UBlocks.infectedSapling);
		ModelRegistry.register(UBlocks.infectedFruit);
		ModelRegistry.register(UItems.infectedcrop);
		ModelRegistry.register(UItems.infectedleather);
		ModelRegistry.register(UItems.infectedbeef);
		ModelRegistry.register(UItems.infectedmilk);
		
		ModelRegistry.register(UItems.acid);
		ModelRegistry.register(UItems.plastic);
		ModelRegistry.register(UBlocks.oilsand);
		ModelRegistry.register(UBlocks.oilglass);
		
		ModelRegistry.register(UBlocks.infestedCleaner);
		
		// Pipes
		ModelRegistry.register(UBlocks.alu_cable);
		ModelRegistry.register(UBlocks.silver_cable);
		ModelRegistry.register(UBlocks.zin_cable);
		
		// Normal Blocks
		ModelRegistry.register(UBlocks.nuke);
		ModelRegistry.register(UBlocks.craftfurnance);
		ModelRegistry.register(UBlocks.rail);
		
		// Stone Stairs
		for (Block block : UBlocks.stonestairs) {
			ModelRegistry.register(block);
		}
		
		// Backpack
		for (int i = 0; i < EnumTypeBackPack.values().length; i++) {
			ModelRegistry.registerVariants(UItems.backpack, UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName());
			ModelRegistry.register(UItems.backpack, i, new ModelResourceLocation(UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName(), "inventory"));
		}
		
		// Tools
		ModelRegistry.register(UTools.emeraldAxe);
		ModelRegistry.register(UTools.emeraldPickaxe);
		ModelRegistry.register(UTools.emeraldSword);
		ModelRegistry.register(UTools.emeraldSpade);
		ModelRegistry.register(UTools.emeraldHoe);
		
		// Keybinding
		KeybindingRegistry.register(info);
	}
	
	@Override
	public void registerRenderer() {
		RenderFX.register(TileEntityEnergyPannel.class, new TileEntityEnergyPannelRender());
		RenderFX.register(TileEntityCable.class, new TileEntityCabelRender());
		RenderFX.register(TileEntityItemPipe.class, new TileEntityItemPipeRender());
		
		RenderRegistry.registerEntity(EntityInfectedCow.class, new RenderInfectedCow());
		RenderRegistry.registerEntity(EntityInfectedCreeper.class, new RenderInfectedCreeper());
		RenderRegistry.registerEntity(EntityNukePrimed.class, new RenderNukePrimed());
		RenderRegistry.registerEntity(EntityInfectedZombie.class, new RenderInfectedZombie());
		RenderRegistry.registerEntity(EntityFX.class, new RenderFX());
		RenderRegistry.registerEntity(EntityRailFX.class, new RenderRailFX());
		
		RenderRegistry.registerTileEntity(TileEntityPulverizer.class, new TileEntityPulverizerSpecialRender());
		VIARegister.registerVIA();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addTooltip(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		String tip = I18n.format("tooltip." + stack.getUnlocalizedName());
		if (!tip.startsWith("tooltip.")) {
			tooltip.add(EnumChatFormatting.BLUE + tip + EnumChatFormatting.RESET);
		}
	}
	
}
