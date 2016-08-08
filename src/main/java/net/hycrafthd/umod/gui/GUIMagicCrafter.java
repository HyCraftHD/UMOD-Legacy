package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.tileentity.TileEntityMagicCrafter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class GUIMagicCrafter extends GuiContainer{

	public static final ResourceLocation background = new ResourceLocation("umod:textures/gui/magic_crafter.png");
	public static BlockPos position;
	public static World world;
	
	public GUIMagicCrafter(Container container) {
		super(container);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		int i = TileEntityMagicCrafter.getfromTileEntity(world.getTileEntity(position)).cooldown / 40;
		mc.renderEngine.bindTexture(background);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		
		if(i == 0){
			//nothing
		}else if(i < 1){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 1);
		}else if(i < 2){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 2);
		}else if(i < 3){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 3);
		}else if(i < 4){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 5);
		}else if(i < 5){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 7);
		}else if(i < 6){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 8);
		}else if(i < 7){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 9);
		}else if(i < 8){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 11);
		}else if(i < 9){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 12);
		}else if(i < 10){
			drawTexturedModalRect(k + 98, l + 34, 177, 1, 7, 13);
		}
	}
	
	public static void setLocation(BlockPos pos, World w){
		
		position = pos;
		world = w;
		
	}
	
}
