package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.WorldServer;

public class TileEntitySolarPanel extends TileEntity implements IPowerProvieder{
	
	public int storedpower = 0;
	public static final int MAXIMUM_POWER = 3000;
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
		return (storedpower -= powerneed);
	}

	@Override
	public boolean canGetPower(int power) {
		if(storedpower  - power >= 0){
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
		if(!worldObj.isRemote){
			er = "World isn't Remoted";
			work = false;
			return;
		}
		if(!worldObj.canSeeSky(pos)){
			er = "Can't see sky";
			work = false;
			return;
		}
		if(!worldObj.provider.isSurfaceWorld()){
			er = "Your not in Surface";
			work = false;
			return;
		}
		if(!worldObj.provider.isDaytime()){
			er = "It's Night";
			work = false;
			return;
		}
		if(!worldObj.isRaining()){
			er = "It's Rainig";
			work = false;
			return;
		}
		if(canAddPower(3)){
			addPower(3);
			er = null;
			work = true;
		}else{
			er = "Maximum Storage reached";
			work = false;
		}
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
		super.writeToNBT(compound);
		compound.setInteger("Stored", storedpower);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.storedpower = compound.getInteger("Stored");
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
