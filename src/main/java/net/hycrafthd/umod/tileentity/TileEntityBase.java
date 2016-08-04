package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.*;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IInteractionObject;

public abstract class TileEntityBase extends TileEntity implements ISidedInventory,IInteractionObject,IPowerProvieder{
	
	public String customname = null;

	@Override
	public boolean hasCustomName() {
		return customname != null;
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
	public IChatComponent getDisplayName() {
		return new ChatComponentText(this.getName());
	}
	
	public final String 
	ITEM_NBT = "items_nbt",
	ENERGY_NBT = "energy_nbt",
	INT_ENERGY = "Energy",
	IO_NBT = "io_nbt",
	OTHER_NBT = "other_nbt";
	
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		NBTTagCompound tagCom = pkt.getNbtCompound();
		this.readFromNBT(tagCom);
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tagCom = new NBTTagCompound();
		this.writeToNBT(tagCom);
		return new S35PacketUpdateTileEntity(pos, getBlockMetadata(), tagCom);
	}
	
	@Override
	public final void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagCompound tagEnergy = new NBTTagCompound();
		this.writeEnergyToNBT(tagEnergy);
		compound.setTag(ENERGY_NBT, tagEnergy);
		NBTTagCompound tagIO = new NBTTagCompound();
		this.writeIOModeToNBT(tagIO);
		compound.setTag(IO_NBT, tagIO);
		NBTTagCompound tagSonstiges = new NBTTagCompound();
		this.writeOtherToNBT(tagSonstiges);
		compound.setTag(OTHER_NBT, tagSonstiges);
/**
 * @author MrTroble
 *  <strong>IMPORTANT</strong>: have to be last 
 */
		NBTTagCompound tagItems = new NBTTagCompound();
		this.writeItemsToNBT(tagItems);
		compound.setTag(ITEM_NBT, tagItems);
	}
	
	@Override
	public final void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.readEnergyFromNBT(compound.getCompoundTag(ENERGY_NBT));
		this.readIOModeFromNBT(compound.getCompoundTag(IO_NBT));
		this.readItemsFromNBT(compound.getCompoundTag(ITEM_NBT));
        this.readOtherFromNBT(compound.getCompoundTag(OTHER_NBT));
	}

	public abstract void writeOtherToNBT(NBTTagCompound tagSonstiges);

	public abstract void writeIOModeToNBT(NBTTagCompound tagIO);

	public void writeEnergyToNBT(NBTTagCompound tagEnergy) {
		 tagEnergy.setDouble(INT_ENERGY, strpo);
		
	}

	public abstract void writeItemsToNBT(NBTTagCompound tagItems);
	
	public abstract void readOtherFromNBT(NBTTagCompound tagSonstiges);

	public void readIOModeFromNBT(NBTTagCompound tagIO) {
	
	}

	public void readEnergyFromNBT(NBTTagCompound tagEnergy) {
		strpo = tagEnergy.getDouble(INT_ENERGY);
		
	}

	public abstract void readItemsFromNBT(NBTTagCompound tagItems);
	

	protected double strpo = 0;
	protected String error;
	protected static int MAXIMUM_POWER = 4000;
	protected boolean work;
	
	@Override
	public double getStoredPower() {
		return strpo;
	}

	@Override
	public void addPower(double power) {
		this.markDirty();
		if(strpo >= MAXIMUM_POWER){
			return;
		}
		strpo += power;
	}

	@Override
	public double getPower(double powerneed) {
		return 0;
	}

	@Override
	public boolean canGetPower(BlockPos p,double power) {
		return false;
	}

	@Override
	public boolean canAddPower(BlockPos p,double power) {
		return strpo + power <= MAXIMUM_POWER;
	}

	@Override
	public double getMaximalPower() {
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
	public void setEnergy(double coun) {
		this.strpo = coun;
	}
	
	@Override
	public boolean hasPower() {
		return strpo > 0;
	}
	
}
