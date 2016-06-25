package net.hycrafthd.umod.VIA;

import java.io.File;

public class VIARegister {
	
	public static VIAFile viaTest; 
	public static VIAFile viaRailPart;
	
	public static void registerVIA() {
		viaTest = new VIAFile("D:\\Desktop\\viatest.via");
		viaRailPart = new VIAFile(new File(System.getProperty("user.dir")).getParent() + "bin\\assets\\umod\\VIA");
	}
	
}
