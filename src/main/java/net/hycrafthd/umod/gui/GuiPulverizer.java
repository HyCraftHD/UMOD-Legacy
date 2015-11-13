package net.hycrafthd.umod.gui;

import java.io.IOException;

<<<<<<< HEAD
import org.lwjgl.opengl.GL11;

=======
>>>>>>> 64322378d8be2401f632ecef38717edb27145f2f
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerPulverizer;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.gui.GuiButton;
<<<<<<< HEAD
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
=======
>>>>>>> 64322378d8be2401f632ecef38717edb27145f2f
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
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
		GuiButton btn = new GuiButton(0, 200, 20,85,20, "Sign with Player");
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
<<<<<<< HEAD
		
=======
>>>>>>> 64322378d8be2401f632ecef38717edb27145f2f
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
<<<<<<< HEAD
    	   p.markDirty();
    	   break;
       }
	}

	
	public void drawColour(int x, int y, int width, int height, int par4)
	{
		WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		renderer.startDrawingQuads();
		renderer.setColorOpaque_I(par4);
		renderer.addVertex(x, y, 0.0D);
		renderer.addVertex(x, y + height, 0.0D);
		renderer.addVertex(x + width, y + height, 0.0D);
		renderer.addVertex(x + width, y, 0.0D);
		Tessellator.getInstance().draw();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
=======
    	   break;
       }
	}
>>>>>>> 64322378d8be2401f632ecef38717edb27145f2f
}
