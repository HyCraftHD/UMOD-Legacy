package net.hycrafthd.umod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;

public abstract class InventoryBackPackBase implements IInventory {

	protected ItemStack[] inv;
	protected int size;

	public InventoryBackPackBase() {
	}

	@Override
	public int getSizeInventory() {
		return this.inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inv[index];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		if (inv[slot] == null)
			return null;
		ItemStack returnStack;
		if (inv[slot].stackSize > count) {
			returnStack = inv[slot].splitStack(count);
		} else {
			returnStack = inv[slot];
			inv[slot] = null;
		}
		onInventoryChanged();
		return returnStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack returnStack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return returnStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if (0 <= slot && slot < size) {
			inv[slot] = itemStack;
		}

	}

	public void onInventoryChanged() {
		for (int i = 0; i < size; i++) {
			ItemStack tempStack = getStackInSlot(i);
			if (tempStack != null && tempStack.stackSize == 0) {
				setInventorySlotContents(i, null);
			}
		}
	}

	public void increaseSize(int i) {
		ItemStack[] newInventory = new ItemStack[size + i];
		System.arraycopy(inv, 0, newInventory, 0, size);
		inv = newInventory;
		size = size + i;
	}

	abstract public void readFromNBT(NBTTagCompound myCompound);

	abstract public void writeToNBT(NBTTagCompound myCompound);

	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentText(getName());
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer myPlayer) {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

}
