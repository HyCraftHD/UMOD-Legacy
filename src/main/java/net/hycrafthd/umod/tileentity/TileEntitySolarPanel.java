package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.utils.WorldUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.*;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileEntitySolarPanel extends TileEntity implements IPowerProvieder {
	
	public double storedpower = 0;
	public double MAXIMUM_POWER = 0;
	public double producing = 0;
	public boolean work;
	public String unlocalizedname;
	public String er = null;
	
	public TileEntitySolarPanel() {
	}
	
	public TileEntitySolarPanel(int produce, int max, String str) {
		this.producing = produce;
		this.MAXIMUM_POWER = max;
		this.unlocalizedname = str;
	}
	
	@Override
	public double getStoredPower() {
		return storedpower;
	}
	
	@Override
	public void setEnergy(double coun) {
		storedpower = coun;
	}
	
	@Override
	public void addPower(double power) {
		storedpower += power;
	}
	
	@Override
	public double getPower(double powerneed) {
		storedpower -= powerneed;
		return powerneed;
	}
	
	@Override
	public boolean canGetPower(BlockPos p, double power) {
		if (storedpower - power >= 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canAddPower(BlockPos p, double power) {
		if (power + storedpower <= MAXIMUM_POWER) {
			return true;
		}
		return false;
	}
	
	@Override
	public void update() {
		if (!WorldUtils.isBlockover(worldObj, pos)) {
			er = "Can't see sky";
			work = false;
			return;
		}
		if (!this.worldObj.provider.isSurfaceWorld()) {
			er = "Your not in Surface";
			work = false;
			return;
		}
		if (WorldUtils.isNight(worldObj)) {
			er = "It's Night";
			work = false;
			return;
		}
		if (this.worldObj.isRaining()) {
			er = "It's Rainig";
			work = false;
			return;
		}
		if (canAddPower(this.pos, producing)) {
			addPower(producing);
			er = null;
			work = true;
		} else {
			er = "Maximum Storage reached";
			work = false;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setDouble("Stored", storedpower);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.storedpower = compound.getDouble("Stored");
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
		return er;
	}
	
	@Override
	public boolean hasPower() {
		return storedpower > 0;
	}
	
	@Override
	public double getPowerProducNeeds() {
		return producing;
	}
	
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
	public String getEnergyClass() {
		return null;
	}
	
	@Override
	public boolean needsPower() {
		return false;
	}
	
	@Override
	public boolean productsPower() {
		return true;
	}

	@Override
	public boolean isInput() {
		return true;
	}

	@Override
	public boolean isOutput() {
		return false;
	}
}
