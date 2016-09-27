package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.container.ContainerPulverizer;
import net.hycrafthd.umod.utils.*;
import net.minecraft.block.Block;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.*;

public class TileEntityPulverizer extends TileEntityLockable implements IPowerProvieder, IIOMode,
                                                                        ISidedInventory, IWorldView{
	
	private ItemStack[] stack = new ItemStack[5];
	private EnumFacing enumfI;
	private EnumFacing enumfO;
	private double strpo;
	
	public boolean isBurning = false;
	
	@Override
	public int getSizeInventory() {
		return stack.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int index) {
		return stack[index];
	}
	
	@Override
	public void setEnergy(double coun) {
		strpo = coun;
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
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
//		if (pl == null || pl.equals(player.getName())) {
//			return true;
//		}
//		player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "THIS IS LOCKED BY " + pl));
		return false;
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
			if (in != null) {
				if (in instanceof BlockOres) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	@Override
	public int getField(int id) {
		return 0;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
//		if (pl != null && !player.getName().equals(pl)) {
//			player.closeScreen();
//			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "THIS PULVERIZER IS LOCKED BY " + pl));
//		}
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
		for (int i = 0; i < this.stack.length; ++i) {
			this.stack[i] = null;
		}
	}
	
	@Override
	public String getName() {
		return "tile.entity.Pulveriser";
	}
	
	private int time = 0;
	public boolean work = false;
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new S35PacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}
	
	@Override
	public void update() {
		ItemStack[] args = ModRegistryUtils.isRecepie(stack[3]);
		if(args != null && this.strpo > 10){
			worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.pos.getX() + 0.5, this.pos.getY() + 0.75, this.pos.getZ() + 0.5, 0, 0, 0, 1);
			worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.pos.getX() + 0.5, this.pos.getY() + 0.25, this.pos.getZ() + 0.5, 0, 0, 0, 1);
				if (isAddebal(0, args[0]) && isAddebal(1, args[1]) && isAddebal(2, args[2])) {
					work = true;
					isBurning = true;
					if(time >= 100){
						time = 0;
						this.decrStackSize(3, 1);
						if(stack[0] == null){
							stack[0] = new ItemStack(args[0].getItem());
						}else{
							stack[0].stackSize++;
						}
						if(stack[1] == null){
							stack[1] = new ItemStack(args[1].getItem());
						}else{
							stack[1].stackSize++;
						}
						if(args[2] != null){
							if(stack[2] == null){
								stack[2] = new ItemStack(args[2].getItem());
							}else{
								stack[2].stackSize++;
							}
						}
						this.markDirty();
						this.getPower(1000);
					}
					time++;	
				}else{
					time = 0;
					this.markDirty();
					work = false;
					isBurning = false;
				}
		}else{
			time = 0;
			this.markDirty();
			work = false;
			isBurning = false;
		}
	}
	
	public int getTime() {
		return time;
	}
	
	public boolean isAddebal(int i,ItemStack st){
		return (stack[i] == null || (stack[i].stackSize < 64 && stack[i].getItem().equals(st.getItem())));
	}
	
	public void addTo(int i,ItemStack st){
		
	}
	
	public static final String
	
	ENUMFACING_OUTPUT = "OP", ENUMFACING_INPUT = "IP", INT_ENERGY = "Energy", SHORT_TIME = "Time", BYTE_SLOTS = "slot", LIST_ITEMS = "items", STRING_PLAYER = "play";
	
	public EnumFacing getEnumInput() {
		return enumfI;
	}
	
	public EnumFacing getEnumOutput() {
		return enumfO;
	}
	
	@Override
	public void setEnumInput(EnumFacing fac) {
		enumfI = fac;
	}
	
	@Override
	public void setEnumOutput(EnumFacing fac) {
		enumfO = fac;
	}
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if ((enumfI == null && side.equals(EnumFacing.UP)) || side.equals(enumfI)) {
			return new int[] { 3 };
		} else if ((enumfO == null && side.equals(EnumFacing.DOWN)) || side.equals(enumfO)) {
			return new int[] { 0, 1, 2 };
		}
		return new int[] {};
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if (direction.equals(enumfI)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		if (index < 3) {
			return true;
		}
		return false;
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerPulverizer((IInventory) playerIn.worldObj.getTileEntity(pos), playerIn, playerIn.worldObj);
	}
	
	@Override
	public String getGuiID() {
		return "0";
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
	}
	
	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public double getPowerProducNeeds() {
		return 10;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setByte(SHORT_TIME, (byte) time);
		tag.setByte(ENUMFACING_OUTPUT, (byte) DirectionUtils.getShortFromFacing(enumfO));
		tag.setByte(ENUMFACING_INPUT, (byte) DirectionUtils.getShortFromFacing(enumfI));
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < stack.length; ++i) {
			if (stack[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte(BYTE_SLOTS, (byte) i);
				stack[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag(LIST_ITEMS, nbttaglist);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		enumfI = DirectionUtils.getFacingFromShort(tag.getByte(ENUMFACING_INPUT));
		enumfO = DirectionUtils.getFacingFromShort(tag.getByte(ENUMFACING_OUTPUT));
		time = tag.getByte(SHORT_TIME);
		NBTTagList nbttaglist = tag.getTagList(LIST_ITEMS, 10);
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
	public EnumFacing getFacing(int i) {
		switch (i) {
		case 0:
			return enumfI;
		case 1:
			return enumfO;
		}
		return null;
	}
	
	@Override
	public void setFacing(int i, EnumFacing face) {
		switch (i) {
		case 0:
			enumfI = face;
			break;
		case 1:
			enumfO = face;
			break;
		}
	}
	
	@Override
	public int hasSomefacing(EnumFacing i) {
		if (enumfI.equals(i)) {
			return 0;
		}
		if (enumfO.equals(i)) {
			return 1;
		}
		return -1;
	}
	
	@Override
	public int getModeCount() {
		return 2;
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

	@Override
	public double getStoredPower() {
		return strpo;
	}

	@Override
	public void addPower(double power) {
		strpo += power;
	}

	@Override
	public double getPower(double powerneed) {
		strpo -= powerneed;
		return powerneed;
	}

	@Override
	public double getMaximalPower() {
		return 4000;
	}

	@Override
	public boolean isWorking() {
		return work;
	}

	@Override
	public String getErrorMessage() {
		return "";
	}

	@Override
	public boolean hasPower() {
		return false;
	}

	@Override
	public boolean showPower() {
		return true;
	}

	@Override
	public String[] textToAdd() {
		if(this.getStackInSlot(3) != null){
		return new String[] {this.getStackInSlot(3).getDisplayName(),"Progress " + this.getField(0) + "/100"};
		}
		return null;
	}


}
