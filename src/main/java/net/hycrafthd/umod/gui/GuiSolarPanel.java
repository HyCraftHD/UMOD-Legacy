package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
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
		this.fontRendererObj.drawSplitString(s1 + (pro.isWorking() ? "3 UE/t" : "0 UE/t"), k + 10, l + 50, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s2 + pro.getStoredPower() + " / " + pro.getMaximalPower(), k + 10, l + 70, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s3 + (pro.isWorking() ? "On" : "Off"), k + 10, l + 90, maxstringlength, 4210752);
		if (!pro.isWorking() && pro.getErrorMessage() != null && pro.getErrorMessage() != "") {
			this.fontRendererObj.drawSplitString(s4 + pro.getErrorMessage(), k + 10, l + 110, maxstringlength, 4210752);
		}

	}

	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}

	public void drawStorage(int k, int l, int height) {
		// GL11.glTranslated(k + 139 + 15, l + 7 + height, 0);
		// GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
		this.drawTexturedModalRect(k + 139, l + 7, 176, 7, 30, height);
		// GL11.glRotatef(90, 0.0f, 0.0f, -1.0f);
		// GL11.glTranslated(-(k + 139 + 15), -(l + 7 + height), 0);

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
