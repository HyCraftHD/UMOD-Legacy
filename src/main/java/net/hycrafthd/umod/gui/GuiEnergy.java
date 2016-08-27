package net.hycrafthd.umod.gui;

import java.io.IOException;

import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockSolarPanel;
import net.hycrafthd.umod.block.BlockSolarPanel.EnumTypeSolarPanel;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiEnergy extends GuiScreen {
	
	private int xSize;
	private int ySize;
	private IPowerProvieder pro;
	private World w;
	private boolean back;
	
	public GuiEnergy(World w, IPowerProvieder po,boolean back) {
		super();
		this.xSize = 176;
		this.ySize = 166;
		this.pro = po;
		this.w = w;
		this.back = back;
		super.initGui();
	}
	
	public GuiEnergy(World w, IPowerProvieder po) {
		this(w, po, true);
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
		Minecraft.getMinecraft().getTextureManager().bindTexture(new GuiRescources("solar.png"));
		
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		
		if(back){
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		}
		
		int high = 0;
		if (pro.hasPower()) {
			double ps = pro.getStoredPower() * 100 / pro.getMaximalPower();
			high = (int) (ps * 0.01 * 152);
		}
		
		this.drawStorage(k, l, high);
		IBlockState ste = w.getBlockState(((TileEntity) pro).getPos());
		EnumTypeSolarPanel type = EnumTypeSolarPanel.byMetadata(ste.getBlock().getMetaFromState(ste));
		
		this.drawCenteredString(this.fontRendererObj, I18n.format(ste.getBlock().getUnlocalizedName() + (ste.getBlock() instanceof BlockSolarPanel ? type.getName():"") + ".name"), k + xSize / 2 - 37 / 2, l + 10, 4210752, false);
		int maxstringlength = 119;
		String s1 = "Generate: ";
		String s2 = "Stored: ";
		String s3 = "Status: ";
		String s4 = "Error: ";
		this.fontRendererObj.drawSplitString(s1 + (pro.isWorking() ? (EnergyUtils.translate(pro.getPowerProducNeeds()) + " UE/t") : "0 UE/t"), k + 10, l + 50, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s2 + EnergyUtils.translate(pro.getStoredPower()) + " / " + EnergyUtils.translate(pro.getMaximalPower()), k + 10, l + 80, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s3 + (pro.isWorking() ? "On" : "Off"), k + 10, l + 110, maxstringlength, 4210752);
		if (!pro.isWorking() && pro.getErrorMessage() != null && pro.getErrorMessage() != "") {
			this.fontRendererObj.drawSplitString(s4 + pro.getErrorMessage(), k + 10, l + 140, maxstringlength, 4210752);
		}
		
	}
	
	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}
	
	public void drawStorage(int l, int k, int height) {
		float f = 0.00390625F;
	    float f1 = 0.00390625F;
		int x = l + 139, y = k;
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 159, 0);
		Tessellator ts = Tessellator.getInstance();
		WorldRenderer ren = ts.getWorldRenderer();
		ren.startDrawingQuads();
		ren.addVertexWithUV(30D, -(double)height, 0D,206D*f,6D*f1);
		ren.addVertexWithUV(0D, -(double)height, 0D,176D*f,6D*f1);
		ren.addVertexWithUV(0D, 0, 0D,176D*f,(6D + (double)height)*f1);
		ren.addVertexWithUV(30D, 0, 0D,206D*f,(6D + (double)height)*f1);
		ts.draw();
		GlStateManager.popMatrix();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
