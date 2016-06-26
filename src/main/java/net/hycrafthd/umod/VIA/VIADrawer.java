package net.hycrafthd.umod.VIA;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.utils.LWJGLUtils;

public class VIADrawer {
	
	private VIAFile fl;

	public VIADrawer(VIAFile fl) {
		this.fl = fl;
	}
	
	public void drawNormal(String s,double x,double y,double z){
		try{
		for(int i = 0;i < fl.getMaxGroups();i++){
			LWJGLUtils.drawVertex(s, fl.interpretVertex(i), x, y, z);
		}
		}catch(Throwable th){
			UMod.log.error("VIA Draw Error");
		}
	}
}
