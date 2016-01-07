package net.hycrafthd.umod.gui;

import java.awt.Color;
import java.util.ArrayList;

import net.hycrafthd.umod.render.RGBA;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

public class GuiCombobox extends Gui{

	private ArrayList<String> strs = new ArrayList<String>();
	
	public GuiCombobox(int x,int y,int width,int height) {
		RGBA rgb = new RGBA(Color.gray);
 		this.drawGradientRect(x, y, x + width, y + height, rgb, rgb);
 		RGBA rgb2 = new RGBA(Color.DARK_GRAY);
 		this.drawHLine(x, x + width, y, rgb2);
 		this.drawHLine(x, x + width, y + height, rgb2);
 		this.drawVLine(x, y, y + height, rgb2);
 		this.drawVLine(x  + width, y, y + height, rgb2);
	}
	
	public ArrayList<String> getItems(){
		return strs;
	}
	
	public void drawHLine(int x,int x2,int y,RGBA r){
		drawGradientRect(x, y, x2, y + 1, r, r);
	}
	
	public void drawVLine(int x,int y,int down,RGBA r){
		drawGradientRect(x, y, x + 1, down, r, r);
	}
	
	public void drawGradientRect(int left, int top, int right, int bottom,RGBA start, RGBA end) {
    	GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
        worldrenderer.addVertex((double)right, (double)top, (double)this.zLevel);
        worldrenderer.addVertex((double)left, (double)top, (double)this.zLevel);
        worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
        worldrenderer.addVertex((double)left, (double)bottom, (double)this.zLevel);
        worldrenderer.addVertex((double)right, (double)bottom, (double)this.zLevel);
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
	
}
