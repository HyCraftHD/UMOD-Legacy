package net.hycrafthd.umod.gui;

import java.io.IOException;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerPulverizer;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiPulverizer extends GuiBase{

	public GuiPulverizer(EntityPlayer pl, IInventory tile,World w) {
		super(new ResourceLocation(UReference.modid, "textures/gui/pulver.png"), pl, tile, new ContainerPulverizer(tile, pl, w));
	}
	
	@Override
	public void initGui() {
		super.initGui();
		GuiButton btn = new GuiButton(0, this.width/2-42, 20,85,20, "Sign with Player");
		buttonList.add(btn);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
 	    TileEntityPulverizer p = (TileEntityPulverizer) this.ent;
		fontRendererObj.drawString(((TileEntityPulverizer)this.ent).getTime() + "%", 240, 64, 0x00000);
		if(p.getSigndPlayerName() != null){
			((GuiButton)buttonList.get(0)).displayString = "Unsign";
		}else{
			((GuiButton)buttonList.get(0)).displayString = "Sign with Player";
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
    	   play.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Unsigned Pulverizer"));
    	   p.signPlayer(null);
    	   }
    	   p.markDirty();
    	   break;
       }
	}
}
