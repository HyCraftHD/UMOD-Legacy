package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.container.ContainerCraftFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class TileEntityCraftFurnance extends TileEntityBase {

	public ItemStack[] stack = new ItemStack[10];
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 9;
	}

	@Override
	public int getSizeInventory() {
		return stack.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stack[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.stack[index] != null)
        {
            ItemStack itemstack;

            if (this.stack[index].stackSize <= count)
            {
                itemstack = this.stack[index];
                this.stack[index] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.stack[index].splitStack(count);

                if (this.stack[index].stackSize == 0)
                {
                    this.stack[index] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.stack[index] != null)
        {
            ItemStack itemstack = this.stack[index];
            this.stack[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.stack[index] = stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
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
		stack = new ItemStack[stack.length];
	}

	@Override
	public String getName() {
		return "tile.entity.craftfurnance";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerCraftFurnace(this, playerInventory.player, pos, this.worldObj);
	}

	@Override
	public String getGuiID() {
		return "4";
	}

}
