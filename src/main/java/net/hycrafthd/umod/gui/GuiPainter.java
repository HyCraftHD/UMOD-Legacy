package net.hycrafthd.umod.gui;

import java.awt.Color;

import net.hycrafthd.umod.render.RGBA;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class GuiPainter extends GuiBase {

	public GuiPainter(EntityPlayer pl,IInventory tile, Container con) {
		super(new GuiRescources("painter.png"), new GuiRescources("battery.png"), new GuiRescources("IOMode.png"), pl, tile, con);
	}

	public GuiSlider red,green,blue;
	
	public void initGui() {
		super.initGui();
		int x = this.guiLeft + 36;
		int y = this.guiTop;
		red = new GuiSlider(x, y + 11, new RGBA(Color.red).setAlpha(155), new RGBA(Color.black),0);
		green = new GuiSlider(x, y + 32, new RGBA(Color.green).setAlpha(155), new RGBA(Color.black),0);
		blue = new GuiSlider(x, y + 53, new RGBA(Color.blue).setAlpha(155), new RGBA(Color.black),0);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		red.draw(mc);
		green.draw(mc);
		blue.draw(mc);
		red.drawOverlay(mc, mouseX, mouseY);
		green.drawOverlay(mc, mouseX, mouseY);
		blue.drawOverlay(mc, mouseX, mouseY);
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
            box2.getItems().add("Input");
		
	}

	@Override
	public void onIOModeSwitched() {
           
	}

	@Override
	public void handelMouseInput(int mouseX, int mouseY) {
	        red.handelMouseClicked(mouseX, mouseY);
	        green.handelMouseClicked(mouseX, mouseY);
	        blue.handelMouseClicked(mouseX, mouseY);
	}
	
	@Override
	public void onMouseClickMoved(int mouseX, int mouseY) {
		handelMouseInput(mouseX, mouseY);
	}
}
