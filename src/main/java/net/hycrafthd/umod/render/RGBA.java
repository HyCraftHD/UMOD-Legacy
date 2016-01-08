package net.hycrafthd.umod.render;

import org.lwjgl.util.Color;

public class RGBA {

	private int Red;
	private int Green;
	private int Blue;
	private int Alpha;

	public RGBA(int r,int g,int b,int a) {
		Red = r;
		Green = g;
		Blue = b;
		Alpha = a;
	}
	
	public RGBA(Color cl){
		this(cl.getRed(),cl.getGreen(),cl.getBlue(),cl.getAlpha());
	}
	
	public RGBA(java.awt.Color cl) {
		this(cl.getRed(),cl.getGreen(),cl.getBlue(),cl.getAlpha());
	}
	
	public int getRed(){
		return Red;
	}
	
	public int getGreen(){
		return Green;
	}
	
	public int getBlue(){
		return Blue;
	}
	
	public int getAlpha(){
		return Alpha;
	}

	public void setAlpha(int i){
		Alpha = i;
	}
}
