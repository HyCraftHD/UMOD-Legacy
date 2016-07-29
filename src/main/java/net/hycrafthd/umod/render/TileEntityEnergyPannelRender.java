package net.hycrafthd.umod.render;

import java.awt.Color;

import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEnergyPannelRender extends TileRender{

	@Override
	public void renderTileEntityAt(TileEntity ent, double posX, double posY, double posZ) {
		TileEntityEnergyPannel tile = (TileEntityEnergyPannel) ent;
		if(tile.hasBlockPos()){
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX , (float)posY + 2, (float)posZ);
        GlStateManager.scale(-f1, -f1, f1);
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableTexture2D();
        LWJGLUtils.drawGradientRectWithMultiplier(0, -40, 120, 40, new RGBA(Color.CYAN), new RGBA(Color.CYAN).setAlpha(155), 1D, 0.01D);
        GlStateManager.rotate(180F, 0F, 0F, 1.0F);
        LWJGLUtils.drawGradientRectWithMultiplier(37, -40, 120, 40, new RGBA(Color.CYAN), new RGBA(Color.CYAN).setAlpha(155), 1D, 0.01D);
        GlStateManager.rotate(180F, 0F, 1.0F, 0);
        GlStateManager.rotate(180F, 0F, 0F, 1.0F);
        LWJGLUtils.drawGradientRect(0D, -40D, 37D, 40D, new RGBA(Color.CYAN).setAlpha(155), new RGBA(Color.CYAN).setAlpha(155), -1D);
        GlStateManager.rotate(180F, 1.0F, 0F, 0F);
        LWJGLUtils.drawGradientRect(0D, -40D, 37D, 40D, new RGBA(Color.CYAN).setAlpha(155), new RGBA(Color.CYAN).setAlpha(155), 1D);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
		}
	}

}
