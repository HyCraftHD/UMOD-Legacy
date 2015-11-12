package net.hycrafthd.umod.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class TileEntityBase extends TileEntity implements ISidedInventory, IUpdatePlayerListBox{
	
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
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
    	this.readFromNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		 if (compound.hasKey("name", 8))
	     {
	       this.customname = compound.getString("name");
	     }
	}
	
	@Override
    public Packet getDescriptionPacket() {
	    	NBTTagCompound tag = new NBTTagCompound();
	    	this.writeToNBT(tag);
	    	return new S35PacketUpdateTileEntity(getPos(), getBlockMetadata(), tag);
    }
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(this.hasCustomName()){
		compound.setString("name", customname);
		}
	}
	
	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentText(this.getName());
	}
}
