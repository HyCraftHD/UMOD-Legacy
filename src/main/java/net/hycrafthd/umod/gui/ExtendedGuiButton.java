package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class ExtendedGuiButton extends GuiButton {
	
	private StringMethod ret = null;
	private int color = 0xFFFFFF;
	
	public ExtendedGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
	public boolean hasString() {
		return ret != null && ret.getString() != null;
	}
	
	public String getString() {
		return ret.getString();
	}
	
	public void setStringRet(StringMethod returnm) {
		ret = returnm;
	}
	
	public int getWidth() {
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
	
	public int getHeight() {
		int size = 16;
		if (hasMoreLines()) {
			String[] str = ret.getString().split("\n");
			size = size * str.length;
		}
		return size;
	}
	
	public boolean hasMoreLines() {
		return ret.getString().contains("\n");
	}
	
	public int getFontColor() {
		return color;
	}
	
	public void setFontColor(int i) {
		color = i;
	}
}
