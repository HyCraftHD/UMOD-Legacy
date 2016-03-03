package net.hycrafthd.umod.armor;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorEmerald extends ItemArmor {

	public ArmorEmerald(ArmorMaterial material, int armorType) {
		super(material, 0, armorType);
		this.setCreativeTab(UReference.tab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (slot == 0 || slot == 1 || slot == 3) {
			return UReference.modid + ":textures/models/armor/emerald_layer_1.png";
		} else if (slot == 2) {
			return UReference.modid + ":textures/models/armor/emerald_layer_2.png";
		}
		return null;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
}
