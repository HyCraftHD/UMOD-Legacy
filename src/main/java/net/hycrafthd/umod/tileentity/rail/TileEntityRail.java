package net.hycrafthd.umod.tileentity.rail;

import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.*;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRail extends TileEntity implements IUpdatePlayerListBox {
	
	public boolean isn = false;
    public byte b = 0;
	
	public void add(){
		if(b < 2)
		b++;
	}
	
	@Override
	public void update() {
		if (!isn)
			init();
	}
	
	private void init() {
		this.worldObj.spawnEntityInWorld(new EntityRailFX(worldObj, pos));
		isn = true;
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
	
    public static final String COUNT = "r_count"; 
	
	@Override
	public void writeToNBT(NBTTagCompound com) {
		super.writeToNBT(com);
		com.setByte(COUNT, b);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound com) {
		super.readFromNBT(com);
		b = com.getByte(COUNT);
	}
	
}
