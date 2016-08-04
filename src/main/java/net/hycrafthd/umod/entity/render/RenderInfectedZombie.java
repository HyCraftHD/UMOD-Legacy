package net.hycrafthd.umod.entity.render;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.entity.model.ModelInfectedZombie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class RenderInfectedZombie extends RenderLiving {
	
	public RenderInfectedZombie() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelInfectedZombie(), 0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(UReference.resource + "textures/entity/InfectedZombie.png");
	}
	
}
