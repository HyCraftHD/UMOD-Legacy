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
	//private boolean bsd = true;
		
	public RenderFX() {
		super(Minecraft.getMinecraft().getRenderManager());
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
		if(entity == null || entity.getPosition() == null)return;
		BlockPos pos = entity.getPosition().subtract(new Vec3i(0, 1, 0));
		//if(bsd){System.out.println(pos);bsd = false;}
		TileEntity ent = entity.getEntityWorld().getTileEntity(pos);
		if(ent == null){
			entity.setDead();
			return;
		}
		TileRender ren = list.get(ent.getClass());
		if(ren == null)return;
		Vec3 vc = ((EntityFX) entity).getPositionVector();
    	ren.renderTileEntityAt(ent,vc.xCoord - 0.5, vc.yCoord- 0.5, vc.zCoord- 0.5);
	}
	
	public static void register(Class<? extends TileEntity> ent,TileRender rend){
		list.put(ent, rend);
	}
}
