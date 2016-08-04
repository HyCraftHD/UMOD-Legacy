package net.hycrafthd.umod.gui;

import net.hycrafthd.corelib.util.*;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class GuiSlider extends Gui {

	private int x,y;
	private RGBA back,slid,slid2;
	private int val = 0;
	private StringMethod ret = null;
	
	public GuiSlider(int x,int y,RGBA color1,RGBA color2,RGBA color3,int val) {
		this.x = x;
		this.y = y;
		back = color1;
		slid = color2;
		slid2 = color3;
		this.val = val;
	}
	
	public void draw(Minecraft mc){
		mc.getTextureManager().bindTexture(new GuiRescources("Slider_Background.png"));
		GlStateManager.color(back.getRed(), back.getGreen(), back.getBlue(),back.getAlpha());
		drawScaledCustomSizeModalRect(x, y+2, 0, 0, 100, 4, 100, 4, 100, 4);
		mc.getTextureManager().bindTexture(new GuiRescources("Slider_slide.png"));
		GlStateManager.color(slid.getRed(), slid.getGreen(), slid.getBlue(),slid.getAlpha());
		int size = 8;
		drawScaledCustomSizeModalRect(x + val - (size/2), y, 0, 0, size, size, size, size, size, size);
		mc.getTextureManager().bindTexture(new GuiRescources("Slider_slide.png"));
		GlStateManager.color(slid2.getRed(), slid2.getGreen(), slid2.getBlue(),slid2.getAlpha());
		drawScaledCustomSizeModalRect(x + val - ((size/2) - 1), y + 1, 0, 0, size-2, size-2, size-2, size-2, size-2, size-2);
	}
	
	public void drawOverlay(Minecraft mc,int mousex,int mousey){
		if(ret == null) return;		if(mousex > x && mousex < x + 100 && mousey > y && mousey < y + 8){
		LWJGLUtils.drawGradientRect(mousex, mousey, mousex + this.getWidth(), mousey + this.getHeight(), back, back,this.zLevel);
		if (this.hasMoreLines()) {
			String[] str = this.getString().split("\n");
			for (int i = 0; i < str.length; i++)
				Minecraft.getMinecraft().getRenderManager().getFontRenderer().drawString(str[i], mousex + 4, mousey + 4 + (i * 16),  0x000000);
		} else {
			Minecraft.getMinecraft().getRenderManager().getFontRenderer().drawString(this.getString(), mousex + 4, mousey + 4, 0x000000);
		}
	    }
	}
	
	public String getString(){
		return ret.getString();
	}
	
	public void setStringRet(StringMethod returnm){
		ret = returnm;
	}
	
	public int getWidth(){
		int size;
        size = ret.getString().length()*5; 
        if(hasMoreLines()){
        	size = 0;
        	String[] str = ret.getString().split("\n");
        	for(int i = 0;i < str.length;i++){
        		if(str[i].length()*5 > size){
        			size = str[i].length()*5;
        		}
        	}
        }
		return size + 10;
	}
	
	public int getHeight(){
		int size = 16;
		if(hasMoreLines()){
		    String[] str = ret.getString().split("\n");
		    size = size * str.length;
		}
		return size;
	}
	
	public boolean hasMoreLines(){
		return ret.getString().contains("\n");
	}
	
	public int getValue(){
		return val;
	}
	
	public void handelMouseClicked(int mousex,int mousey){
		if(mousex >= x && mousex <= x + 100 && mousey >= y && mousey <= y + 8){
			val = mousex - x;
		}
	}
}
