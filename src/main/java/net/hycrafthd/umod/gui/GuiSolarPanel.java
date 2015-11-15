package net.hycrafthd.umod.gui;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
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
			System.out.println(high);
		}
		this.drawStorage(k, l, high);

		this.fontRendererObj.drawString("Stored Power: " + pro.getStoredPower() + "UE", 157, 80, 0xFF8000);
		this.fontRendererObj.drawString("Maximum Power: " + pro.getMaximalPower() + "UE", 157, 100, 0xFF8000);
		this.fontRendererObj.drawString("Products: 3UE/t", 157, 120, 0xFF8000);
		String of = pro.isWorking() ? "On" : "Off";
		this.fontRendererObj.drawString("Status: " + of, 157, 140, pro.isWorking() ? 0x04BA01 : 0xF00404);

		if (pro.getErrorMessage() != null) {
			this.fontRendererObj.drawString(pro.getErrorMessage(), 157, 160, 0xF00404);
		}

	}

	public void drawStorage(int k, int l, int height) {
		//GL11.glTranslated(k + 139 + 15, l + 7 + height, 0);
		//GL11.glRotatef(90, 0.0f, 0.0f, 1.0f);
		this.drawTexturedModalRect(k + 139, l + 7, 176, 7, 30, height);
		//GL11.glRotatef(90, 0.0f, 0.0f, -1.0f);
		//GL11.glTranslated(-(k + 139 + 15), -(l + 7 + height), 0);
		

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
