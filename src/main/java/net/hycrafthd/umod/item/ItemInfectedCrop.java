package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.UReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemInfectedCrop extends ItemFood {

	public ItemInfectedCrop() {
		super(1, 0.2F, false);
		this.setCreativeTab(UReference.tab);
		this.setPotionEffect(UPotion.radiationPotion.getId(), 5, 1, 0.8F);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}

}
