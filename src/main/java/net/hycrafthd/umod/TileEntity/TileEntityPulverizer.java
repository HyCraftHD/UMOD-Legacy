package net.hycrafthd.umod.TileEntity;

import akka.util.Index;
import net.hycrafthd.umod.UModRegistery;
import net.hycrafthd.umod.API.PulverizerRecepie;
import net.hycrafthd.umod.block.BlockOres;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;

public class TileEntityPulverizer extends TileEntityBase{

	private ItemStack[] stack = new ItemStack[4];
	private String pl = null;
	
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
	    if(stack[index].stackSize > count){
	    	stack[index].stackSize -= count;
	    }else{
	    	stack[index] = null;
	    }
		return stack[index];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		return stack[index];
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
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(pl == null || pl.equals(player.getName())){
			return true;
		}
		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "THIS IS LOCKED BY " + pl));
		return false;
	}

	public void signPlayer(EntityPlayer pl){
		if(pl != null){
		pl.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Pulveriser Locked for Player: " + pl.getName()));
		this.pl = pl.getName();
		}else{
		this.pl = null;
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		switch (index) {
		case 0:
			return false;
		case 1:
			return false;
		case 2: 
			return false;
		case 3:
			Block in = Block.getBlockFromItem(stack.getItem());
			if(in != null){
				if(in instanceof BlockOre || in instanceof BlockOres){
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@Override
	public int getField(int id) {
		return time;
	}

	@Override
	public void setField(int id, int value) {
		time = value;
	}

	@Override
	public int getFieldCount() {
		return 1;
	}

	@Override
	public void clear() {
		stack = new ItemStack[this.getSizeInventory()];
	}
	
	@Override
	public String getName() {
		return "tile.entity.Pulveriser";
	}

	public String getSigndPlayerName(){
		return pl;
	}
	
	private int time = 0;
	
	@Override
	public void update() {
		ItemStack[] args = UModRegistery.isRecepie(new PulverizerRecepie(stack[3], null, null));
		if(args != null){
			if(stack[2] != null && stack[2].stackSize > 64){
				time = 0;
				return;
			}
			if(stack[0] == null || stack[1] == null){
				time++;
			    if(time == 100){
			    	if(stack[3].stackSize == 1){
			    		stack[3] = null;
			    	}else{
			    		stack[3].stackSize--;
			    	}
			    	finishItem(0, args[0]);
			    	finishItem(1, args[1]);
			    	finishItem(2, args[2]);
			    	time = 0;
			    }
			    return;
			}
			if(stack[0].stackSize <= 64 - args[0].stackSize && stack[1].stackSize <= 64 - args[1].stackSize){
		    time++;
		    if(time == 100){
		    	if(stack[3].stackSize == 1){
		    		stack[3] = null;
		    	}else{
		    		stack[3].stackSize--;
		    	}
		    	finishItem(0, args[0]);
		    	finishItem(1, args[1]);
		    	finishItem(2, args[2]);
		    	time = 0;
		    }
			}
		}else{
			time = 0;
		}
	}
	
	public int getTime(){
		return time;
	}
	
	private void finishItem(int in,ItemStack is){
		ItemStack s = stack[in];
		if(s == null){
			stack[in] = is;
		}else if(s.isItemEqual(is) && is != null){
			stack[in].stackSize += is.stackSize;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("Time", time);
		if(pl != null){
		compound.setString("Player", pl);
		}
		 NBTTagList nbttaglist = new NBTTagList();

	     for (int i = 0; i < this.stack.length; ++i)
	     {
	         if (this.stack[i] != null)
	         {
	             NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	             nbttagcompound1.setByte("Slot", (byte)i);
	             this.stack[i].writeToNBT(nbttagcompound1);
	             nbttaglist.appendTag(nbttagcompound1);
	         }
	     }

	     compound.setTag("Items", nbttaglist);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("Player")){
			this.pl = compound.getString("Player");
		}
		this.time = compound.getInteger("Time");
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.stack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.stack.length)
            {
                this.stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
           }
        }
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if(side.equals(EnumFacing.UP)){
			return new int[]{3};
		}else if(side.equals(EnumFacing.DOWN)){
			return new int[]{0,1,2};
		}
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if(direction.equals(EnumFacing.UP)){
			return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if(index < 3){
			return true;
		}
		return false;
	}

}
