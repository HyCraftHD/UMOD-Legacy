package net.hycrafthd.umod.gui;

import java.awt.Color;

import net.hycrafthd.corelib.util.*;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;

public class GuiCheckbox extends Gui {
	
	private double x;
	private double width;
	private double y;
	private double height;
	private RGBA rgb;
	private RGBA hover;
	private boolean isSelected;
	private StringMethod ret;
	private Runnable run;
	
	public GuiCheckbox(double x, double y, double width, double height, RGBA rgb, RGBA hover) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rgb = rgb;
		this.hover = hover;
	}
	
	public boolean isMouseOver(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}
	
	public void draw(int x, int y, Minecraft mc) {
		RGBA use = null;
		FontRenderer fontobj = mc.fontRendererObj;
		LWJGLUtils.drawFrame(this.x, this.y, this.width, this.height, new RGBA(Color.black), this.zLevel);
		if (isMouseOver(x, y)) {
			use = hover;
			LWJGLUtils.drawGradientRect(this.x, this.y, this.x + this.width, this.y + this.height, use, this.zLevel);
			if (isSelected) {
				fontobj.drawString("X", (int) Math.round((float) this.x + this.width / 2) - 2, (int) Math.round((float) this.y + this.height / 2) - 4, Color.RED.getRGB());
			}
			if (hasTooltip()) {
				LWJGLUtils.drawGradientRect(x, y, x + getWidth(), y + getHeight(), new RGBA(Color.white), this.zLevel);
				if (this.hasMoreLines()) {
					String[] str = ret.getString().split("\n");
					for (int i = 0; i < str.length; i++)
						fontobj.drawString(str[i], x + 4, y + 4 + (i * 16), 0x000000);
				} else {
					fontobj.drawString(ret.getString(), x + 4, y + 4, 0x000000);
				}
			}
		} else {
			use = rgb;
			LWJGLUtils.drawGradientRect(this.x, this.y, this.x + this.width, this.y + this.height, use, this.zLevel);
			if (isSelected) {
				fontobj.drawString("X", (int) Math.round((float) this.x + this.width / 2) - 2, (int) Math.round((float) this.y + this.height / 2) - 4, Color.RED.getRGB());
			}
		}
	}
	
	private int getWidth() {
		int size;
		size = ret.getString().length() * 5;
		if (hasMoreLines()) {
			size = 0;
			String[] str = ret.getString().split("\n");
			for (int i = 0; i < str.length; i++) {
				if (str[i].length() * 5 > size) {
					size = str[i].length() * 5;
				}
			}
		}
		return size + 10;
	}
	
	private int getHeight() {
		int size = 16;
		if (hasMoreLines()) {
			String[] str = ret.getString().split("\n");
			size = size * str.length;
		}
		return size;
	}
	
	public boolean isSelceted() {
		return isSelected;
	}
	
	public void setSelected(boolean b) {
		isSelected = b;
	}
	
	public boolean hasMoreLines() {
		return ret.getString().contains("\n");
	}
	
	public final void handelMouseClick(int x, int y) {
		if (this.isMouseOver(x, y)) {
			isSelected = !isSelected;
			if (run != null) {
				run.run();
			}
		}
	}
	
	public void setTooltip(StringMethod ret) {
		this.ret = ret;
	}
	
	public void setOnSlectedChanged(Runnable r) {
		run = r;
	}
	
	public boolean hasTooltip() {
		return ret != null;
	}
}
