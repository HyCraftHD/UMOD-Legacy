package net.hycrafthd.umod.tileentity;

import com.sun.javafx.scene.traversal.Direction;

import net.hycrafthd.umod.Logger;
import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.api.IGuiProvider;
import net.hycrafthd.umod.api.ISignable;
import net.hycrafthd.umod.api.PulverizerRecepie;
import net.hycrafthd.umod.api.energy.EnergyAPI;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.container.ContainerPulverizer;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;

public class TileEntityPulverizer extends TileEntityBase implements 
                               IPowerProvieder,IGuiProvider,ISignable{

	private ItemStack[] stack = new ItemStack[5];
	private String pl;
	private EnumFacing enumfI;
	private EnumFacing enumfO;
	
	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stack[index];
	}
	
	@Override
	public void setEnergy(int coun) {
		strpo = coun;
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
		this.markDirty();
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

	@Override
	public void signPlayer(EntityPlayer pl){
		if(pl != null){
		pl.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Pulveriser Locked for Player: " + pl.getName()));
		this.pl = pl.getName();
		}else{
		this.pl = null;
		}
		this.markDirty();
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
				if(in instanceof BlockOres){
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
	public void openInventory(EntityPlayer player) {
		if(pl != null && !player.getName().equals(pl)){
			player.closeScreen();
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "THIS PULVERIZER IS LOCKED BY " + pl));
		}
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
		for (int i = 0; i < this.stack.length; ++i)
        {
            this.stack[i] = null;
        }
		this.markDirty();
	}
	
	@Override
	public String getName() {
		return "tile.entity.Pulveriser";
	}
	
	private int time = 0;
	public boolean work = false;
	
	@Override
	public void update() {
		if(stack[3] != null){
		ItemStack[] args = ModRegistryUtils.isRecepie(stack[3].copy());
		if(args != null && strpo > 10){
			if(stack[2] != null && stack[2].stackSize > 64){
				time = 0;
				this.markDirty();
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
			    	finishItem(0, args[0].copy());
			    	finishItem(1, args[1].copy());
			    	if(args[2] != null){
				    	finishItem(2, args[2].copy());
				    }			    	
			    	time = 0;
			    }
			    strpo -= EnergyUtils.inUE(10);
			    work = true;
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
		    	finishItem(0, args[0].copy());
		    	finishItem(1, args[1].copy());
		    	if(args[2] != null){
		    	finishItem(2, args[2].copy());
		    	}
		    	time = 0;
			    work = true;
			    strpo -= EnergyUtils.inUE(10);
		    }
			}
			this.markDirty();
		}else{
		    work = false;
		}
		}
		EnergyAPI api = new EnergyAPI(this);
		api.transferEnergy();
		api.tranferFromBattery(this.stack[4]);
	}
	
	public int getTime(){
		return time;
	}
	
	private void finishItem(int in,ItemStack is){
		ItemStack s = stack[in];
		if(is != null && is != null &&s == null){
			this.stack[in] = is;
		}else if(is != null && s.isItemEqual(is)){
			this.stack[in].stackSize += is.stackSize;
		}
	}
	
	public static final String
	
	ENUMFACING_OUTPUT = "OP",
	ENUMFACING_INPUT = "IP",
	SHORT_ENERGY = "Energy",
	SHORT_TIME = "Time",
	BYTE_SLOTS = "slot",
	LIST_ITEMS = "items",
	STRING_PLAYER = "play";

	public EnumFacing getEnumInput(){
		return enumfI;
	}
	
	public EnumFacing getEnumOutput(){
		return enumfO;
	}
	
	public void setEnumInput(EnumFacing fac){
		Logger.info(fac.toString());
		enumfI = fac;
		Logger.info(enumfI.toString());
	}
	
	public void setEnumOutput(EnumFacing fac){
		enumfO = fac;
	}
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		if((enumfI == null && side.equals(EnumFacing.UP)) || side.equals(enumfI)){
			return new int[]{3};
		}else if((enumfO == null && side.equals(EnumFacing.DOWN)) || side.equals( enumfO )){
			return new int[]{0,1,2};
		}
		return new int[]{};
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		if(direction.equals( enumfI )){
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

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerPulverizer((IInventory) playerIn.worldObj.getTileEntity(pos), playerIn, playerIn.worldObj);
	}

	@Override
	public String getGuiID() {
		return "0";
	}

	public int strpo = 0;
	public String error;
	public static final int MAXIMUM_POWER = 4000;
	
	@Override
	public int getStoredPower() {
		return strpo;
	}

	@Override
	public void addPower(int power) {
		this.markDirty();
		if(strpo >= MAXIMUM_POWER){
			return;
		}
		strpo += EnergyUtils.inUE(power);
	}

	@Override
	public int getPower(int powerneed) {
		return 0;
	}

	@Override
	public boolean canGetPower(BlockPos p,int power) {
		return false;
	}

	@Override
	public boolean canAddPower(BlockPos p,int power) {
		return strpo + EnergyUtils.inUE(power) <= MAXIMUM_POWER;
	}

	@Override
	public int getMaximalPower() {
		return MAXIMUM_POWER;
	}

	@Override
	public boolean isWorking() {
		return work;
	}

	@Override
	public String getErrorMessage() {
		return error;
	}

	@Override
	public boolean hasPower() {
		return strpo > 0;
	}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean hasCustomName() {
		return false;
	}
	
	@Override
	public int getPowerProducNeeds() {
		return EnergyUtils.inUE(10);
	}

	@Override
	public int getGui() {
		return 0;
	}

	@Override
	public String getSignedPlayerName() {
		return pl;
	}

	@Override
	public void writeOtherToNBT(NBTTagCompound tagSonstiges) {
		 tagSonstiges.setShort(SHORT_TIME, (short) this.time);
		 if(pl != null){
		     tagSonstiges.setString(STRING_PLAYER, pl);
	     }
	}

	@Override
	public void writeIOModeToNBT(NBTTagCompound tagIO) {
		UMod.log.info("Write IO");
		tagIO.setByte(ENUMFACING_OUTPUT, (byte) DirectionUtils.getShortFromFacing(enumfO));
		tagIO.setByte(ENUMFACING_INPUT, (byte) DirectionUtils.getShortFromFacing(enumfI));
	}

	@Override
	public void writeEnergyToNBT(NBTTagCompound tagEnergy) {
		 tagEnergy.setShort(SHORT_ENERGY, (short) this.strpo);
		
	}

	@Override
	public void writeItemsToNBT(NBTTagCompound tagItems) {
		NBTTagList nbttaglist = new NBTTagList();

	     for (int i = 0; i < this.stack.length; ++i)
	     {
	         if (this.stack[i] != null)
	         {
	             NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	             nbttagcompound1.setByte(BYTE_SLOTS, (byte) i);
	             this.stack[i].writeToNBT(nbttagcompound1);
	             nbttaglist.appendTag(nbttagcompound1);
	         }
	     }

	    tagItems.setTag(LIST_ITEMS, nbttaglist);
	}

	@Override
	public void readOtherFromNBT(NBTTagCompound tagSonstiges) {
		this.time = tagSonstiges.getShort(SHORT_TIME);
		this.pl = tagSonstiges.getString(STRING_PLAYER);
	}

	@Override
	public void readIOModeFromNBT(NBTTagCompound tagIO) {
		this.enumfI = DirectionUtils.getFacingFromShort(tagIO.getShort(ENUMFACING_INPUT));
		this.enumfO = DirectionUtils.getFacingFromShort(tagIO.getShort(ENUMFACING_OUTPUT));
		
	}

	@Override
	public void readEnergyFromNBT(NBTTagCompound tagEnergy) {
		this.strpo = tagEnergy.getShort(SHORT_ENERGY);
		
	}

	@Override
	public void readItemsFromNBT(NBTTagCompound tagItems) {
		NBTTagList nbttaglist = tagItems.getTagList(LIST_ITEMS, 10);
        this.stack = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int b0 = nbttagcompound1.getByte(BYTE_SLOTS);

            if (b0 >= 0 && b0 < this.stack.length)
            {
                this.stack[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
           }
        }
	}
}
