package net.hycrafthd.umod.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPainterSpecialRender extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ,
			float p_180535_8_, int p_180535_9_) {
		WorldViewRender.INSTANCE.render(tileEntity, posX, posY, posZ);
	}

	
	
}
