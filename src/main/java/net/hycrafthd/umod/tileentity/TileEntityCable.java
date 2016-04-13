package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPlugabel;
import net.hycrafthd.umod.api.energy.ICabel;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.api.energy.TunnelHolder;
import net.hycrafthd.umod.api.energy.UETunnel;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityCable extends TileEntity implements IPlugabel, ICabel {

	public int Maximum_Power;
	public int stored;
	public int loos;
	public boolean firstrun = false;
	public ItemStack conduit = null;
	public int tun = -1;
	
	public TileEntityCable() {
	}

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

	
	public TileEntityCable(int maxpower, int pipelooseone) {
		Maximum_Power = EnergyUtils.inUE(maxpower);
		loos = pipelooseone;
	}

	public void setConduit(ItemStack b){
		conduit = b;
	}
	
	public boolean hasConduit(){
		return conduit != null;
	}
	
	public ItemStack getConduit(){
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
	
	@Deprecated
	public void notifyOfPipeState(TileEntity ent){
		//TODO Be not so behindert and futherly delete this method
	}
	
	public void onBlockSetInWorld(){
		if(this.tun == -1){
		    World w = this.getWorld();
			boolean csouth = this.canConnect(w, pos.south());
			boolean cnorth = this.canConnect(w, pos.north());
			boolean cdown = this.canConnect(w, pos.down());
			boolean cup = this.canConnect(w, pos.up());
			boolean ceast = this.canConnect(w, pos.east());
			boolean cwest = this.canConnect(w, pos.west());

			TileEntity ent = null;
			if (cup) {
				ent = w.getTileEntity(pos.up());
				if(ent instanceof ICabel){
					ICabel cab = (ICabel) ent;
						this.tun = cab.getTunnel().getID();
				}
			}
			if (cdown) {
				if(ent != null){
				ent = w.getTileEntity(pos.down());
				if(ent instanceof ICabel){
					ICabel cab = (ICabel) ent;
						this.tun = cab.getTunnel().getID();
				}
				}
			}
			if (cwest) {
				if(ent != null){
					ent = w.getTileEntity(pos.west());
					if(ent instanceof ICabel){
						ICabel cab = (ICabel) ent;
							this.tun = cab.getTunnel().getID();
					}
					}
			}
			if (ceast) {
				if(ent != null){
					ent = w.getTileEntity(pos.east());
					if(ent instanceof ICabel){
						ICabel cab = (ICabel) ent;
							this.tun = cab.getTunnel().getID();
					}
					}
			}
			if (cnorth) {
					ent = w.getTileEntity(pos.north());
					if(ent instanceof ICabel){
						ICabel cab = (ICabel) ent;
						if(ent != null){
							if(this.tun == -1){
							this.tun = cab.getTunnel().getID();
							}else{
								TunnelHolder.merge(this.tun, cab.getTunnel().getID());
							}
						}
					}
			}
			if (csouth) {
				if(ent != null){
					ent = w.getTileEntity(pos.south());
					if(ent instanceof ICabel){
						ICabel cab = (ICabel) ent;
							this.tun = cab.getTunnel().getID();
					}
					}
			}
		}		
	}

	@Override
	public void setEnergy(int coun) {
		stored = coun;
	}

	@Override
	public String getEnergyClass() {
		return "UE/T";
	}

	@Override
	public int getEnergy() {
		return stored;
	}

	@Deprecated
	@Override
	public void searchForInput(ICabel cab) {
		//TODO Be not so behindert and futherly delete this method
	}

	@Deprecated
	@Override
	public void tranferTo(ICabel cab) {
		//TODO Be not so behindert and futherly delete this method
	}

	@Override
	public boolean isInput() {
		return false;
	}

	@Override
	public boolean isOutput() {
		return false;
	}

	@Override
	public int getMaxEnergy() {
		return Maximum_Power;
	}

	@Override
	public boolean hasConnectedOutput() {
		return false;
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
		return tun;
	}

	@Override
	public UETunnel getTunnel() {
		return TunnelHolder.getUETunnel(tun);
	}

	@Override
	public void setTunnelID(int i) {
		this.tun = i;
	}

}
