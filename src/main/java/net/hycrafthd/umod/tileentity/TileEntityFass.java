package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.container.ContainerFass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class TileEntityFass extends TileEntityBase{

	ItemStack[] stacks = new ItemStack[27];
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stacks[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack stac = stacks[index];
		if(stacks[index].stackSize > count){
			stacks[index].stackSize -= count;
		}else{
			stacks[index] = null;
		}
		return stac;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		return stacks[index];
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
          stacks[index] = stack;		
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
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
		stacks = new ItemStack[27];
	}

	@Override
	public String getName() {
		return "tile.entity.fass";
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerFass(this, playerIn,pos, worldObj);
	}

	@Override
	public String getGuiID() {
		return "4";
	}

}
