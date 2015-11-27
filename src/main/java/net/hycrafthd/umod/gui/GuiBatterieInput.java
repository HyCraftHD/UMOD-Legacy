package net.hycrafthd.umod.gui;

import java.io.IOException;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IGuiProvider;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.hycrafthd.umod.container.ContainerBattery;
import net.hycrafthd.umod.container.ContainerChargeStation;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiBatterieInput extends GuiContainer {

	    protected int xSize = 176;
	    protected int ySize = 166;
		public IPowerProvieder pro;
		public EntityPlayer play;
		public BlockPos pos;
		public int ag;
		private World worldObj;
	
	public GuiBatterieInput(EntityPlayer pl, IInventory tile) {
		super(new ContainerBattery(tile, pl, ((TileEntity)tile).getPos(), ((TileEntity)tile).getWorld()));
		pro = (IPowerProvieder) tile;
		play = pl;
		pos = ((TileEntity)tile).getPos();
		worldObj = pl.worldObj;
	}
	
	@Override
	 protected void keyTyped(char p_73869_1_, int p_73869_2_) throws IOException {
	 super.keyTyped(p_73869_1_, p_73869_2_);
	    	if(p_73869_1_ == 'e'){
	    		play.closeScreen();
	    	}
	 }
	
	@Override
	public void initGui() {
		super.initGui();
		GuiButton ba = new GuiButton(1, this.width/2-(this.xSize/2), 20,20,20, "<");
		GuiButton fo = new GuiButton(2, this.width/2+(this.xSize/2)-20, 20,20,20, ">");
		buttonList.add(ba);
		buttonList.add(fo);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(pro instanceof IGuiProvider){
		this.ag = ((IGuiProvider)pro).getGui();
		}else{
		this.ag = Integer.MAX_VALUE;
		}
		switch(button.id){
	 case 1:
  	   this.play.closeScreen();
  	   this.play.openGui(UReference.modid, EnumTypeGui.BATTERIE.getID(), this.play.worldObj, this.pos.getX(), this.pos.getY(), this.pos.getZ());
     case 2:
  	   this.play.closeScreen();
  	   this.play.openGui(UReference.modid, ag, this.play.worldObj, this.pos.getX(), this.pos.getY(), this.pos.getZ());
   }
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(new ResourceLocation(UReference.modid, "textures/gui/charge.png"));

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
