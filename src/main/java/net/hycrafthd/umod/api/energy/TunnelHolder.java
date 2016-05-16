package net.hycrafthd.umod.api.energy;

import java.util.ArrayList;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import scala.collection.TraversableOnce.OnceCanBuildFrom;

public class TunnelHolder {

	private static ArrayList<UETunnel> tunnels = new ArrayList<UETunnel>();
	
	public static int addUETunnel(UETunnel tn) {
		int num = tunnels.size();
		tn.setID(num);
		tunnels.add(tn);
		return num;
	}
	
	public static UETunnel getUETunnel(int i){
		return tunnels.get(i);
	}
	
	public static int merge(int i,int i2){
		System.out.println("Try to Merge UETunnel " + i + " and " + i2);
		UETunnel tnl1 = TunnelHolder.getUETunnel(i);
		UETunnel tnl2 = TunnelHolder.getUETunnel(i2);
		UETunnel newT = new UETunnel(tnl1.getWorld());
		tunnels.remove(i);
		if(i > i2){
		tunnels.remove(i2);
		}else{
		tunnels.remove(i2 - 1);
		}
		for(int y = i;y < tunnels.size();y++){
			tunnels.get(i).setID(y);
			UETunnel tnl = tunnels.get(i);
			for(BlockPos cab : tnl){
				ICabel cabs = (ICabel) tnl.getWorld().getTileEntity((BlockPos) cab);
				cabs.setTunnelID(tnl.getID());
			}
		}
		int id = addUETunnel(newT);
		for(BlockPos cab : tnl1){
			TunnelHolder.getUETunnel(id).add(cab);
		}
		for(BlockPos cab : tnl2){
			TunnelHolder.getUETunnel(id).add(cab);
		}
		UMod.log.debug("Merged UETunnel " + i + " and " + i2);
		return id;
	}
	
	public static int regenUETunnel(int id,World w){
		if(id >= tunnels.size()){
			return TunnelHolder.addUETunnel(new UETunnel(w));
		}
		UETunnel tun = getUETunnel(id);
		tunnels.remove(id);
		for(int i = id;i < tunnels.size();i++){
			tunnels.get(i).setID(i);
		}
		for(BlockPos cad : tun){
			TileEntity ca = tun.getWorld().getTileEntity(cad);
			if(ca instanceof TileEntityCable){
				if(ca != null){
				((TileEntityCable) ca).onBlockSetInWorld();
				}
			}
		}
		UMod.log.debug("Readd " + id);
		return id;
	}
}
