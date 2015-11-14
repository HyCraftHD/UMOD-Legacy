package net.hycrafthd.umod.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiSecondPulverizer extends GuiScreen{

    protected int xSize = 176;
    protected int ySize = 166;
	public IPowerProvieder pro;
	public EntityPlayer pl;
	public BlockPos pos;
	
	public GuiSecondPulverizer(IPowerProvieder po,EntityPlayer pl,BlockPos pos) {
	pro = po;
	this.pl = pl;
	this.pos = pos;
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
		   this.fontRendererObj.drawString("Needs: 10UE/t", 157, 120, 0xFF8000);
		   String of = pro.isWorking() ? "On":"Off";
		   this.fontRendererObj.drawString("Status: " + of, 157, 140, pro.isWorking() ? 0x04BA01:0xF00404);
		   
		   if(pro.getErrorMessage() != null){
		   this.fontRendererObj.drawString(pro.getErrorMessage(), 157, 160, 0xF00404);
		   }	
		   if(((TileEntityPulverizer) pro).getSigndPlayerName() != null){
				((GuiButton)buttonList.get(0)).displayString = "Unsign";
			}else{
				((GuiButton)buttonList.get(0)).displayString = "Sign with Player";
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
    	   this.pl.openGui(UReference.modid, 0, this.pl.worldObj, this.pos.getX(), this.pos.getY(), this.pos.getZ());
       }
	}
}
