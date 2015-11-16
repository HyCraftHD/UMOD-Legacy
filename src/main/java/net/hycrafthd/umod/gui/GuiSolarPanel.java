package net.hycrafthd.umod.gui;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiSolarPanel extends GuiScreen {

	int xSize;
	int ySize;
	IPowerProvieder pro;

	public GuiSolarPanel(IPowerProvieder po) {
		xSize = 176;
		ySize = 166;
		pro = po;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		this.mc.getTextureManager().bindTexture(new ResourceLocation(UReference.modid, "textures/gui/solar.png"));

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		int high = 0;
		if (pro.hasPower()) {
			double ps = pro.getStoredPower() * 100 / pro.getMaximalPower();
			high = (int) (ps * 0.01 * 152);
		}

		this.drawStorage(k, l, high);

		this.drawCenteredString(this.fontRendererObj, I18n.format("tile.solar.name"), k + xSize / 2 - 37 / 2, l + 10, 4210752, false);
		int maxstringlength = 119;
		String s1 = "Generate: ";
		String s2 = "Stored: ";
		String s3 = "Status: ";
		String s4 = "Error: ";
		this.fontRendererObj.drawSplitString(s1 + (pro.isWorking() ? (pro.getPowerProducNeeds() + " UE/t") : "0 UE/t"), k + 10, l + 50, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s2 + pro.getStoredPower() + " / " + pro.getMaximalPower(), k + 10, l + 80, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s3 + (pro.isWorking() ? "On" : "Off"), k + 10, l + 110, maxstringlength, 4210752);
		if (!pro.isWorking() && pro.getErrorMessage() != null && pro.getErrorMessage() != "") {
			this.fontRendererObj.drawSplitString(s4 + pro.getErrorMessage(), k + 10, l + 140, maxstringlength, 4210752);
		}

	}

	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}

	public void drawStorage(int l, int k, int height) {
		    int x = l + 169,y = k + 159;
	        Tessellator tessellator = Tessellator.getInstance();
	        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        worldrenderer.startDrawingQuads();
	        worldrenderer.setColorRGBA(255, 0, 0, 255);
	        worldrenderer.addVertex(x, y, 0.0D);
	        worldrenderer.addVertex(x, y - height, 0.0D);
	        worldrenderer.addVertex(x - 30, y - height, 0.0D);
	        worldrenderer.addVertex(x - 30, y, 0.0D);
	        tessellator.draw();
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
