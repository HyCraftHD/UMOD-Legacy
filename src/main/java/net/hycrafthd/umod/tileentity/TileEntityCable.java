package net.hycrafthd.umod.tileentity;

import java.util.*;

import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.api.energy.*;
import net.hycrafthd.umod.entity.EntityFX;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.*;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;

public class TileEntityCable extends TileEntity implements IPlugabel, ICabel, IUpdatePlayerListBox, IConduitProvider {
	
	public boolean firstrun = false;
	public ItemStack conduit = null;
	public int tun = -1;
	public int idInT = -1;
	public boolean isInit = false;
	public double rate;
	
	public TileEntityCable() {
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
	
	public TileEntityCable(double maxpower, int pipelooseone) {
		rate = maxpower;
	}
	
	@Override
	public void setConduit(ItemStack b) {
		conduit = b;
	}
	
	@Override
	public boolean hasConduit() {
		return conduit != null;
	}
	
	@Override
	public ItemStack getConduit() {
		return conduit;
	}
	
	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		TileEntity et = w.getTileEntity(p);
		if (et instanceof IPowerProvieder || et instanceof ICabel) {
			return true;
		}
		return false;
	}
	
	@Override
	public NBTTagCompound getTileData() {
		return ((S35PacketUpdateTileEntity) getDescriptionPacket()).getNbtCompound();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		compound.setDouble("Rate", rate);
		super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.rate = compound.getDouble("Rate");
		super.readFromNBT(compound);
	}
	
	@Override
	public String getEnergyClass() {
		return "";
	}
	
	protected boolean isout = false, isin = false;
	
	@Override
	public boolean isInput() {
		return getInputs().length > 0;
	}
	
	@Override
	public boolean isOutput() {
		return getOutputs().length > 0;
	}
	
	@Override
	public void addToTunnel(ICabel cab) {
		TunnelHolder.getUETunnel(tun).add(cab);
	}
	
	@Override
	public ICabel[] getOutputsFromTunnel() {
		return TunnelHolder.getUETunnel(tun).getOutput();
	}
	
	@Override
	public ICabel[] getInputsFromTunnel() {
		return TunnelHolder.getUETunnel(tun).getInput();
	}
	
	@Override
	public int getTunnelIDofCabel() {
		return this.tun;
	}
	
	@Override
	public UETunnel getTunnel() {
		return TunnelHolder.getUETunnel(tun);
	}
	
	@Override
	public void setTunnelID(int i) {
		this.tun = i;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update() {
		List<EntityFX> p = worldObj.getEntitiesWithinAABB(EntityFX.class, new AxisAlignedBB(pos, pos.add(1, 1, 1)));
		if (p.size() <= 0) {
			this.worldObj.spawnEntityInWorld(new EntityFX(this.worldObj, this.pos));
		}
		if (!isInit){
		this.worldObj.spawnEntityInWorld(new EntityFX(this.worldObj, this.pos));
		isInit = true;
		}
		TileEntity[] args = new TileEntity[] {
				worldObj.getTileEntity(this.pos.up()),
				worldObj.getTileEntity(this.pos.down()),
				worldObj.getTileEntity(this.pos.north()),
				worldObj.getTileEntity(this.pos.south()),
				worldObj.getTileEntity(this.pos.east()),
				worldObj.getTileEntity(this.pos.west())
		};
		if(this.tun > -1){
		for(TileEntity ent : args){
			if(ent != null && ent instanceof ICabel){
				ICabel cab  = (ICabel) ent;
				if(cab.getTunnelIDofCabel() != this.tun){
					if(cab.getTunnelIDofCabel() > -1){
					TunnelHolder.merge(tun, cab.getTunnelIDofCabel(),worldObj);
					}
				}
			}
		}
		}
		if(this.tun > -1)return;
		for(TileEntity ent : args){
			if(ent != null && ent instanceof ICabel){
				ICabel cab  = (ICabel) ent;
				if(cab.getTunnelIDofCabel() > -1 && TunnelHolder.getUETunnel(cab.getTunnelIDofCabel()) != null){
			        TunnelHolder.getUETunnel(cab.getTunnelIDofCabel()).add(this);
					return;
				}
			}
		}
		if(this.tun > -1)return;
		if(TunnelHolder.contains(pos)){
			this.tun = TunnelHolder.getTunnelFromPos(pos);
			return;
		}
		UETunnel tnl = new UETunnel(worldObj);
        TunnelHolder.getUETunnel(TunnelHolder.addUETunnel(tnl)).add(this);
	}

	@Override
	public double getRate() {
		return rate;
	}

	@Override
	public BlockPos[] getInputs() {
		ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
		TileEntity[] args = new TileEntity[] {
				worldObj.getTileEntity(this.pos.up()),
				worldObj.getTileEntity(this.pos.down()),
				worldObj.getTileEntity(this.pos.north()),
				worldObj.getTileEntity(this.pos.south()),
				worldObj.getTileEntity(this.pos.east()),
				worldObj.getTileEntity(this.pos.west())
		};
		for(TileEntity ent : args){
			if(ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder)ent).isInput()){
				ins.add(ent.getPos());
			}
		}
		BlockPos[] posses = new BlockPos[ins.size()];
		int i = 0;
		for(BlockPos pos : ins){
			posses[i] = pos;
			i++;
		}
		return posses;
	}

	@Override
	public BlockPos[] getOutputs() {
		ArrayList<BlockPos> ins = new ArrayList<BlockPos>();
		TileEntity[] args = new TileEntity[] {
				worldObj.getTileEntity(this.pos.up()),
				worldObj.getTileEntity(this.pos.down()),
				worldObj.getTileEntity(this.pos.north()),
				worldObj.getTileEntity(this.pos.south()),
				worldObj.getTileEntity(this.pos.east()),
				worldObj.getTileEntity(this.pos.west())
		};
		for(TileEntity ent : args){
			if(ent != null && ent instanceof IPowerProvieder && ((IPowerProvieder)ent).isOutput()){
				ins.add(ent.getPos());
			}
		}
		BlockPos[] posses = new BlockPos[ins.size()];
		int i = 0;
		for(BlockPos pos : ins){
			posses[i] = pos;
			i++;
		}
		return posses;
	}
	
}
