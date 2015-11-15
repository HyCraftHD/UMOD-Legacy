package net.hycrafthd.umod.tileentity;

import java.util.ArrayList;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.UUtils;
import net.hycrafthd.umod.api.IPipeRange;
import net.hycrafthd.umod.api.IPlugabel;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityPipe extends TileEntity implements IPlugabel,IPowerProvieder,IPipeRange{
	
	public int Maximum_Power;
	public int stored;
	public int loos;

	public TileEntityPipe() {}
	
	public TileEntityPipe(int maxpower,int pipelooseone) {
		Maximum_Power = maxpower;
		loos = pipelooseone;
	}
	
	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		TileEntity et = w.getTileEntity(p);
		if(et instanceof IPowerProvieder){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		ArrayList<BlockPos> poses = new ArrayList<BlockPos>();
		BlockPos[] list = {pos.east(),pos.north(),pos.south(),pos.west(),pos.up(),pos.down()};
		for(int i = 0;i < list.length;i++){
		Block b = worldObj.getBlockState(list[i]).getBlock();
		if(!(b instanceof BlockBaseMachine)){
        TileEntity e = worldObj.getTileEntity(list[i]);
		if(e instanceof IPowerProvieder && !poses.contains(list[i])){
			IPowerProvieder p = (IPowerProvieder) e;
			if(p.canGetPower(Maximum_Power) && this.canAddPower(Maximum_Power)){
				stored += p.getPower(Maximum_Power);
				if(p instanceof IPipeRange){
					IPipeRange r = (IPipeRange) p;
					if(r.getPastPipeCount() < loos){
						passtpip++;
					}else{
						stored--;
	                    passtpip = 0;
					}
				}else{
					passtpip = 0;
				}
			}
		}
		}else{
			poses.add(list[i].offset(UUtils.getDirectory(list[i],pos)));
		}
		}
	}
	
	public int passtpip = 0;

	@Override
	public int getStoredPower() {
		return stored;
	}

	@Override
	public void addPower(int power) {}

	@Override
	public int getPower(int powerneed) {
	    stored -= powerneed;
		return powerneed;
	}

	@Override
	public boolean canGetPower(int power) {
		if(stored - power >= 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean canAddPower(int power) {
		if(!hasPower() && power + stored <= Maximum_Power){
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
		compound.setInteger("Stored", stored);
		compound.setInteger("Max", Maximum_Power);
		super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.stored = compound.getInteger("Stored");
		this.Maximum_Power = compound.getInteger("Max");
		super.readFromNBT(compound);
	}
	
}
