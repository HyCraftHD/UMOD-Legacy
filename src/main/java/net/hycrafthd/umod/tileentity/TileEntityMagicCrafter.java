package net.hycrafthd.umod.tileentity;


import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.container.ContainerMagicCrafter;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;

public class TileEntityMagicCrafter extends TileEntityLockable implements IUpdatePlayerListBox , ISidedInventory{

	private ItemStack[] inv = new ItemStack[4];
	
	boolean isCreating = false;
	boolean isCrafting = false;
	
	MagicCrafterRecipe rec;
	MagicCrafterRecipe recipe;
	
	private int count;
		
	public void update(){
		
		/** 0 crystal slot, 1&2 input slot, 3 output slot */
		if(isCreating){
						
			if(!Crystal.isStackCrystal(this.getStackInSlot(0))){
				isCreating = false;
				count = 0;
			}
			
			if(isCreating && count >= 400){
				isCrafting = false;
				this.setInventorySlotContents(0, removeItemFromStack(this.getStackInSlot(0)));
				this.setInventorySlotContents(1, removeItemFromStack(this.getStackInSlot(1)));
				this.setInventorySlotContents(2, removeItemFromStack(this.getStackInSlot(2)));
				this.setInventorySlotContents(3, recipe.getOutput());
				count = 0;
			}
			count++;
			
		}else if(Crystal.isStackCrystal(this.getStackInSlot(0)) && this.getStackInSlot(1) != null && this.getStackInSlot(2) != null && this.getStackInSlot(3) == null){
			
			MagicCrafterRecipe mcr = MagicCrafterRecipe.getRecipe(this.getStackInSlot(1), this.getStackInSlot(2));
			
			if(mcr != null){
				recipe = mcr;
				isCreating = true;				
			}
			
		}
		
	}
	
	private ItemStack removeItemFromStack(ItemStack is){
		if(!(is.stackSize <= 1)){
			return new ItemStack(is.getItem(), is.stackSize -1, is.getMetadata());
		}else{
			return null;
		}
	}
	
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		/** saves the items in the magic crafter block inventory */
		NBTTagList list = new NBTTagList();
		
		for(int i = 0; i < this.getSizeInventory(); i++){
			if(this.getStackInSlot(i) != null){
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(tag);
				list.appendTag(tag);
			}
		}
		
		compound.setTag("ItemStacks", list);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		/** loads the items for the magic crafter block inventory */
		NBTTagList list = compound.getTagList("ItemStacks", 10);
				
		for(int i = 0; i<list.tagCount(); i++){
			
			NBTTagCompound tag = list.getCompoundTagAt(i);
			byte b = tag.getByte("Slot");
			if(b >= 0 && b < this.getSizeInventory()){
				this.setInventorySlotContents(b, ItemStack.loadItemStackFromNBT(tag));
			}
			
		}
	}
	
	
	public int getCount(){
		return count;
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inv[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.inv[index] != null) {
			ItemStack itemstack;
			
			if (this.inv[index].stackSize <= count) {
				itemstack = this.inv[index];
				this.inv[index] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.inv[index].splitStack(count);
				
				if (this.inv[index].stackSize == 0) {
					this.inv[index] = null;
				}
				this.markDirty();
				return itemstack;
			}
		} else {
			this.markDirty();
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		if (this.inv[index] != null) {
			ItemStack itemstack = this.inv[index];
			this.inv[index] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.inv[index] = stack;		
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {		}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inv = new ItemStack[inv.length];
	}

	@Override
	public String getName() {
		return "tile.magiccraft";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerMagicCrafter(this,playerInventory.player,this.pos,this.worldObj);
	}

	@Override
	public String getGuiID() {
		return "magic";
	}

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
		return false;
	}
}
