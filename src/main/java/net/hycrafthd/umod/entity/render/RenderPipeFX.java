package net.hycrafthd.umod.entity.render;

import net.hycrafthd.umod.render.TileEntityCabelSpecialRender;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;

public class RenderPipeFX extends RenderEntity{

	public RenderPipeFX() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		TileEntityCable cab = (TileEntityCable) entity.getEntityWorld().getTileEntity(entity.getPosition().subtract(new Vec3i(0, 1, 0)));
		if(cab != null){
    	TileEntityCabelSpecialRender.renderTileEntityAt(cab,x - 0.5, y- 0.5, z- 0.5, p_76986_8_, (int) Math.round(partialTicks));
		}
	}
	
}
