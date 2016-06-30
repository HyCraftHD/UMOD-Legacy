package net.hycrafthd.umod.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.ClientProxy;
import net.hycrafthd.umod.container.ContainerBase.Mode;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class GuiPainter extends GuiBase {

	public GuiPainter(EntityPlayer pl,IInventory tile, Container con) {
		super(new GuiRescources("painter.png"), new GuiRescources("battery.png"), new GuiRescources("IOMode.png"), pl, tile, con);
	}

	public GuiSlider red,green,blue,sat;
	
	public void initGui() {
		super.initGui();
		int x = this.guiLeft + 36;
		int y = this.guiTop;
		red = new GuiSlider(x, y + 11, new RGBA(Color.red).setAlpha(155), new RGBA(Color.black),new RGBA(Color.WHITE),0);
		red.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Red Color: " + red.getValue();
			}
		});
		green = new GuiSlider(x, y + 32, new RGBA(Color.green).setAlpha(155), new RGBA(Color.black),new RGBA(Color.WHITE),0);
        green.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Green Color: " + green.getValue();
			}
		});
		blue = new GuiSlider(x, y + 53, new RGBA(Color.blue).setAlpha(155), new RGBA(Color.black),new RGBA(Color.WHITE),0);
		blue.setStringRet(new StringMethod() {
				
				@Override
				public String getString() {
					return "Blue Color: " + blue.getValue();
				}
		});
		sat = new GuiSlider(x, y + 67, new RGBA(Color.WHITE).setAlpha(155), new RGBA(Color.black).setAlpha(155), new RGBA(Color.WHITE).setAlpha(155), 0);
		sat.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Saturation of Color: " + sat.getValue();
			}
	    });
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	
		GlStateManager.popMatrix();
		if(this.basecon.getMode().equals(Mode.NORMAL)){
			red.draw(mc);
			green.draw(mc);
			blue.draw(mc);
			sat.draw(mc);
			LWJGLUtils.drawGradientRect(297, 73, 315, 115, new RGBA(red.getValue() * 255 / 100,green.getValue() * 255 / 100,blue.getValue() * 255 / 100,sat.getValue() * 255 / 100));
			if(Keyboard.isKeyDown(ClientProxy.info.getKeyCode())){
			red.drawOverlay(mc, mouseX, mouseY);
			green.drawOverlay(mc, mouseX, mouseY);
			blue.drawOverlay(mc, mouseX, mouseY);
			sat.drawOverlay(mc, mouseX, mouseY);
			if(mouseX > 297 && mouseX < 315 && mouseY > 73 && mouseY < 115){
				FontRenderer rend = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
				String blued = "Blue: " + blue.getValue();
				String redd = "Red: " + red.getValue();
				String greend = "Green: " + green.getValue();
				int with = Math.max(Math.max(rend.getStringWidth(redd), rend.getStringWidth(greend)), rend.getStringWidth(blued));
				LWJGLUtils.drawGradientRect(mouseX, mouseY, mouseX + with + 12, mouseY + 16*3 + 4, new RGBA(new Color(red.getValue() * 255 / 100,green.getValue() * 255 / 100,blue.getValue() * 255 / 100,sat.getValue() * 255 / 100).darker()));
				rend.drawString(redd, mouseX + 6, mouseY + 6, 0xFF0000);
				rend.drawString(greend, mouseX + 6, mouseY + 22, 0x00FF00);
				rend.drawString(blued, mouseX + 6, mouseY + 38, 0x0000FF);
			}
		    }
		}
	    RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) this.guiLeft, (float) this.guiTop, 0.0F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableRescaleNormal();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
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
		
	}
	
	@Override
	public void handelMouseInput(int mouseX, int mouseY) {
		if(this.basecon.getMode().equals(Mode.NORMAL)){
	        red.handelMouseClicked(mouseX, mouseY);
	        green.handelMouseClicked(mouseX, mouseY);
	        blue.handelMouseClicked(mouseX, mouseY);
	        sat.handelMouseClicked(mouseX, mouseY);
		}
	}
	
	@Override
	public void onMouseClickMoved(int mouseX, int mouseY) {
		handelMouseInput(mouseX, mouseY);
	}
}
