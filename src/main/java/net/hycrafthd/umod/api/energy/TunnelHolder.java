package net.hycrafthd.umod.api.energy;

import java.util.ArrayList;

import net.hycrafthd.umod.UMod;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TunnelHolder {
	
	private static ArrayList<UETunnel> tunnels = new ArrayList<UETunnel>();
	
	public static int getMax(){
		return tunnels.size();
	}
	
	public static int addUETunnel(UETunnel tn) {
		int num = tunnels.size();
		tn.setID(num);
		tunnels.add(tn);
		System.out.println("Added " + num);
		return num;
	}
	
	public static boolean contains(BlockPos p){
		for(UETunnel tnl : tunnels){
			if(tnl.contains(p)){
				return true;
			}
		}
		return false;
	}
	
	public static int getTunnelFromPos(BlockPos p){
		for(UETunnel tnl : tunnels){
			if(tnl.contains(p)){
				return tnl.getID();
			}
		}
		return -1;
	}
	
	public static UETunnel getUETunnel(int i) {
		if (i >= tunnels.size() || i < 0)
			return null;
		return tunnels.get(i);
	}
	
	public static int merge(int i, int i2,World w) {
		if(i == i2)return i;
		if(getUETunnel(i) == null || getUETunnel(i2) == null)return -1;
	    ArrayList<BlockPos> psses = new ArrayList<BlockPos>(getUETunnel(i));
	    psses.addAll(getUETunnel(i2));
	    if(i > i2){
	    	tunnels.remove(i);
	    	tunnels.remove(i2);
	    }else{
	    	tunnels.remove(i2);
	    	tunnels.remove(i);
	    }
	    int id = TunnelHolder.addUETunnel(new UETunnel(w));
	    for(BlockPos pos : psses){
	    	ICabel cab = (ICabel) w.getTileEntity(pos);
	    	TunnelHolder.getUETunnel(id).add(cab);
	    }
		UMod.log.debug("Merged UETunnel " + i + " and " + i2);
		return id;
	}
	
	public static int regenUETunnel(int id, World w) {
		tunnels.remove(id);
		for (int y = 0; y < tunnels.size(); y++) {
			tunnels.get(y).setID(y);
			UETunnel tnl = tunnels.get(y);
			for (BlockPos cab : tnl) {
				ICabel cabs = (ICabel) tnl.getWorld().getTileEntity((BlockPos) cab);
				cabs.setTunnelID(tnl.getID());
			}
		}
		return id;
	}
	
	public static boolean remove(int id){ 
		if(getUETunnel(id) == null)return true;
		if(getUETunnel(id).size() <= 0){
			tunnels.remove(id);
			for (int y = 0; y < tunnels.size(); y++) {
				tunnels.get(y).setID(y);
				UETunnel tnl = tunnels.get(y);
				for (BlockPos cab : tnl) {
					ICabel cabs = (ICabel) tnl.getWorld().getTileEntity((BlockPos) cab);
					if(cabs == null)continue;
					cabs.setTunnelID(tnl.getID());
				}
			}
			return true;
		}
		return false;
	}
}
