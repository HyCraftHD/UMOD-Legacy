package net.hycrafthd.umod.gui;

import java.io.IOException;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerPulverizer;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiPulverizer extends GuiBase{
	
	public BlockPos pos;

	public GuiPulverizer(EntityPlayer pl, IInventory tile,World w,BlockPos pos) {
		super(new ResourceLocation(UReference.modid, "textures/gui/pulver.png"), pl, tile, new ContainerPulverizer(tile, pl, w));
		this.pos = pos;
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
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
 	    TileEntityPulverizer p = (TileEntityPulverizer) this.ent;
		fontRendererObj.drawString(((TileEntityPulverizer)this.ent).getTime() + "%", this.width/2-5, 64, 0x00000);
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
       case 1:
       case 2:
    	   this.play.closeScreen();
    	   this.play.openGui(UReference.modid, 2, this.play.worldObj, this.pos.getX(), this.pos.getY(),this.pos.getZ());
       }
	}
}
