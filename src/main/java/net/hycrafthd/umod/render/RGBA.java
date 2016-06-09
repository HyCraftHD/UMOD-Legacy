package net.hycrafthd.umod.render;

import org.lwjgl.util.Color;

public class RGBA {

	private int Red;
	private int Green;
	private int Blue;
	private int Alpha;
	
	public static final RGBA NULL = new RGBA(0, 0, 0, 0);

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

	public RGBA setAlpha(int i){
		Alpha = i;
		return this;
	}
	
	public java.awt.Color toAWTColor(){
		return new java.awt.Color(Red, Green, Blue,Alpha);
	}
	
	public Color toColor(){
		return new Color(Red, Green, Blue,Alpha);
	}
}
