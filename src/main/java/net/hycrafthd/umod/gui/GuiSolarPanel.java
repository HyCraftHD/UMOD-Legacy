package net.hycrafthd.umod.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSolarPanel extends GuiScreen {

	int xSize;
	int ySize;
	IPowerProvieder pro;
	private World w;

	public GuiSolarPanel(World w, IPowerProvieder po) {
		xSize = 176;
		ySize = 166;
		pro = po;
		this.w = w;
	}

	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) throws IOException {
		super.keyTyped(p_73869_1_, p_73869_2_);
		if (p_73869_1_ == 'e') {
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

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
		IBlockState ste = w.getBlockState(((TileEntity) pro).getPos());
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(ste.getBlock().getMetaFromState(ste));

		this.drawCenteredString(this.fontRendererObj, I18n.format(ste.getBlock().getUnlocalizedName() + type.getName() + ".name"), k + xSize / 2 - 37 / 2, l + 10, 4210752, false);
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
		super.drawScreen(mouseX, mouseY, partialTicks);

	}

	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}

	public void drawStorage(int l, int k, int height) {
		int x = l + 169, y = k + 159;
		drawTexturedModalRect(x, y, 206, height + 7, -30, -height);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
