package net.hycrafthd.umod.utils;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.render.RGBA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class LWJGLUtils {

	public static void drawHLine(double x, double x2, double y, RGBA r) {
		drawGradientRect(x, y, x2, y + 1, r, r);
	}

	public static void drawVLine(double x, double y, double down, RGBA r) {
		drawGradientRect(x, y, x + 1, down, r, r);
	}
	
	public static void drawHLine(double x, double x2, double y, RGBA r,double z) {
		drawGradientRect(x, y, x2, y + 1, r, r,z);
	}

	public static void drawVLine(double x, double y, double down, RGBA r,double z) {
		drawGradientRect(x, y, x + 1, down, r, r,z);
	}

	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA rgb,double z){
		drawGradientRect(left, top, right, bottom, rgb, rgb,z);
	}
	
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA rgb){
		drawGradientRect(left, top, right, bottom, rgb, rgb);
	}
	
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA start, RGBA end){
		drawGradientRect(left, top, right, bottom, start, end, 0);
	}
	
	public static void drawGradientRect(double left, double top, double right, double bottom, RGBA start, RGBA end,double z) {
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
		worldrenderer.addVertex((double) right, (double) top, (double) z);
		worldrenderer.addVertex((double) left, (double) top, (double) z);
		worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
		worldrenderer.addVertex((double) left, (double) bottom, (double) z);
		worldrenderer.addVertex((double) right, (double) bottom, (double) z);
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}
	
	public static void drawFrame(double x ,double y,double width,double height,RGBA rgb){
	      drawFrame(x, y, width, height, rgb, 0);
	}
	
	public static void drawFrame(double x ,double y,double width,double height,RGBA rgb,double z){
		drawHLine(x - 1, x + width + 1, y - 1, rgb,z);
		drawHLine(x - 1, x + width + 1, y + height, rgb,z);
		drawVLine(x - 1, y, y + height, rgb,z);
		drawVLine(x + width, y, y + height, rgb,z);
	}
	
	public static void drawStringInWorld(BlockPos pos,double posX,double posY,double posZ,int p_180535_9_,String text,RGBA start,RGBA end,RGBA frame,int color){
		EntityPlayer pl  = Minecraft.getMinecraft().thePlayer;
        BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ())
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
        byte b0 = 0;
        GlStateManager.disableTexture2D();
        int j = fontrenderer.getStringWidth(text) / 2;
        int j2 = fontrenderer.getStringWidth(text);
        drawGradientRect((double)(-j - 1), (double)(-1 + b0), (double)(j + 1), (double)(8 + b0), start, end);
        drawFrame((double)(-j - 1), (double)(-1 + b0), (double)(j2 + 1), (double)(8 + b0), frame);
        GlStateManager.enableTexture2D();
        fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, color);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        fontrenderer.drawString(text, -fontrenderer.getStringWidth(text) / 2, b0, color);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
		}
	}
	
	public static void drawTexture(ResourceLocation location, double textureWidth, double textureHeight, double x, double y,double z, double width, double height, double u, double v){
		Minecraft.getMinecraft().getTextureManager().bindTexture(location);
		double f4 = 1.0F / textureWidth;
		double f5 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV((double)x, (double)(y + height), z, (double)(u * f4), (double)((v + (float)height) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)(y + height), z, (double)((u + (float)width) * f4), (double)((v + (float)height) * f5));
        worldrenderer.addVertexWithUV((double)(x + width), (double)y, z, (double)((u + (float)width) * f4), (double)(v * f5));
        worldrenderer.addVertexWithUV((double)x, (double)y, z, (double)(u * f4), (double)(v * f5));
        tessellator.draw();
	}
	
	public static void drawTexturedCube(ResourceLocation location, double x, double y,double z, double width, double height,double depth){
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + width/2, y + height/2, z + depth/2);
		GlStateManager.shadeModel(7425);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableLighting();
		drawTexture(location, width, height, -width/2, -height/2, -depth/2, width, height, 0, 0);
		GlStateManager.rotate(90, 1.0F, 0, 0F);
		drawTexture(location, width, depth, -width/2,-depth/2,-height/2,  width, depth, 0, 0);
		GlStateManager.rotate(180, 1.0F, 0, 0F);
		drawTexture(location, width, depth, -width/2,-depth/2,-height/2,  width, depth, 0, 0);
		GlStateManager.rotate(270, 1.0F, 0, 0F);
	    drawTexture(location, width, height, -width/2, -height/2, -depth/2, width, height, 0, 0);
		GlStateManager.rotate(90, 0F, 1.0F, 0F);
		drawTexture(location, depth, height, -depth/2,-height/2,-width/2, depth, height, 0, 0);
		GlStateManager.rotate(-90, 0F, 1.0F, 0F);
		GlStateManager.rotate(90, 0F, 1.0F, 0F);
		GlStateManager.rotate(-180, 1.0F, 0F, 0F);
		drawTexture(location, depth, height, -depth/2,-height/2,-width/2, depth, height, 0, 0);
		GlStateManager.enableLighting();
		GlStateManager.shadeModel(7424);
		GlStateManager.popMatrix();
	}
	
	public static void drawBlock(ResourceLocation loc,double posX ,double posY, double posZ,double d,double e,double f){
		drawTexturedCube(loc, posX + (0.5 - d/2), posY + (0.5 - e/2), posZ + (0.5 - f/2), d, e, f);
	}
}
