package net.hycrafthd.umod.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiBattery extends GuiScreen{

    protected int xSize = 176;
    protected int ySize = 166;
	public IPowerProvieder pro;
	public EntityPlayer pl;
	public BlockPos pos;
	public int ag;
	
	public GuiBattery(IPowerProvieder po,EntityPlayer pl,BlockPos pos,int ag) {
	pro = po;
	this.pl = pl;
	this.pos = pos;
	this.ag = ag;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		GuiButton btn = new GuiButton(0, this.width/2-42, 20,85,20, "Sign with Player");
		buttonList.add(btn);
		GuiButton ba = new GuiButton(1, this.width/2-(this.xSize/2), 20,20,20, "<");
		GuiButton fo = new GuiButton(2, this.width/2+(this.xSize/2)-20, 20,20,20, ">");
		buttonList.add(ba);
		buttonList.add(fo);
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
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
       switch(button.id){
       case 0:
    	   TileEntityPulverizer p = (TileEntityPulverizer) this.pro;
    	   if(button.displayString.equals("Sign with Player")){
    	   p.signPlayer(pl);
    	   button.displayString = "Unsign";
    	   }else{
    	   pl.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unsigned Pulverizer"));
    	   p.signPlayer(null);
    	   }
    	   p.markDirty();
    	   break;
       case 1:
       case 2:
    	   this.pl.closeScreen();
    	   this.pl.openGui(UReference.modid, ag, this.pl.worldObj, this.pos.getX(), this.pos.getY(), this.pos.getZ());
       }
	}
}
