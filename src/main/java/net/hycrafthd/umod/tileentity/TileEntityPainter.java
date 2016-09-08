package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.util.EnumFacing;

public class TileEntityPainter extends TileEntityBase implements IUpdatePlayerListBox ,ISliderEntry, IWorldView{
	
	private ItemStack[] stack = new ItemStack[6];
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		switch (side) {
		case DOWN:
			return new int[] { 2, 4 };
		case EAST:
			break;
		case NORTH:
			break;
		case SOUTH:
			break;
		case UP:
			return new int[] { 0, 1, 3 };
		case WEST:
			break;
		default:
			break;
		}
		return new int[] {};
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if (index == 0 || index == 1 || index == 3) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index == 2 || index == 4) {
			return true;
		}
		return false;
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
		if (this.stack[index] != null) {
			ItemStack itemstack;
			
			if (this.stack[index].stackSize <= count) {
				itemstack = this.stack[index];
				this.stack[index] = null;
				return itemstack;
			} else {
				itemstack = this.stack[index].splitStack(count);
				
				if (this.stack[index].stackSize == 0) {
					this.stack[index] = null;
				}
				
				return itemstack;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.stack[index] != null) {
			ItemStack itemstack = this.stack[index];
			this.stack[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.stack[index] = stack;
		this.markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (index != 2 && index != 4) {
			return true;
		}
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
		stack = new ItemStack[getSizeInventory()];
	}
	
	@Override
	public String getName() {
		return "tilepainter";
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return null;
	}
	
	@Override
	public String getGuiID() {
		return "6";
	}
	
	public static final String
	
	ENUMFACING_OUTPUT = "OP", ENUMFACING_INPUT = "IP", INT_ENERGY = "Energy", SHORT_TIME = "Time", BYTE_SLOTS = "slot", LIST_ITEMS = "items", STRING_PLAYER = "play";
	
	private EnumFacing enumfI;
	private EnumFacing enumfO;
	
	@Override
	public void writeOtherToNBT(NBTTagCompound tagSonstiges) {
		// tagSonstiges.setShort(SHORT_TIME, (short) time);
	}
	
	@Override
	public void writeIOModeToNBT(NBTTagCompound tagIO) {
		UMod.log.info("Write IO");
		tagIO.setByte(ENUMFACING_OUTPUT, (byte) DirectionUtils.getShortFromFacing(enumfO));
		tagIO.setByte(ENUMFACING_INPUT, (byte) DirectionUtils.getShortFromFacing(enumfI));
	}
	
	@Override
	public void writeItemsToNBT(NBTTagCompound tagItems) {
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < stack.length; ++i) {
			if (stack[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte(BYTE_SLOTS, (byte) i);
				stack[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		tagItems.setTag(LIST_ITEMS, nbttaglist);
	}
	
	@Override
	public void readOtherFromNBT(NBTTagCompound tagSonstiges) {
	}
	
	@Override
	public void readIOModeFromNBT(NBTTagCompound tagIO) {
		enumfI = DirectionUtils.getFacingFromShort(tagIO.getShort(ENUMFACING_INPUT));
		enumfO = DirectionUtils.getFacingFromShort(tagIO.getShort(ENUMFACING_OUTPUT));
		
	}
	
	@Override
	public void readItemsFromNBT(NBTTagCompound tagItems) {
		NBTTagList nbttaglist = tagItems.getTagList(LIST_ITEMS, 10);
		stack = new ItemStack[this.getSizeInventory()];
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int b0 = nbttagcompound1.getByte(BYTE_SLOTS);
			
			if (b0 >= 0 && b0 < stack.length) {
				stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public double getPowerProducNeeds() {
		return 0;
	}
	
	@Override
	public String getEnergyClass() {
		return "";
	}
	
	@Override
	public boolean needsPower() {
		return true;
	}
	
	@Override
	public boolean productsPower() {
		return false;
	}

	@Override
	public boolean isInput() {
		return false;
	}

	@Override
	public boolean isOutput() {
		return true;
	}
	
	public int[] ids = {0,0,0,100};

	@Override
	public void storeValueForId(int id, int vl) {
		   ids[id] = vl;
	}

	@Override
	public int getValueFromId(int id) {
		return ids[id];
	}

	@Override
	public boolean showPower() {
		return true;
	}

	@Override
	public String[] textToAdd() {
		return null;
	}
	
}
