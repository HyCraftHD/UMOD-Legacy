package net.hycrafthd.umod.gui;

import java.awt.Color;
import java.util.ArrayList;

import akka.actor.dsl.Inbox.Select;
import net.hycrafthd.umod.render.RGBA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import scala.collection.GenTraversableLike;

public class GuiCombobox extends Gui{

	private ArrayList<String> strs = new ArrayList<String>();
	private String slected = "Choose";
	private boolean extend = false;
	private int x;
	private int y;
	private int width;
	private int height;
    private Runnable runn = null;
	
	public GuiCombobox(int x,int y,int width,int height) {
	      this.x = x;
	      this.y = y;
	      this.width = width;
	      this.height = height;
	}
	
	public void draw(Minecraft mc){
		RGBA rgb = new RGBA(Color.WHITE);
 		this.drawGradientRect(x, y, x + width, y + height, rgb, rgb);
 		RGBA rgb2 = new RGBA(Color.DARK_GRAY);
 		this.drawHLine(x, x + width, y, rgb2);
 		this.drawHLine(x, x + width, y + height, rgb2);
 		this.drawVLine(x, y, y + height, rgb2);
 		this.drawVLine(x  + width, y, y + height, rgb2);

        FontRenderer fontrenderer = mc.fontRendererObj;
        if(!extend){
        mc.getTextureManager().bindTexture(new GuiRescources("menudown.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        drawModalRectWithCustomSizedTexture(x + width - 15 , y + height/2 - 4, 0, 0, 15, 9, 15, 9);
        fontrenderer.drawString(slected, x + 3, y + 2, 0x000000);
        }else{
        	 mc.getTextureManager().bindTexture(new GuiRescources("menuup.png"));
             GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
             GlStateManager.enableBlend();
             GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
             GlStateManager.blendFunc(770, 771);
             drawModalRectWithCustomSizedTexture(x + width - 15 , y + height/2 - 4, 0, 0, 15, 9, 15, 9);	
      		 this.drawGradientRect(x+1, y + height, x + width-2, y + strs.size()*18, rgb, rgb);
             for(int i = 0;i < strs.size();i++){
              fontrenderer.drawString(strs.get(i), x + 3, y + height + 4 + 9*i, 0x000000);	 
             }
        }
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
	
	public boolean isMouseover(int x,int y){
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}
	
	public void handelClick(int x,int y){
		if(extend){
			if(x >= this.x && x <= this.x + this.width && y >= this.y + this.height){
				for(int i = 0;i < strs.size();i++){
					if(y <= this.y + this.height + (9*(i+1))){
					    setSelected(i);
					    extend = false;
					    if(runn != null){
					    	runn.run();
					    }
					    return;
					}
				}
			}
		}
		if(isMouseover(x, y)){
			extend = !extend;
		}
		
	}
	
	public void setOnListClicked(Runnable run){
		runn = run;
	}
	
	public void setSelected(int i){
		slected = strs.get(i);
	}
	
	public int getSelceted(){
		return strs.indexOf(slected);
	}
}
