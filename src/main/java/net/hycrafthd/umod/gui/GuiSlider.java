package net.hycrafthd.umod.gui;

import java.awt.Color;

import net.hycrafthd.umod.render.RGBA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class GuiSlider extends Gui {

	private int x,y;
	private RGBA back,slid;
	private int val = 0;
	
	public GuiSlider(int x,int y,RGBA color1,RGBA color2,int val) {
		this.x = x;
		this.y = y;
		back = color1;
		slid = color2;
		this.val = val;
	}
	
	public void draw(Minecraft mc){
		mc.getTextureManager().bindTexture(new GuiRescources("Slider_Background.png"));
		GlStateManager.color(back.getRed(), back.getGreen(), back.getBlue(),back.getAlpha());
		drawScaledCustomSizeModalRect(x, y+3, 0, 0, 100, 10, 100, 10, 100, 10);
		mc.getTextureManager().bindTexture(new GuiRescources("Slider_slide.png"));
		GlStateManager.color(slid.getRed(), slid.getGreen(), slid.getBlue(),slid.getAlpha());
		drawScaledCustomSizeModalRect(x + val - 8, y, 0, 0, 16, 16, 16, 16, 16, 16);
		mc.getTextureManager().bindTexture(new GuiRescources("Slider_slide.png"));
		RGBA rgb = new RGBA(Color.white);
		GlStateManager.color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(),rgb.getAlpha());
		drawScaledCustomSizeModalRect(x + val - 7, y + 1, 0, 0, 14, 14, 14, 14, 14, 14);
	}
	
	public void drawOverlay(Minecraft mc,int mousex,int mousey){
		
	}
	
	public void handelMouseClicked(int mousex,int mousey){
		if(mousex > x && mousex < x + 100 && mousey > y && mousey < y + 16){
			val = mousex - x;
		}
	}
}
