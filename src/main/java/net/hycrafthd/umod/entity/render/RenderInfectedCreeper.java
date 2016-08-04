package net.hycrafthd.umod.entity.render;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.model.ModelInfectedCreeper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderInfectedCreeper extends RenderLiving {
	
	public RenderInfectedCreeper() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelInfectedCreeper(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(UReference.resource + "textures/entity/InfectedCreeper.png");
	}
	
}
