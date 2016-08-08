package net.hycrafthd.umod.entity.render;

import java.util.HashMap;

import net.hycrafthd.umod.entity.EntityFX;
import net.hycrafthd.umod.render.TileRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;

public class RenderFX extends RenderEntity{

	private static HashMap<Class<? extends TileEntity>,TileRender> list = new HashMap<Class<? extends TileEntity>,TileRender>();
		
	public RenderFX() {
		super(Minecraft.getMinecraft().getRenderManager());
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		if (entity == null || entity.getPosition() == null)
			return;
		BlockPos pos = entity.getPosition();
		System.out.println(pos.toString());
		TileEntity ent = entity.getEntityWorld().getTileEntity(pos);
		if (ent == null) {
			entity.setDead();
			return;
		}
		System.out.println("Tile not null");
		TileRender ren = list.get(ent.getClass());
		if (ren == null)return;
		System.out.println("Pass to Render");
		Vec3 vc = ((EntityFX) entity).getPositionVector().add(new Vec3(-0.5, -0.5, -0.5));
    	ren.render(ent,vc.xCoord, vc.yCoord, vc.zCoord);
	}
	
	public static void register(Class<? extends TileEntity> ent, TileRender rend) {
		list.put(ent, rend);
	}
}
