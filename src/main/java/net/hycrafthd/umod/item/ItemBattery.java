package net.hycrafthd.umod.item;

import java.util.List;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.EntityTommahak;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBattery extends ItemBase {
	
	public ItemBattery() {
		this.setMaxDamage(500);
		this.setMaxStackSize(1);
		setCreativeTab(UReference.maschines);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		worldIn.spawnEntityInWorld(new EntityTommahak(worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ(), playerIn));
		return super.onItemRightClick(itemStackIn, worldIn, playerIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, @SuppressWarnings("rawtypes") List tooltip, boolean advanced) {
	}
	
}
