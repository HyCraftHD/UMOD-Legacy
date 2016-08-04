package net.hycrafthd.umod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

public class InventoryBackPack extends InventoryBackPackBase {
	
	public InventoryBackPack(ItemStack itemStack, EntityPlayer player, int backpacksize) {
		size = backpacksize;
		inv = new ItemStack[size];
		readFromNBT(itemStack.getTagCompound());
	}
	
	@Override
	public String getName() {
		return "backpack";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		NBTTagList list = tag.getTagList("backpackinventory", 10);
		for (int i = 0; i < list.tagCount() && i < inv.length; i++) {
			NBTTagCompound index = (NBTTagCompound) list.get(i);
			int ii = index.getInteger("index");
			try {
				inv[ii] = ItemStack.loadItemStackFromNBT(index);
			} catch (NullPointerException ex) {
				inv[ii] = null;
			}
		}
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.inv.length; i++) {
			if (this.inv[i] != null && this.inv[i].stackSize > 0) {
				NBTTagCompound index = new NBTTagCompound();
				list.appendTag(index);
				index.setInteger("index", i);
				inv[i].writeToNBT(index);
			}
		}
		tag.setTag("backpackinventory", list);
	}
	
	@Override
	public void markDirty() {
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void setField(int id, int value) {
	}
	
	@Override
	public int getFieldCount() {
		return 0;
	}
	
	@Override
	public void clear() {
	}
	
}