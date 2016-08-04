package net.hycrafthd.umod.container;

import net.hycrafthd.umod.enumtype.EnumTypeBackPack;
import net.hycrafthd.umod.inventory.InventoryBackPack;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerBackPack extends Container {
	
	public boolean updateNotification;
	private InventoryBackPack inv;
	private EnumTypeBackPack type;
	
	public ContainerBackPack(InventoryBackPack inventorybackpack, InventoryPlayer inventoryplayer, EnumTypeBackPack type) {
		
		this.updateNotification = false;
		this.inv = inventorybackpack;
		this.type = type;
		
		int x_backpackinv = 0;
		int y_backpackinv = 20;
		
		int x_playerinv = 8;
		int y_playerinv = 0;
		
		switch (type) {
		case BACKPACKSMALL:
			x_backpackinv = 44;
			y_playerinv = 84;
			break;
		case BACKPACKMEDIUM:
			x_backpackinv = 26;
			y_playerinv = 120;
			break;
		case BACKPACKBIG:
			x_backpackinv = 8;
			y_playerinv = 156;
			break;
		}
		
		for (int i = 0; i < type.getX(); i++) {
			for (int j = 0; j < type.getY(); j++) {
				this.addSlotToContainer(new Slot(inventorybackpack, j + i * type.getY(), j * 18 + x_backpackinv, i * 18 + y_backpackinv));
			}
		}
		
		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				for (int j = 0; j < 9; j++) {
					this.addSlotToContainer(new Slot(inventoryplayer, j + i * 9 + 9, j * 18 + x_playerinv, i * 18 + y_playerinv));
				}
			} else {
				for (int j = 0; j < 9; j++) {
					this.addSlotToContainer(new Slot(inventoryplayer, j, j * 18 + x_playerinv, i * 18 + 4 + y_playerinv));
				}
			}
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	public void saveToNBT(ItemStack itemStack) {
		if (!itemStack.hasTagCompound()) {
			itemStack.setTagCompound(new NBTTagCompound());
		}
		inv.writeToNBT(itemStack.getTagCompound());
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index < type.getCount()) {
				if (!this.mergeItemStack(itemstack1, type.getCount(), this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, type.getCount(), false)) {
				return null;
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}
		this.updateNotification = true;
		return itemstack;
	}
	
	@Override
	public ItemStack slotClick(int slotId, int clickedButton, int mode, EntityPlayer playerIn) {
		Slot tmpSlot;
		if (slotId >= 0 && slotId < inventorySlots.size()) {
			tmpSlot = (Slot) inventorySlots.get(slotId);
		} else {
			tmpSlot = null;
		}
		if (tmpSlot != null) {
			if (tmpSlot.isSlotInInventory(playerIn.inventory, playerIn.inventory.currentItem)) {
				return tmpSlot.getStack();
			}
		}
		// TODO Bad way to block mode 2! If anyone had an idea to fix, please
		// create an issue
		if (mode == 2) {
			return null;
		}
		this.updateNotification = true;
		return super.slotClick(slotId, clickedButton, mode, playerIn);
	}
	
}
