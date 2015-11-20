package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.container.ContainerFass;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiFass extends GuiContainer{

    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
	
	public GuiFass(IInventory inv, EntityPlayer pl, BlockPos pos, World wo) {
		super(new ContainerFass(inv, pl, pos, wo));
	}

	 protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	 {
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 3 * 18 + 17);
	        this.drawTexturedModalRect(k, l + 3 * 18 + 17, 0, 126, this.xSize, 96);
	  }
	
}
