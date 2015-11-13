package net.hycrafthd.umod.armor;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class RadiationArmor extends ItemArmor{

	public RadiationArmor(ArmorMaterial material, int armorType) {
		super(material, 0, armorType);
		setCreativeTab(UReference.tab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if(slot == 0 || slot == 1 || slot == 3){
			return UReference.modid + ":textures/models/armor/radiation_suit_layer_1.png";
		}else if(slot == 2){
			return UReference.modid + ":textures/models/armor/radiation_suit_layer_2.png";
		}
		return null;
	}
}
