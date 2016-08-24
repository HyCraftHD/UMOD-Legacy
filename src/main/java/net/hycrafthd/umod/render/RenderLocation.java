package net.hycrafthd.umod.render;

import net.hycrafthd.umod.UReference;
import net.minecraft.util.ResourceLocation;

public class RenderLocation extends ResourceLocation{

	public RenderLocation(String st) {
		super(UReference.modid,"textures/render/" + st);
	}

}
