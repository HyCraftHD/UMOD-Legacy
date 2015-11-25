package net.hycrafthd.umod.gui;

import java.io.IOException;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerChargeStation;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiChargstation extends GuiBase {

	public GuiChargstation(EntityPlayer pl, IInventory tile) {
		super(new ResourceLocation(UReference.modid, "textures/gui/charge.png"), pl, tile, new ContainerChargeStation(tile, pl, ((TileEntity)tile).getPos(), ((TileEntity)tile).getWorld()));
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
		switch (button.id) {
		 case 1:
	     case 2:
	    	   this.play.closeScreen();
	    	   this.play.openGui(UReference.modid, 2, this.play.worldObj, this.pos.getX(), this.pos.getY(),this.pos.getZ());
	     break;	   
		}
	}
	
}
