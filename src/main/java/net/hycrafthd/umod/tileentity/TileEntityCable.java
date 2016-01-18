package net.hycrafthd.umod.tileentity;

import java.util.ArrayList;

import net.hycrafthd.umod.api.IPlugabel;
import net.hycrafthd.umod.api.energy.EnergyAPI;
import net.hycrafthd.umod.api.energy.IPipeRange;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileEntityCable extends TileEntity implements IPlugabel, IPowerProvieder, IPipeRange {

	public int Maximum_Power;
	public int stored;
	public int loos;
	private ArrayList<BlockPos> getter = new ArrayList<BlockPos>();

	public TileEntityCable() {
	}

	public TileEntityCable(int maxpower, int pipelooseone) {
		Maximum_Power = EnergyUtils.inUE(maxpower);
		loos = pipelooseone;
	}

	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		TileEntity et = w.getTileEntity(p);
		if (et instanceof IPowerProvieder) {
			return true;
		}
		return false;
	}

	@Override
	public void update() {
	     EnergyAPI api = new EnergyAPI(this);
	     api.transferEnergy();
	     
	}

	public int passtpip = 0;

	@Override
	public int getStoredPower() {
		return stored;
	}

	@Override
	public void addPower(int power) {
		stored += EnergyUtils.inUE(power);
		this.markDirty();
	}

	@Override
	public int getPower(int powerneed) {
		stored -= EnergyUtils.inUE(powerneed);
		this.markDirty();
		return powerneed;
	}

	@Override
	public boolean canGetPower(BlockPos pos,int power) {
		if (stored - EnergyUtils.inUE(power) >= 0 && !getter.contains(pos)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canAddPower(BlockPos pos,int power) {
		if (EnergyUtils.inUE(power) + stored <= Maximum_Power) {
			getter.add(pos);
			return true;
		}
		return false;
	}

	@Override
	public int getMaximalPower() {
		return Maximum_Power;
	}

	@Override
	public boolean isWorking() {
		return true;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public boolean hasPower() {
		return stored > 0;
	}

	@Override
	public int getPastPipeCount() {
		return passtpip;
	}

	@Override
	public NBTTagCompound getTileData() {
		return ((S35PacketUpdateTileEntity) getDescriptionPacket()).getNbtCompound();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setShort("Stored", (short) stored);
		compound.setInteger("Max", Maximum_Power);
		super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.stored = compound.getShort("Stored");
		this.Maximum_Power = compound.getShort("Max");
		super.readFromNBT(compound);
	}

	@Override
	public int getPowerProducNeeds() {
		return EnergyUtils.inUE(Maximum_Power);
	}

	@Override
	public int addBlock(int count) {
		int i = getPastPipeCount();
		passtpip = count;
		return i;
	}

	@Override
	public int getMaximalRange() {
		return loos;
	}

	@Override
	public void remove(int count) {
		getPower(count);
	}

	@Override
	public void clearPast() {
		passtpip = 0;
	}

}
