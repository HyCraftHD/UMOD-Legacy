package net.hycrafthd.umod.entity.render.rail;

import net.hycrafthd.umod.utils.LWJGLUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;

public class RenderRailFX extends RenderEntity{
	
	public RenderRailFX() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		LWJGLUtils.drawSwell("textures/blocks/stone.png", x - 0.5, y - 0.5, z - 0.5);
	}

}
