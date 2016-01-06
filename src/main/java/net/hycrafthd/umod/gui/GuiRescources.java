package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.UReference;
import net.minecraft.util.ResourceLocation;

public class GuiRescources extends ResourceLocation{

	public GuiRescources(String name) {
		super(UReference.modid,"textures/gui/" + name);
	}
	
}
