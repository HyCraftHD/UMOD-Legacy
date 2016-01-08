package net.hycrafthd.umod.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IGuiProvider;
import net.hycrafthd.umod.api.ISignable;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class GuiBattery extends GuiScreen{

    protected int xSize = 176;
    protected int ySize = 166;
	public IPowerProvieder pro;
	public EntityPlayer pl;
	public BlockPos pos;
	public int ag;
	private World worldObj;
	
	public GuiBattery(World w,IPowerProvieder po,EntityPlayer pl,BlockPos pos,int ag) {
	pro = po;
	this.pl = pl;
	this.pos = pos;
	if(po instanceof IGuiProvider){
	this.ag = ((IGuiProvider)po).getGui();
	}else{
	this.ag = Integer.MAX_VALUE;
	}
	worldObj = w;
	}
	
	@Override
	 protected void keyTyped(char p_73869_1_, int p_73869_2_) throws IOException {
	 super.keyTyped(p_73869_1_, p_73869_2_);
	    	if(p_73869_1_ == 'e'){
	    		pl.closeScreen();
	    	}
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

		this.drawCenteredString(this.fontRendererObj, I18n.format(worldObj.getBlockState(pos).getBlock().getUnlocalizedName() + ".name"), k + xSize / 2 - 37 / 2, l + 10, 4210752, false);
		int maxstringlength = 119;
		String s1 = "Needs: ";
		String s2 = "Stored: ";
		String s3 = "Status: ";
		String s4 = "Error: ";
		this.fontRendererObj.drawSplitString(s1 + pro.getPowerProducNeeds() + " UE/t", k + 10, l + 50, maxstringlength, 4210752);
		this.fontRendererObj.drawSplitString(s2 + pro.getStoredPower() + " / " + pro.getMaximalPower(), k + 10, l + 70, maxstringlength, 4210752);
		if(ag != -1){
		this.fontRendererObj.drawSplitString(s3 + (pro.isWorking() ? "On" : "Off"), k + 10, l + 90, maxstringlength, 4210752);
		if (!pro.isWorking() && pro.getErrorMessage() != null && pro.getErrorMessage() != "") {
			this.fontRendererObj.drawSplitString(s4 + pro.getErrorMessage(), k + 10, l + 110, maxstringlength, 4210752);
		}
		}

	}
	
	public int drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color, boolean shadow) {
		return fontRendererIn.drawString(text, (float) (x - fontRendererIn.getStringWidth(text) / 2), (float) y, color, shadow);
	}
	
	public void drawStorage(int l, int k, int height) {
	    int x = l + 169,y = k + 159;
	       drawTexturedModalRect(x, y, 206, height + 7, -30, -height);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
       switch(button.id){
       case 0:
    	   if(this.pro instanceof ISignable){
    	   ISignable p = (ISignable) this.pro;
    	   if(button.displayString.equals("Sign with Player")){
    	   p.signPlayer(this.mc.thePlayer);
    	   button.displayString = "Unsign";
    	   }else{
    	   this.mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unsigned Pulverizer"));
    	   p.signPlayer(null);
    	   }
    	   ((TileEntity)this.pro).markDirty();
    	   }
    	   break;
       case 1:
    	   this.pl.closeScreen();
    	   this.pl.openGui(UReference.modid, ag, this.pl.worldObj, this.pos.getX(), this.pos.getY(), this.pos.getZ());
       case 2:
    	   this.pl.closeScreen();
    	   this.pl.openGui(UReference.modid, ag, this.pl.worldObj, this.pos.getX(), this.pos.getY(), this.pos.getZ());
       }
	}
}
