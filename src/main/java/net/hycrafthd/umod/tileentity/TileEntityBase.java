package net.hycrafthd.umod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;

public abstract class TileEntityBase extends TileEntity implements ISidedInventory,IInteractionObject{
	
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
 *  <strong>IMPORTANT</strong>: hav to be last 
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

	public abstract void writeEnergyToNBT(NBTTagCompound tagEnergy);

	public abstract void writeItemsToNBT(NBTTagCompound tagItems);
	
	public abstract void readOtherFromNBT(NBTTagCompound tagSonstiges);

	public abstract void readIOModeFromNBT(NBTTagCompound tagIO);

	public abstract void readEnergyFromNBT(NBTTagCompound tagEnergy);

	public abstract void readItemsFromNBT(NBTTagCompound tagItems);
}
