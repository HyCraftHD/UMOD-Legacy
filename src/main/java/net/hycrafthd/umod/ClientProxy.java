package net.hycrafthd.umod;

import java.util.List;

import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.entity.EntityNukePrimed;
import net.hycrafthd.umod.entity.render.RenderInfectedCow;
import net.hycrafthd.umod.entity.render.RenderInfectedCreeper;
import net.hycrafthd.umod.entity.render.RenderNukePrimed;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.render.TileEntityPulverizerSpecialRender;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.utils.ClientRegistryUtils;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerModels() {
		// Ore
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.ores), UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UBlocks.ores, i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
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
		ClientRegistryUtils.registerModelRenderer(UBlocks.pulver);

		// Cobble Dust
		ClientRegistryUtils.registerModelRenderer(UItems.cdust);

		// SolarPanel
		for (int i = 0; i < EnumTypeSolarPanel.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.solarpanel), UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName());
			ClientRegistryUtils.registerModelRenderer(UBlocks.solarpanel, i, new ModelResourceLocation(UReference.resource + "solarpanel" + EnumTypeSolarPanel.byMetadata(i).getName(), "inventory"));
		}

		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitHelmet);
		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitChestplate);
		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitLeggings);
		ClientRegistryUtils.registerModelRenderer(UArmor.radiationSuitBoots);

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

		// Pipes
		ClientRegistryUtils.registerModelRenderer(UBlocks.iron_pipe);
		ClientRegistryUtils.registerModelRenderer(UBlocks.alu_pipe);
		ClientRegistryUtils.registerModelRenderer(UBlocks.silver_pipe);
		ClientRegistryUtils.registerModelRenderer(UBlocks.lead_pipe);
		ClientRegistryUtils.registerModelRenderer(UBlocks.gold_pipe);
		ClientRegistryUtils.registerModelRenderer(UBlocks.copper_pipe);
		ClientRegistryUtils.registerModelRenderer(UBlocks.zin_pipe);

		//Nuke
		ClientRegistryUtils.registerModelRenderer(UBlocks.blockNuke);
		
	}

	@Override
	public void registerRenderer() {
		ClientRegistryUtils.registerEntityRenderer(EntityInfectedCow.class, new RenderInfectedCow());
		ClientRegistryUtils.registerEntityRenderer(EntityInfectedCreeper.class, new RenderInfectedCreeper());
		ClientRegistryUtils.registerEntityRenderer(EntityNukePrimed.class, new RenderNukePrimed());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPulverizer.class, new TileEntityPulverizerSpecialRender());
	}

	@Override
	public void addTooltip(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		ClientRegistryUtils.addTooltip(stack, player, tooltip, advanced);
	}

}
