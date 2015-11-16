package net.hycrafthd.umod.utils;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientRegistryUtils {

	public static void registerModelRenderer(Object obj) {
		ClientRegistryUtils.registerModelRenderer(obj, "");
	}

	public static void registerModelRenderer(Object obj, String path) {
		ClientRegistryUtils.registerModelRenderer(obj, 0, path);
	}

	public static void registerModelRenderer(Object obj, int meta, String path) {
		if (obj instanceof Block || obj instanceof Item) {
			Item item;
			if (obj instanceof Block) {
				item = Item.getItemFromBlock((Block) obj);
			} else {
				item = (Item) obj;
			}
			ClientRegistryUtils.registerModelRenderer(item, meta, new ModelResourceLocation(UReference.resource + ((path == "") ? ("") : ("/" + path + "/")) + item.getUnlocalizedName().substring(5), "inventory"));
		} else {
			throw new IllegalArgumentException("Only instances of block or items!");
		}
	}

	public static void registerModelRenderer(Object obj, int meta, ModelResourceLocation loc) {
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

	public static void addTooltip(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		String tip = I18n.format("tooltip." + stack.getUnlocalizedName());
		if (!tip.startsWith("tooltip.")) {
			tooltip.add(EnumChatFormatting.BLUE + tip + EnumChatFormatting.RESET);
		}
	}

	public static String getBlockName(Block o) {
		return o.getUnlocalizedName().replace("tile.", "");
	}

}
