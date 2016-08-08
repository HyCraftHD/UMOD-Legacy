package net.hycrafthd.umod.render;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;

public abstract class TileRender {

	
	public final void render(TileEntity p_180535_1_, double posX, double posY, double posZ){
//		UMod.log.info(p_180535_1_.getPos().toString());
//		UMod.log.info("Render Pos[" + posX + "," + posY + "," + posZ + "]");
		if(ConduitRender.render(p_180535_1_, Minecraft.getMinecraft().thePlayer, posX, posY, posZ))return;
		System.out.println("No Conduit detected, Pass to real Render");
		this.renderTileEntityAt(p_180535_1_, posX, posY, posZ);
	}

    public abstract void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ);
	
}
