package net.hycrafthd.umod.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPulverizerSpecialRender extends TileEntitySpecialRenderer{
	
	private EntityItem ent = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ,
			float p_180535_8_, int p_180535_9_) {
		TileEntityPulverizer oven = (TileEntityPulverizer) tileEntity;	
		ItemStack ore = oven.getStackInSlot(3);
		if (ore != null)
		{
	    double d3 = Minecraft.getMinecraft().thePlayer.getDistanceSqToEntity(Minecraft.getMinecraft().getRenderManager().livingPlayer);

		if (d3 <= (double)(p_180535_9_ * p_180535_9_))
		{
		FontRenderer fontrenderer = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX + 0.5F, (float)posY + 1.5F, (float)posZ + 0.5F);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-f1, -f1, f1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        byte b0 = 0;
        GlStateManager.disableTexture2D();
        int j = fontrenderer.getStringWidth(ore.getDisplayName()) / 2;
        RGBA rgba = new RGBA(Color.ORANGE);
        RGBA rgba2 = new RGBA(Color.RED);
        rgba.setAlpha(125);
        rgba2.setAlpha(125);
        this.drawGradientRect((double)(-j - 1), (double)(-1 + b0), (double)(j + 1), (double)(8 + b0), rgba, rgba2);
        this.drawHLine((double)(-j - 1),  (double)(j + 1), (double)(-2 + b0), new RGBA(Color.GRAY));
        this.drawHLine((double)(-j - 1),  (double)(j + 1), (double)(8 + b0), new RGBA(Color.GRAY));
        this.drawVLine((double)(-j - 2), (double)(-1 + b0), (double)(8 + b0),  new RGBA(Color.GRAY));
        this.drawVLine((double)(j + 1), (double)(-1 + b0), (double)(8 + b0),  new RGBA(Color.GRAY));
        GlStateManager.enableTexture2D();
        fontrenderer.drawString(ore.getDisplayName(), -fontrenderer.getStringWidth(ore.getDisplayName()) / 2, b0, 0x3ADF00);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        fontrenderer.drawString(ore.getDisplayName(), -fontrenderer.getStringWidth(ore.getDisplayName()) / 2, b0, 0x3ADF00);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
		}
		GlStateManager.pushMatrix();
		{
			
			GlStateManager.translate(posX + 0.3, posY + 0.52, posZ + 0.5);
			GlStateManager.scale(1.5, 1.5, 1.5);

					ent.setEntityItemStack(new ItemStack(ore.getItem(),1,ore.getMetadata()));
			        GlStateManager.enableRescaleNormal();
					GL11.glPushMatrix();
					{
						WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
						renderer.setBrightness(15728880);

						Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(ent, 0.15D, -0.3D, 0.0D, 0, 0);
						
					}
					GL11.glPopMatrix();
		}
		GlStateManager.popMatrix();
		
	}
  }
	
	
	public void drawHLine(double x, double x2, double y, RGBA r) {
		drawGradientRect(x, y, x2, y + 1, r, r);
	}

	public void drawVLine(double x, double y, double down, RGBA r) {
		drawGradientRect(x, y, x + 1, down, r, r);
	}

	public void drawGradientRect(double left, double top, double right, double bottom, RGBA start, RGBA end) {
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
		worldrenderer.addVertex((double) right, (double) top, (double) 0.1D);
		worldrenderer.addVertex((double) left, (double) top, (double) 0.1D);
		worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
		worldrenderer.addVertex((double) left, (double) bottom, (double) 0.1D);
		worldrenderer.addVertex((double) right, (double) bottom, (double) 0.1D);
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}
}
