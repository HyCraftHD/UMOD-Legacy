package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class ItemInfectedFruit extends ItemFood {

	public ItemInfectedFruit() {
		super(1, 0.2F, false);
		this.setCreativeTab(UReference.tab);
		this.setPotionEffect(Potion.poison.getId(), 5, 1, 1.0F);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		UReference.proxy.addTooltip(stack, player, tooltip, advanced);
	}

}
