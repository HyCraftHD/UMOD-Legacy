package net.hycrafthd.umod.gui;

import java.awt.Color;
import java.io.IOException;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.container.ContainerBase.Mode;
import net.hycrafthd.umod.render.RGBA;
import net.hycrafthd.umod.utils.LWJGLUtils;
import net.hycrafthd.umod.utils.StringReturnment;
import net.minecraft.client.settings.KeyBinding;
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
		red = new GuiSlider(x, y + 11, new RGBA(Color.red).setAlpha(155), new RGBA(Color.black),new RGBA(Color.WHITE),0);
		red.setStringRet(new StringReturnment() {
			
			@Override
			public String getString() {
				return "Red Color: " + red.getValue();
			}
		});
		green = new GuiSlider(x, y + 32, new RGBA(Color.green).setAlpha(155), new RGBA(Color.black),new RGBA(Color.WHITE),0);
        green.setStringRet(new StringReturnment() {
			
			@Override
			public String getString() {
				return "Green Color: " + green.getValue();
			}
		});
		blue = new GuiSlider(x, y + 53, new RGBA(Color.blue).setAlpha(155), new RGBA(Color.black),new RGBA(Color.WHITE),0);
		blue.setStringRet(new StringReturnment() {
				
				@Override
				public String getString() {
					return "Blue Color: " + blue.getValue();
				}
		});
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		if(this.basecon.getMode().equals(Mode.NORMAL)){
		red.draw(mc);
		green.draw(mc);
		blue.draw(mc);
		LWJGLUtils.drawGradientRect(297, 73, 315, 115, new RGBA(red.getValue() * 255 / 100,green.getValue() * 255 / 100,blue.getValue() * 255 / 100,155));
		if(UMod.info.isKeyDown()){
		red.drawOverlay(mc, mouseX, mouseY);
		green.drawOverlay(mc, mouseX, mouseY);
		blue.drawOverlay(mc, mouseX, mouseY);
	    }
		}
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
            box2.getItems().add("Input");
		
	}

	@Override
	public void onIOModeSwitched() {
           
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		if(UMod.info.getKeyCode() == keyCode){
			if(!UMod.info.isKeyDown()){
			KeyBinding.setKeyBindState(keyCode, true);
			}else{
			KeyBinding.setKeyBindState(keyCode, false);
			}
		}
	}
	
	@Override
	public void handelMouseInput(int mouseX, int mouseY) {
		if(this.basecon.getMode().equals(Mode.NORMAL)){
	        red.handelMouseClicked(mouseX, mouseY);
	        green.handelMouseClicked(mouseX, mouseY);
	        blue.handelMouseClicked(mouseX, mouseY);
		}
	}
	
	@Override
	public void onMouseClickMoved(int mouseX, int mouseY) {
		handelMouseInput(mouseX, mouseY);
	}
}
