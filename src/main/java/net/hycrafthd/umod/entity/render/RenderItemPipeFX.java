package net.hycrafthd.umod.entity.render;

import net.hycrafthd.umod.render.TileEntityItemPipeRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3i;

public class RenderItemPipeFX extends RenderEntity{

	public RenderItemPipeFX() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		TileEntity cab = (TileEntity) entity.getEntityWorld().getTileEntity(entity.getPosition().subtract(new Vec3i(0, 1, 0)));
		if(cab != null){
    	TileEntityItemPipeRender.renderTileEntityAt(cab,x - 0.5, y- 0.5, z- 0.5, p_76986_8_, (int) Math.round(partialTicks));
		}
	}
	
}
