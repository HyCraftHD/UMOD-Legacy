package net.hycrafthd.umod.item;

import net.hycrafthd.umod.UPotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemInfectedMilk extends ItemBase {
	
	public ItemInfectedMilk() {
		this.setMaxStackSize(1);
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if (!playerIn.capabilities.isCreativeMode) {
			--stack.stackSize;
		}
		
		if (!worldIn.isRemote) {
			playerIn.curePotionEffects(stack);
			playerIn.addPotionEffect(new PotionEffect(UPotion.radiationPotion.getId(), 100, 0));
		}
		
		return stack.stackSize <= 0 ? new ItemStack(Items.bucket) : stack;
	}
	
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
	
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
		return itemStackIn;
	}
}