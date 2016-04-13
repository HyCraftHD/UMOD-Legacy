package net.hycrafthd.umod.api.energy;

import java.util.ArrayList;

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
	
	public static void merge(int i,int i2){
		UETunnel tnl1 = TunnelHolder.getUETunnel(i);
		UETunnel tnl2 = TunnelHolder.getUETunnel(i2);
		UETunnel newT = new UETunnel();
		tunnels.remove(i);
		if(i > i2){
		tunnels.remove(i2);
		}else{
		tunnels.remove(i2 - 1);
		}
		int id = addUETunnel(newT);
		for(ICabel cab : tnl1){
			TunnelHolder.getUETunnel(id).add(cab);
		}
		for(ICabel cab : tnl2){
			TunnelHolder.getUETunnel(id).add(cab);
		}
	}
}
