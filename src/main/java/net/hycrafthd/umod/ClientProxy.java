package net.hycrafthd.umod;

import java.util.List;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ClientProxy extends CommonProxy {

	
	@Override
	public void registerModels() {
		// Ore
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.ores), UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName());
			this.registerModelRenderer(UBlocks.ores, i, new ModelResourceLocation(UReference.resource + "ore" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Blocks
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(Item.getItemFromBlock(UBlocks.blocks), UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName());
			this.registerModelRenderer(UBlocks.blocks, i, new ModelResourceLocation(UReference.resource + "block" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Ingot (and Sulphur Chunk)
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(UItems.ingots, UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName());
			this.registerModelRenderer(UItems.ingots, i, new ModelResourceLocation(UReference.resource + "ingot" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Dust
		for (int i = 0; i < EnumTypeBaseStuff.values().length; i++) {
			ModelBakery.addVariantName(UItems.dusts, UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName());
			this.registerModelRenderer(UItems.dusts, i, new ModelResourceLocation(UReference.resource + "dust" + EnumTypeBaseStuff.byMetadata(i).getName(), "inventory"));
		}

		// Pulverizer
		this.registerModelRenderer(UBlocks.pulver);

		// Cobble Dust
		this.registerModelRenderer(UItems.cdust);

		// SolarPanel
		this.registerModelRenderer(UBlocks.solar);

		this.registerModelRenderer(UArmor.radiationSuitHelmet);
		this.registerModelRenderer(UArmor.radiationSuitChestplate);
		this.registerModelRenderer(UArmor.radiationSuitLeggings);
		this.registerModelRenderer(UArmor.radiationSuitBoots);

		// Infected
		this.registerModelRenderer(UBlocks.infectedGrass);
		this.registerModelRenderer(UBlocks.infectedDirt);
		this.registerModelRenderer(UBlocks.infectedLog);
		this.registerModelRenderer(UBlocks.infectedLeave);
		this.registerModelRenderer(UBlocks.infectedPlank);
		this.registerModelRenderer(UBlocks.infectedSapling);
		this.registerModelRenderer(UBlocks.infectedFruit);
		this.registerModelRenderer(UItems.infectedcrop);
		
		//Pipes
		this.registerModelRenderer(UBlocks.iron_pipe);
}

	private void registerModelRenderer(Object obj) {
		this.registerModelRenderer(obj, 0);
	}

	private void registerModelRenderer(Object obj, int meta) {
		if (obj instanceof Block || obj instanceof Item) {
			Item item;
			if (obj instanceof Block) {
				item = Item.getItemFromBlock((Block) obj);
			} else {
				item = (Item) obj;
			}
			this.registerModelRenderer(item, meta, new ModelResourceLocation(UReference.resource + item.getUnlocalizedName().substring(5), "inventory"));
		} else {
			throw new IllegalArgumentException("Only instances of block or items!");
		}
	}

	private void registerModelRenderer(Object obj, int meta, ModelResourceLocation loc) {
		if (obj instanceof Block || obj instanceof Item) {
			Item item;
			if (obj instanceof Block) {
				item = Item.getItemFromBlock((Block) obj);
			} else {
				item = (Item) obj;
			}
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, loc);
		} else {
			throw new IllegalArgumentException("Only instances of block or items!");
		}
	}

	@Override
	public void addTooltip(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		tooltip.add(I18n.format("tooltip." + stack.getUnlocalizedName()));
	}

}
