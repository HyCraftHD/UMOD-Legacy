package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.hycrafthd.umod.utils.WorldUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileEntitySolarPanel extends TileEntity implements IPowerProvieder {

	public int storedpower = 0;
	public int MAXIMUM_POWER = 0;
	public int producing = 0;
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
	public int getStoredPower() {
		return storedpower;
	}

	@Override
	public void setEnergy(int coun) {
		storedpower = coun;
	}
	
	@Override
	public void addPower(int power) {
		storedpower += EnergyUtils.inUE(power);
	}

	@Override
	public int getPower(int powerneed) {
		storedpower -= EnergyUtils.inUE(powerneed);
		return EnergyUtils.inUE(powerneed);
	}

	@Override
	public boolean canGetPower(BlockPos p,int power) {
		if (storedpower - EnergyUtils.inUE(power) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canAddPower(BlockPos p,int power) {
		if (EnergyUtils.inUE(power) + storedpower <= MAXIMUM_POWER) {
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
		if (canAddPower(this.pos,EnergyUtils.inUE(producing))) {
			addPower(EnergyUtils.inUE(producing));
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

	@Override
	public int getPowerProducNeeds() {
		return EnergyUtils.inUE(producing);
	}
}
