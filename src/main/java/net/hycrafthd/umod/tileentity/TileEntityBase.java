package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IIOMode;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
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
		 tagEnergy.setInteger(INT_ENERGY, strpo);
		
	}

	public abstract void writeItemsToNBT(NBTTagCompound tagItems);
	
	public abstract void readOtherFromNBT(NBTTagCompound tagSonstiges);

	public void readIOModeFromNBT(NBTTagCompound tagIO) {
	
	}

	public void readEnergyFromNBT(NBTTagCompound tagEnergy) {
		strpo = tagEnergy.getInteger(INT_ENERGY);
		
	}

	public abstract void readItemsFromNBT(NBTTagCompound tagItems);
	

	protected int strpo = 0;
	protected String error;
	protected static int MAXIMUM_POWER = 4000;
	protected boolean work;
	
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
	public void setEnergy(int coun) {
		this.strpo = coun;
	}
	
	@Override
	public boolean hasPower() {
		return strpo > 0;
	}
	
}
