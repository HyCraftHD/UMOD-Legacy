package net.hycrafthd.umod;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.entity.EntityInfectedZombie;
import net.hycrafthd.umod.entity.EntityNukePrimed;
import net.hycrafthd.umod.entity.EntityPipeFX;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.entity.render.RenderInfectedCow;
import net.hycrafthd.umod.entity.render.RenderInfectedCreeper;
import net.hycrafthd.umod.entity.render.RenderInfectedZombie;
import net.hycrafthd.umod.entity.render.RenderNukePrimed;
import net.hycrafthd.umod.entity.render.RenderPipeFX;
import net.hycrafthd.umod.entity.render.rail.RenderRailFX;
import net.hycrafthd.umod.enumtype.EnumTypeBackPack;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.render.TileEntityCabelSpecialRender;
import net.hycrafthd.umod.render.TileEntityEnergyPannelSpecialRender;
import net.hycrafthd.umod.render.TileEntityPulverizerSpecialRender;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.utils.ClientRegistryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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
			ClientRegistryUtils.registerModelRenderer(UBlocks.ores, i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.netherores), UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UBlocks.netherores, i, new ModelResourceLocation(UReference.resource + "netherore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Blocks
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.blocks), UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UBlocks.blocks, i, new ModelResourceLocation(UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Ingot (and Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(UItems.ingots, UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UItems.ingots, i, new ModelResourceLocation(UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Dust
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(UItems.dusts, UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UItems.dusts, i, new ModelResourceLocation(UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Pulverizer
		ClientRegistryUtils.registerModelRenderer(UBlocks.charge);
		ClientRegistryUtils.registerModelRenderer(UBlocks.pulver);
		ClientRegistryUtils.registerModelRenderer(UBlocks.craftfurnance);
        ClientRegistryUtils.registerModelRenderer(UBlocks.painter);
		// Cobble Dust
		ClientRegistryUtils.registerModelRenderer(UItems.cdust);
		
		ClientRegistryUtils.registerModelRenderer(UItems.battery);

		// SolarPanel
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.solarpanel), UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UBlocks.solarpanel, i, new ModelResourceLocation(UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName(), "inventory"));
		}

		//Armor
		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitHelmet);
		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitChestplate);
		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitLeggings);
		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitBoots);
		
		ClientRegistryUtils.registerModelRenderer(UArmor.emeraldHelmet);
		ClientRegistryUtils.registerModelRenderer(UArmor.emeraldChestplate);
		ClientRegistryUtils.registerModelRenderer(UArmor.emeraldLeggings);
		ClientRegistryUtils.registerModelRenderer(UArmor.emeraldBoots);
		
		// Infected
		ClientRegistryUtils.registerModelRenderer(UBlocks.infectedGrass);
		ClientRegistryUtils.registerModelRenderer(UBlocks.infectedDirt);
		ClientRegistryUtils.registerModelRenderer(UBlocks.infectedLog);
		ClientRegistryUtils.registerModelRenderer(UBlocks.infectedLeave);
		ClientRegistryUtils.registerModelRenderer(UBlocks.infectedPlank);
		ClientRegistryUtils.registerModelRenderer(UBlocks.infectedSapling);
		ClientRegistryUtils.registerModelRenderer(UBlocks.infectedFruit);
		ClientRegistryUtils.registerModelRenderer(UItems.infectedcrop);
		ClientRegistryUtils.registerModelRenderer(UItems.infectedleather);
		ClientRegistryUtils.registerModelRenderer(UItems.infectedbeef);
		ClientRegistryUtils.registerModelRenderer(UItems.infectedmilk);

		ClientRegistryUtils.registerModelRenderer(UBlocks.infestedCleaner);
		
		// Pipes
		ClientRegistryUtils.registerModelRenderer(UBlocks.alu_cable);
		ClientRegistryUtils.registerModelRenderer(UBlocks.silver_cable);
		ClientRegistryUtils.registerModelRenderer(UBlocks.zin_cable);

		// Normal Blocks
		ClientRegistryUtils.registerModelRenderer(UBlocks.nuke);
        ClientRegistryUtils.registerModelRenderer(UBlocks.craftfurnance);
        ClientRegistryUtils.registerModelRenderer(UBlocks.rail);
		// Backpack
		for (int i = 0; i < EnumTypeBackPack.values().length; i++) {
			ModelBakery.addVariantName(UItems.backpack, UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UItems.backpack, i, new ModelResourceLocation(UReference.resource + "backpack" + EnumTypeBackPack.byMetadata(i).getName(), "inventory"));
		}

		//Tools
		ClientRegistryUtils.registerModelRenderer(UTools.emeraldAxe);
		ClientRegistryUtils.registerModelRenderer(UTools.emeraldPickaxe);
		ClientRegistryUtils.registerModelRenderer(UTools.emeraldSword);
		ClientRegistryUtils.registerModelRenderer(UTools.emeraldSpade);
		ClientRegistryUtils.registerModelRenderer(UTools.emeraldHoe);
		
		//Keybinding
	    ClientRegistry.registerKeyBinding(info);
	}

	@Override
	public void registerRenderer() {
		ClientRegistryUtils.registerEntityRenderer(EntityInfectedCow.class, new RenderInfectedCow());
		ClientRegistryUtils.registerEntityRenderer(EntityInfectedCreeper.class, new RenderInfectedCreeper());
		ClientRegistryUtils.registerEntityRenderer(EntityNukePrimed.class, new RenderNukePrimed());
		ClientRegistryUtils.registerEntityRenderer(EntityInfectedZombie.class, new RenderInfectedZombie());
		ClientRegistryUtils.registerEntityRenderer(EntityPipeFX.class, new RenderPipeFX());
		ClientRegistryUtils.registerEntityRenderer(EntityRailFX.class, new RenderRailFX());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPulverizer.class, new TileEntityPulverizerSpecialRender());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyPannel.class, new TileEntityEnergyPannelSpecialRender());
	}

	@Override
	public void addTooltip(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		ClientRegistryUtils.addTooltip(stack, player, tooltip, advanced);
	}

}
