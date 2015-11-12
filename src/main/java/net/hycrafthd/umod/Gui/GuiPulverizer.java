package net.hycrafthd.umod.Gui;

import java.io.IOException;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.Gui.Container.ContainerPulverizer;
import net.hycrafthd.umod.TileEntity.TileEntityPulverizer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiPulverizer extends GuiBase{

	public GuiPulverizer(EntityPlayer pl, IInventory tile,World w) {
		super(new ResourceLocation(UReference.modid, "textures/gui/pulver.png"), pl, tile, new ContainerPulverizer(tile, pl, w));
	}
	
	@Override
	public void initGui() {
		super.initGui();
		GuiButton btn = new GuiButton(0, 100, 20,50,20, "Sign with Player");
		buttonList.add(btn);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
 	    TileEntityPulverizer p = (TileEntityPulverizer) this.ent;
		fontRendererObj.drawString(((TileEntityPulverizer)this.ent).getTime() + "%", 240, 64, 0x00000);
		if(p.getSigndPlayerName() != null){
			((GuiButton)buttonList.get(0)).displayString = "Unsign";
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
       switch(button.id){
       case 0:
    	   TileEntityPulverizer p = (TileEntityPulverizer) this.ent;
    	   if(button.displayString.equals("Sign with Player")){
    	   p.signPlayer(play);
    	   button.displayString = "Unsign";
    	   }else{
    	   p.signPlayer(null);
    	   }
    	   break;
       }
	}
}
