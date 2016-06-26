package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.ISignable;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.container.ContainerCraftFurnace;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityCraftFurnance extends TileEntityBase implements IPowerProvieder,ISignable{

	public ItemStack[] stack = new ItemStack[11];
	
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

	int time = 0;
	
	@Override
	public void update() {
		
		ItemStack stac = ModRegistryUtils.isCraftSmelt(new ItemStack[] {stack[0],stack[1],stack[2]}, new ItemStack[] {stack[3],stack[4],stack[5]}, new ItemStack[] {stack[6],stack[7],stack[8]});
		if(stac != null && this.strpo > 150){
			time++;
			this.strpo -= 150;
			if(time == 80){
				time = 0;
		if(stack[9] == null){
			stack[9] = stac.copy();
		}else if(stack[9] != null && stack[9].stackSize < 64 && stack[9].isItemEqual(stac)){
			stack[9].stackSize += stac.stackSize;
		}
		for(int i = 0;i < 9;i++){
			if(stack[i].stackSize > 1){
				stack[i].stackSize--;
			}else{
				stack[i] = null;
			}
		}
			}
		}
	}

	@Override
	public void signPlayer(EntityPlayer pl) {
		
	}

	@Override
	public String getSignedPlayerName() {
		return null;
	}

	@Override
	public void writeOtherToNBT(NBTTagCompound tagSonstiges) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeIOModeToNBT(NBTTagCompound tagIO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeItemsToNBT(NBTTagCompound tagItems) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readOtherFromNBT(NBTTagCompound tagSonstiges) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readIOModeFromNBT(NBTTagCompound tagIO) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void readItemsFromNBT(NBTTagCompound tagItems) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getPowerProducNeeds() {
		return 10;
	}

	@Override
	public String getEnergyClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean needsPower() {
		return true;
	}

	@Override
	public boolean productsPower() {
		return false;
	}
	
	
	
}
