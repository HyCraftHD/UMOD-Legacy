package net.hycrafthd.umod.tileentity;

import java.util.ArrayList;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.api.IPlugabel;
import net.hycrafthd.umod.api.energy.ICabel;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.api.energy.TunnelHolder;
import net.hycrafthd.umod.api.energy.UETunnel;
import net.hycrafthd.umod.entity.EntityPipeFX;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityCable extends TileEntity implements IPlugabel, ICabel, IUpdatePlayerListBox{

	public double Maximum_Power;
	public double stored;
	public int loos;
	public boolean firstrun = false;
	public ItemStack conduit = null;
	public int tun = -1;
	public int idInT = -1;
	public boolean isInit = false;
	
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
		compound.setDouble("Stored", (short) stored);
		compound.setDouble("Max", Maximum_Power);
		super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.stored = compound.getDouble("Stored");
		this.Maximum_Power = compound.getDouble("Max");
		super.readFromNBT(compound);
		this.onBlockSetInWorld();
	}
	
	@Deprecated
	public void notifyOfPipeState(TileEntity ent){
		//TODO Be not so behindert and futherly delete this method
	}
	
	public void onBlockSetInWorld(){
		   if(worldObj == null)return;
		    World w = this.getWorld();
			boolean csouth = this.canConnect(w, pos.south());
			boolean cup = this.canConnect(w, pos.up());
			boolean cwest = this.canConnect(w, pos.west());
			
			ArrayList<TileEntity> ent = new ArrayList<TileEntity>();
			if (cup) {
				if(ent != null){
				ent.add(w.getTileEntity(pos.up()));
				}
			}
			if (cwest) {
				if(ent != null){
					ent.add(w.getTileEntity(pos.west()));
				}
			}
			if (csouth) {
				if(ent != null){
					ent.add(w.getTileEntity(pos.south()));
				}
			}
			
			
			for(TileEntity et : ent){
			if(et instanceof IPowerProvieder){
					if(((IPowerProvieder) et).needsPower()){
						this.isout = true;
						UMod.log.debug("Output found");
					}else if(((IPowerProvieder) et).productsPower()){
						this.isin = true;
						UMod.log.debug("Input found");
					}
			}else
			if(et instanceof ICabel){
					ICabel cab = (ICabel) et;
					if(cab != null){
						if(this.tun < 0){
						if(cab.getTunnelIDofCabel() >= 0){
						this.tun = cab.getTunnel().getID();
						}else{
						this.tun = TunnelHolder.addUETunnel(new UETunnel(this.worldObj));
						cab.setTunnelID(this.tun);
						TunnelHolder.getUETunnel(this.tun).add(this);
						TunnelHolder.getUETunnel(this.tun).add(cab);
						}
						}else if(cab.getTunnelIDofCabel() != this.tun && cab.getTunnelIDofCabel() > 0){
						this.tun = TunnelHolder.merge(this.tun, cab.getTunnelIDofCabel());
						}else{
						cab.setTunnelID(this.tun);
						}
					}
			}
			}
			isInit = true;
	}

	public void onBlockBreak(){
		if(!isInit)return;
		TunnelHolder.regenUETunnel(tun,this.worldObj);
	}
	
	@Override
	public void setEnergy(double coun) {
		stored = coun;
	}

	@Override
	public String getEnergyClass() {
		return "UE/T";
	}

	@Override
	public double getEnergy() {
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

	protected boolean isout = false,isin = false;
	
	@Override
	public boolean isInput() {
		return isin;
	}

	@Override
	public boolean isOutput() {
		return isout;
	}

	@Override
	public double getMaxEnergy() {
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

	@Override
	public void update() {
		    if(isInit)return;
			onBlockSetInWorld();
		    this.worldObj.spawnEntityInWorld(new EntityPipeFX(this.worldObj,this.pos));				 	    
	}

}
