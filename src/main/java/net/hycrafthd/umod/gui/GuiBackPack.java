package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.container.ContainerBackPack;
import net.hycrafthd.umod.enumtype.EnumTypeBackPack;
import net.hycrafthd.umod.inventory.InventoryBackPack;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiBackPack extends GuiContainer {
	
	private ResourceLocation texture_small = new GuiRescources("backpack/small.png");
	private ResourceLocation texture_medium = new GuiRescources("backpack/medium.png");
	private ResourceLocation texture_big = new GuiRescources("backpack/big.png");
	
	private EnumTypeBackPack type;
	
	public GuiBackPack(InventoryBackPack inventorybackpack, InventoryPlayer inventoryplayer, EnumTypeBackPack type) {
		super(new ContainerBackPack(inventorybackpack, inventoryplayer, type));
		this.type = type;
		xSize = 176;
		switch (type) {
		case BACKPACKSMALL:
			ySize = 166;
			break;
		case BACKPACKMEDIUM:
			ySize = 202;
			break;
		case BACKPACKBIG:
			ySize = 238;
			break;
		}
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// TODO mehr anzeigen
		this.fontRendererObj.drawString("Backpack", 7, 8, 4210752);
	}
	
	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		switch (type) {
		case BACKPACKSMALL:
			mc.getTextureManager().bindTexture(texture_small);
			break;
		case BACKPACKMEDIUM:
			mc.getTextureManager().bindTexture(texture_medium);
			break;
		case BACKPACKBIG:
			mc.getTextureManager().bindTexture(texture_big);
			break;
		}
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
}
