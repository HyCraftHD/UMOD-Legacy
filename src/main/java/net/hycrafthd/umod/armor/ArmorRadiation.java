package net.hycrafthd.umod.armor;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;

public class ArmorRadiation extends ItemArmor {
	
	public ArmorRadiation(ArmorMaterial material, int armorType) {
		super(material, 0, armorType);
		this.setCreativeTab(UReference.tab);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (slot == 0 || slot == 1 || slot == 3) {
			return UReference.modid + ":textures/models/armor/radiation_suit_layer_1.png";
		} else if (slot == 2) {
			return UReference.modid + ":textures/models/armor/radiation_suit_layer_2.png";
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}
}
