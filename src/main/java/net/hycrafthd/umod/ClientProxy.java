package net.hycrafthd.umod;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.corelib.registry.KeybindingRegistry;
import net.hycrafthd.corelib.registry.ModelRegistry;
import net.hycrafthd.corelib.registry.RenderRegistry;
import net.hycrafthd.corelib.util.ItemUtil;
import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.hycrafthd.umod.entity.EntityFX;
import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.entity.EntityInfectedZombie;
import net.hycrafthd.umod.entity.EntityNukePrimed;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.entity.render.RenderFX;
import net.hycrafthd.umod.entity.render.RenderInfectedCow;
import net.hycrafthd.umod.entity.render.RenderInfectedCreeper;
import net.hycrafthd.umod.entity.render.RenderInfectedZombie;
import net.hycrafthd.umod.entity.render.RenderNukePrimed;
import net.hycrafthd.umod.entity.render.rail.RenderRailFX;
import net.hycrafthd.umod.enumtype.EnumTypeBackPack;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.render.TileEntityCabelRender;
import net.hycrafthd.umod.render.TileEntityEnergyPannelRender;
import net.hycrafthd.umod.render.TileEntityItemPipeRender;
import net.hycrafthd.umod.render.TileEntityPulverizerSpecialRender;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.hycrafthd.umod.tileentity.TileEntityItemPipe;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

	public static KeyBinding info = new KeyBinding("Infromation", Keyboard.KEY_I, "UMod");
    @SideOnly(Side.CLIENT)
	public static EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	
	@Override
	public void registerModels() {
		// Ore and NetherOre
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.ores), UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.ores), i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.netherores), UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.netherores), i, new ModelResourceLocation(UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Blocks
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.blocks), UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.blocks), i, new ModelResourceLocation(UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Ingot (and Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(UItems.ingots, UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(UItems.ingots, i, new ModelResourceLocation(UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Dust
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(UItems.dusts, UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName());
			ModelRegistry.register(UItems.dusts, i, new ModelResourceLocation(UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Pulverizer
		ModelRegistry.register(UBlocks.charge);
		ModelRegistry.register(UBlocks.pulver);
		ModelRegistry.register(UBlocks.craftfurnance);
		ModelRegistry.register(UBlocks.painter);
		// cbbl Dust
		ModelRegistry.register(UItems.cdust);
		
		ModelRegistry.register(UItems.battery);

		// SolarPanel
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.solarpanel), UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName());
			ModelRegistry.register(ItemUtil.from(UBlocks.solarpanel), i, new ModelResourceLocation(UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName(), "inventory"));
		}

		//Armor
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

		ModelRegistry.register(UBlocks.infestedCleaner);
		
		// Pipes
		ModelRegistry.register(UBlocks.alu_cable);
		ModelRegistry.register(UBlocks.silver_cable);
		ModelRegistry.register(UBlocks.zin_cable);

		// Normal Blocks
		ModelRegistry.register(UBlocks.nuke);
        ModelRegistry.register(UBlocks.craftfurnance);
        ModelRegistry.register(UBlocks.rail);
		// Backpack
		for (int i = 0; i < EnumTypeBackPack.values().length; i++) {
			ModelBakery.addVariantName(UItems.backpack, UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName());
			ModelRegistry.register(UItems.backpack, i, new ModelResourceLocation(UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName(), "inventory"));
		}

		//Tools
		ModelRegistry.register(UTools.emeraldAxe);
		ModelRegistry.register(UTools.emeraldPickaxe);
		ModelRegistry.register(UTools.emeraldSword);
		ModelRegistry.register(UTools.emeraldSpade);
		ModelRegistry.register(UTools.emeraldHoe);
		
		//Keybinding
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
