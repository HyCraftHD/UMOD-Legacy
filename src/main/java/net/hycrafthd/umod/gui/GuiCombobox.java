package net.hycrafthd.umod.gui;

import java.awt.Color;
import java.util.ArrayList;

import net.hycrafthd.corelib.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiCombobox extends Gui {
	
	private ArrayList<String> strs = new ArrayList<String>();
	private String slected = "Choose";
	private boolean extend = false;
	private int x;
	private int y;
	private int width;
	private int height;
	private Runnable runn = null;
	
	public GuiCombobox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Minecraft mc) {
		RGBA rgb = new RGBA(Color.WHITE);
		LWJGLUtils.drawGradientRect(x, y, x + width, y + height, rgb, rgb, this.zLevel);
		RGBA rgb2 = new RGBA(Color.DARK_GRAY);
		LWJGLUtils.drawHLine(x, x + width, y, rgb2, this.zLevel);
		LWJGLUtils.drawHLine(x, x + width, y + height, rgb2, this.zLevel);
		LWJGLUtils.drawVLine(x, y, y + height, rgb2, this.zLevel);
		LWJGLUtils.drawVLine(x + width, y, y + height, rgb2, this.zLevel);
		
		FontRenderer fontrenderer = mc.fontRendererObj;
		if (!extend) {
			mc.getTextureManager().bindTexture(new GuiRescources("menudown.png"));
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			drawModalRectWithCustomSizedTexture(x + width - 15, y + height / 2 - 4, 0, 0, 15, 9, 15, 9);
			fontrenderer.drawString(slected, x + 3, y + 2, 0x000000);
		} else {
			mc.getTextureManager().bindTexture(new GuiRescources("menuup.png"));
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			drawModalRectWithCustomSizedTexture(x + width - 15, y + height / 2 - 4, 0, 0, 15, 9, 15, 9);
			LWJGLUtils.drawGradientRect(x + 1, y + height, x + width - 2, y + strs.size() * 18, rgb, rgb, this.zLevel);
			for (int i = 0; i < strs.size(); i++) {
				fontrenderer.drawString(strs.get(i), x + 3, y + height + 4 + 9 * i, 0x000000);
			}
		}
	}
	
	public ArrayList<String> getItems() {
		return strs;
	}
	
	public boolean isMouseover(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}
	
	public void handelClick(int x, int y) {
		if (extend) {
			if (x >= this.x && x <= this.x + this.width && y >= this.y + this.height) {
				for (int i = 0; i < strs.size(); i++) {
					if (y <= this.y + this.height + (9 * (i + 1))) {
						setSelected(i);
						extend = false;
						if (runn != null) {
							runn.run();
						}
						return;
					}
				}
			}
		}
		if (isMouseover(x, y)) {
			extend = !extend;
		}
		
	}
	
	public void setOnListClicked(Runnable run) {
		runn = run;
	}
	
	public void setSelected(int i) {
		slected = strs.get(i);
	}
	
	public int getSelceted() {
		return strs.indexOf(slected);
	}
}
