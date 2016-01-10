package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.UReference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRescources extends ResourceLocation {

	public GuiRescources(String name) {
		super(UReference.modid, "textures/gui/" + name);
	}

}
