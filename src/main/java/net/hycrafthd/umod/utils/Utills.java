package net.hycrafthd.umod.utils;

public class Utills {

	public static double getDistanceAtoB(double x1, double z1, double x2, double z2){
		double dx = x1-x2;
		double dz = z1-z2;
		return Math.sqrt((dx*dx + dz*dz ));
	}
	
}
