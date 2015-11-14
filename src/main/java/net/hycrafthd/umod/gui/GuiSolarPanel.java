package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiSolarPanel extends GuiScreen{
	
    protected int xSize = 176;
    protected int ySize = 166;
	IPowerProvieder pro;

	public GuiSolarPanel(IPowerProvieder po) {
	pro = po;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(UReference.modid,"textures/gui/solar.png"));
	   int k = (this.width - this.xSize) / 2;
       int l = (this.height - this.ySize) / 2;
       this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
       double high = 0;
       if(pro.hasPower()){
       double ps = pro.getStoredPower() * 100 / pro.getMaximalPower();
       high = ps * 83 / 100;
	   }
	   drawColour(305, 156, 19, high, 255,0,0);
	   this.fontRendererObj.drawString("Stored Power: " + pro.getStoredPower() + "UE", 157, 80, 0xFF8000);
	   this.fontRendererObj.drawString("Maximum Power: " + pro.getMaximalPower() + "UE", 157, 100, 0xFF8000);
	   this.fontRendererObj.drawString("Products: 3UE/t", 157, 120, 0xFF8000);
	   String of = pro.isWorking() ? "On":"Off";
	   this.fontRendererObj.drawString("Status: " + of, 157, 140, pro.isWorking() ? 0x04BA01:0xF00404);
	   
	   if(pro.getErrorMessage() != null){
	   this.fontRendererObj.drawString(pro.getErrorMessage(), 157, 160, 0xF00404);
	   }

	} 

	public void drawColour(int x, int y, int width, double height, int r,int g,int b)
	{
		WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		renderer.startDrawingQuads();
		renderer.setColorRGBA(r, g, b, 255);
		renderer.addVertex(x, y, 0.0D);
		renderer.addVertex(x, y - height, 0.0D);
		renderer.addVertex(x - width, y - height, 0.0D);
		renderer.addVertex(x - width, y, 0.0D);
		Tessellator.getInstance().draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
