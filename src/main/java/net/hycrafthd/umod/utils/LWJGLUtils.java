package net.hycrafthd.umod.utils;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.gui.GuiRescources;
import net.hycrafthd.umod.render.RGBA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

@SuppressWarnings("deprecation")
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
	
	public static void drawGradientRectWithMultiplier(int left, int top, int right, int bottom, RGBA start, RGBA end,double z,double multiplier,boolean flag) {
		double oldZ = z;
		for(int i = 0;i < right;i++){
		z = z + (multiplier + (i * 0.005));
		GlStateManager.pushMatrix();
	    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
	 	GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
	    GlStateManager.disableLighting();
	    GlStateManager.depthMask(false);
	    GlStateManager.enableBlend();
	    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
		worldrenderer.addVertex((double) left + i, (double) top, (double) oldZ);
		worldrenderer.addVertex((double) left + 1 + i, (double) top, (double) z);
		worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
		worldrenderer.addVertex((double) left + 1 + i, (double) bottom, (double) z);
		worldrenderer.addVertex((double) left + i, (double) bottom, (double) oldZ);
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180F, 0F, 1.0F, 0F);
		if(left > 0){
		GlStateManager.translate(-left, 0, 0);
		}else{
	    GlStateManager.translate(37, 0, 0);
		}
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA(start.getRed(), start.getGreen(), start.getBlue(), start.getAlpha());
		worldrenderer.addVertex((double) left + i, (double) top, (double) -oldZ);
		worldrenderer.addVertex((double) left + 1 + i, (double) top, (double) -z);
		worldrenderer.setColorRGBA(end.getRed(), end.getGreen(), end.getBlue(), end.getAlpha());
		worldrenderer.addVertex((double) left + 1 + i, (double) bottom, (double) -z);
		worldrenderer.addVertex((double) left + i, (double) bottom, (double) -oldZ);
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
		GlStateManager.popMatrix();
		oldZ = z;
		}
		if(flag){
		//drawGradientRectWithMultiplier(-left, -top, -right, -bottom, start, end, oldZ, multiplier,false);
		}
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
	
	public static void drawOnlyStringInWorld(BlockPos pos,double posX,double posY,double posZ,int p_180535_9_,String text,int color){
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
	public static void drawSmThInWorld(BlockPos pos,double posX,double posY,double posZ,Runnable run){
		EntityPlayer pl  = Minecraft.getMinecraft().thePlayer;
        BlockPos po = pl.getPosition();
		if (pos.getX() + 10 > po.getX() && pos.getX() - 10 < po.getX() && pos.getY() + 10 > po.getY() && pos.getY() - 10 < po.getY() && pos.getZ() + 10 > po.getZ() && pos.getZ() - 10 < po.getZ())
		{
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
        GlStateManager.disableTexture2D();
        run.run();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glNormal3f(1.0F, 1.0F, 1.0F);
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
		}
	}
	
	public static void drawFrameInWorld(BlockPos pos,double posX,double posY,double posZ,final double width,final double height,final RGBA frame){
	   drawSmThInWorld(pos, posX, posY, posZ, new Runnable() {
		@Override
		public void run() {
			GlStateManager.shadeModel(7425);
	        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
	        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
	        GlStateManager.enableDepth();
	        GlStateManager.color(1, 1, 1);
	        drawFrame((double)(-width/2 - 1), (double)(-1 + 0), (double)(width + 1), (double)(height + 0), frame);
	        GlStateManager.enableDepth();
	        GlStateManager.enableLighting();
			GlStateManager.shadeModel(7424);
		}
	   });
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
	
    
	public static void renderBlockConduit(ItemStack stack, double x, double y,double z)
    {
        IBakedModel ibakedmodel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.HEAD);
        renderItem(stack, ibakedmodel,x,y,z);
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
    }
    
	public static void renderItem(ItemStack stack, IBakedModel model,double x,double y,double z)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x , y, z);
       
            renderModel(model, -1,stack);

        GlStateManager.popMatrix();
    }
    
	private static void renderModel(IBakedModel model, int color, ItemStack stack)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.setVertexFormat(DefaultVertexFormats.ITEM);
        EnumFacing[] aenumfacing = EnumFacing.values();
        int j = aenumfacing.length;

        for (int k = 0; k < j; ++k)
        {
            EnumFacing enumfacing = aenumfacing[k];
            renderQuads(worldrenderer, model.getFaceQuads(enumfacing), color, stack);
        }

        renderQuads(worldrenderer, model.getGeneralQuads(), color, stack);
        tessellator.draw();
    }
    
    @SuppressWarnings("rawtypes")
	private static void renderQuads(WorldRenderer renderer, List quads, int color, ItemStack stack)
    {
        boolean flag = color == -1 && stack != null;
        BakedQuad bakedquad;
        int j;

        for (Iterator iterator = quads.iterator(); iterator.hasNext(); renderQuad(renderer, bakedquad, j))
        {
            bakedquad = (BakedQuad)iterator.next();
            j = color;

            if (flag && bakedquad.hasTintIndex())
            {
                j = stack.getItem().getColorFromItemStack(stack, bakedquad.getTintIndex());

                if (EntityRenderer.anaglyphEnable)
                {
                    j = TextureUtil.anaglyphColor(j);
                }

                j |= -16777216;
            }
        }
    }
    
    private static void renderQuad(WorldRenderer renderer, BakedQuad quad, int color)
    {
        renderer.addVertexData(quad.getVertexData());
        if(quad instanceof net.minecraftforge.client.model.IColoredBakedQuad)
            net.minecraftforge.client.ForgeHooksClient.putQuadColor(renderer, quad, color);
        else
        renderer.putColor4(color);
        putQuadNormal(renderer, quad);
    }

    private static void putQuadNormal(WorldRenderer renderer, BakedQuad quad)
    {
        Vec3i vec3i = quad.getFace().getDirectionVec();
        renderer.putNormal((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
    }
    
    public static void drawTexturePoints(String text,Vec3 vec,Vec3 vec1,Vec3 vec2,Vec3 vec3){
    	Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(text));
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        worldrenderer.addVertexWithUV( vec.xCoord, vec.yCoord, vec.zCoord, 0,0);
        worldrenderer.addVertexWithUV( vec1.xCoord, vec1.yCoord, vec1.zCoord, 0,0);
        worldrenderer.addVertexWithUV( vec2.xCoord, vec2.yCoord, vec2.zCoord, 0,0);
        worldrenderer.addVertexWithUV( vec3.xCoord, vec3.yCoord, vec3.zCoord, 0,0);
        tessellator.draw();
    }

	public static void drawSwell(String text,double x,double y,double z){
    	GlStateManager.pushMatrix();
    	GlStateManager.translate(x, y, z);
    	    	
    	Vec3 corn1 = new Vec3(0.4, 0.4, 0.4);
    	Vec3 corn2 = new Vec3(-0.4, 0.4, 0.4);
    	Vec3 corn3 = new Vec3(0.5, 0, 0.5);
    	Vec3 corn4 = new Vec3(-0.5, 0, 0.5);
    	drawTexturePoints(text,corn1, corn2, corn3, corn4);
    	
    	GlStateManager.rotate(180F, 0F, 1.0F, 0F);
    	
    	Vec3 corn12 = new Vec3(0.4, 0.4, 0.4);
    	Vec3 corn22 = new Vec3(-0.4, 0.4,0.4);
    	Vec3 corn32 = new Vec3(0.5, 0, 0.5);
    	Vec3 corn42 = new Vec3(-0.5, 0, 0.5);
    	drawTexturePoints(text,corn12, corn22, corn32, corn42);
    	
    	drawTexture(new ResourceLocation(text), 100, 100, 0, 0, 0, 100, 100, 0, 0);
    	
    	GlStateManager.popMatrix();
    }
}
