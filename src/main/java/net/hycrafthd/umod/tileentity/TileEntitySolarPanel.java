package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.UUtils;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;

public class TileEntitySolarPanel extends TileEntity implements IPowerProvieder{
	
	public int storedpower = 0;
	public int MAXIMUM_POWER = 300000;
	public boolean work;
	public String er = null;

	@Override
	public int getStoredPower() {
		return storedpower;
	}

	@Override
	public void addPower(int power) {
		storedpower += power;
	}

	@Override
	public int getPower(int powerneed) {
		storedpower -= powerneed;
		return powerneed;
	}

	@Override
	public boolean canGetPower(int power) {
		if(storedpower - power >= 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean canAddPower(int power) {
		if(power + storedpower <= MAXIMUM_POWER){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		if(this.worldObj.canSeeSky(this.pos)){
			er = "Can't see sky";
			work = false;
			return;
		}
		if(!this.worldObj.provider.isSurfaceWorld()){
			er = "Your not in Surface";
			work = false;
			return;
		}
		if(this.worldObj.getLight(this.pos) >= 4){
			er = "It's Night";
			work = false;
			return;
		}
		if(this.worldObj.isRaining()){
			er = "It's Rainig";
			work = false;
			return;
		}
		if(canAddPower(UUtils.inUE(3))){
			addPower(UUtils.inUE(3));
			er = null;
			work = true;
		}else{
			er = "Maximum Storage reached";
			work = false;
		}
	}
	
	@Override
	public NBTTagCompound getTileData() {
		return ((S35PacketUpdateTileEntity)getDescriptionPacket()).getNbtCompound();
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
    	this.readFromNBT(tag);
	}

	
	@Override
    public Packet getDescriptionPacket() {
	    	NBTTagCompound tag = new NBTTagCompound();
	    	this.writeToNBT(tag);
	    	return new S35PacketUpdateTileEntity(getPos(), getBlockMetadata(), tag);
    }
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setInteger("Stored", storedpower);
		super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.storedpower = compound.getInteger("Stored");
		super.readFromNBT(compound);
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
		return er;
	}

	@Override
	public boolean hasPower() {
		return storedpower > 0;
	}
}
