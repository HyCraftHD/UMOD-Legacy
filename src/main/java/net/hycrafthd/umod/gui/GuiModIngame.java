package net.hycrafthd.umod.gui;

import java.util.*;

import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.item.ItemEnergyDisplay;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class GuiModIngame {
	
	private static int ticks = 0;
	private static int zLevel = 0;
	public static ScaledResolution res;
	
	public static void renderGameOverlay(float partialTicks, ScaledResolution reso) {
		res = reso;
		onDraw();
	}
	
	private static void onDraw() {
		try {
			EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
			if (pl != null && pl.getCurrentEquippedItem() != null && (pl.getCurrentEquippedItem().getItem() instanceof ItemEnergyDisplay) && pl.getCurrentEquippedItem().hasTagCompound() && pl.getCurrentEquippedItem().getTagCompound().hasKey(ItemEnergyDisplay.NBT_TAG) && (pl.openContainer == null || pl.openContainer instanceof ContainerPlayer)) {
				drawScreen(pl);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
			System.err.println(e.getMessage());
			System.err.println("Excepted by " + e.getStackTrace()[0].getMethodName() + " in Class");
			System.err.println("" + e.getStackTrace()[0].getClassName() + "[" + e.getStackTrace()[0].getFileName() + "] Line " + e.getStackTrace()[0].getLineNumber());
			return;
		}
	}
	
	protected void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
		float f = (float) (startColor >> 24 & 255) / 255.0F;
		float f1 = (float) (startColor >> 16 & 255) / 255.0F;
		float f2 = (float) (startColor >> 8 & 255) / 255.0F;
		float f3 = (float) (startColor & 255) / 255.0F;
		float f4 = (float) (endColor >> 24 & 255) / 255.0F;
		float f5 = (float) (endColor >> 16 & 255) / 255.0F;
		float f6 = (float) (endColor >> 8 & 255) / 255.0F;
		float f7 = (float) (endColor & 255) / 255.0F;
		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setColorRGBA_F(f1, f2, f3, f);
		worldrenderer.addVertex((double) right, (double) top, (double) zLevel);
		worldrenderer.addVertex((double) left, (double) top, (double) zLevel);
		worldrenderer.setColorRGBA_F(f5, f6, f7, f4);
		worldrenderer.addVertex((double) left, (double) bottom, (double) zLevel);
		worldrenderer.addVertex((double) right, (double) bottom, (double) zLevel);
		tessellator.draw();
		GlStateManager.shadeModel(7424);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.enableTexture2D();
	}
	
	private static void drawScreen(EntityPlayer pl) {
		ticks++;
		
		double screenwidth = res.getScaledWidth_double();
		double screenheight = res.getScaledHeight_double();
		int width = (int) Math.round(screenwidth / 2);
		int height = (int) Math.round(screenheight / 4);
		
		String str = "No Ore Detected";
		World w = Minecraft.getMinecraft().theWorld;
		NBTTagCompound comp = (NBTTagCompound) pl.getCurrentEquippedItem().getTagCompound().getTag(ItemEnergyDisplay.NBT_TAG);
		BlockPos p = new BlockPos(comp.getInteger("X"), comp.getInteger("Y"), comp.getInteger("Z"));
		TileEntityPulverizer oven = (TileEntityPulverizer) w.getTileEntity(p);
		FontRenderer rend = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
		if (oven != null) {
			if (oven.getStackInSlot(3) != null) {
				str = oven.getStackInSlot(3).getDisplayName();
			}
			
			String energy = "Energy " + ((IPowerProvieder) oven).getStoredPower() + "/" + ((IPowerProvieder) oven).getMaximalPower();
			String stat = "Progress " + oven.getField(0) + "/100";
			String pos = "X=" + p.getX() + " Y=" + p.getY() + " Z=" + p.getZ();
			String name = I18n.format(oven.getWorld().getBlockState(oven.getPos()).getBlock().getUnlocalizedName() + ".name");
			
			GlStateManager.pushMatrix();
			{
				GlStateManager.translate(screenwidth / 2, height, 0);
				rend.drawStringWithShadow(name, -rend.getStringWidth(name) / 2, -14, 0xFFFFFF);
				rend.drawStringWithShadow(pos, -rend.getStringWidth(pos) / 2, -1, 0xFFFFFF);
				rend.drawStringWithShadow(str, -rend.getStringWidth(str) / 2, 9, 0xFFFFFF);
				rend.drawStringWithShadow(energy, -rend.getStringWidth(energy) / 2, 19, 0xFFFFFF);
				rend.drawStringWithShadow(stat, -rend.getStringWidth(stat) / 2, 29, 0xFFFFFF);
			}
			GlStateManager.popMatrix();
			
			GlStateManager.pushMatrix();
			{
				RenderHelper.enableGUIStandardItemLighting();
				GlStateManager.color(1, 1, 1);
				GlStateManager.translate(width, height, 0);
				renderItemIntoGUI(new ItemStack(w.getBlockState(p).getBlock()), -10, -40);
			}
			GlStateManager.popMatrix();
			
		} else {
			GlStateManager.pushMatrix();
			GlStateManager.enableDepth();
			GlStateManager.translate(width, height, 0);
			rend.drawStringWithShadow("Out of range", -rend.getStringWidth("Out of range") / 2, -14, 0xFFFFFF);
			GlStateManager.popMatrix();
		}
	}
	
	private static int trans = 45;
	
	private static void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d) {
		GlStateManager.translate((float) xPosition, (float) yPosition, 100.0F);
		GlStateManager.translate(8.0F, 8.0F, 0.0F);
		GlStateManager.scale(2.0F, 2.0F, -2.0F);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		
		if (isGui3d) {
			if (ticks >= 10) {
				trans++;
				ticks = 0;
			}
			if (trans >= 360) {
				trans = 0;
			}
			GlStateManager.scale(40.0F, 40.0F, 40.0F);
			GlStateManager.rotate(10F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(trans, 0.0F, 1.0F, 0.0F);
			GlStateManager.enableLighting();
		} else {
			GlStateManager.scale(64.0F, 64.0F, 64.0F);
			GlStateManager.disableLighting();
		}
	}
	
	public static void renderItemIntoGUI(ItemStack stack, int x, int y) {
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
		setupGuiTransform(x, y, ibakedmodel.isGui3d());
		ibakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(ibakedmodel, ItemCameraTransforms.TransformType.GUI);
		renderItem(stack, ibakedmodel);
		GlStateManager.disableAlpha();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableLighting();
		GlStateManager.popMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture).restoreLastBlurMipmap();
	}
	
	private static void renderModel(IBakedModel model, int color) {
		renderModel(model, color, (ItemStack) null);
	}
	
	public static void renderItem(ItemStack stack, IBakedModel model) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		
		if (model.isBuiltInRenderer()) {
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableRescaleNormal();
			TileEntityItemStackRenderer.instance.renderByItem(stack);
		} else {
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			renderModel(model, -1, stack);
			
			if (stack.hasEffect()) {
				renderEffect(model);
			}
		}
		
		GlStateManager.popMatrix();
	}
	
	private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
	
	private static void renderEffect(IBakedModel model) {
		GlStateManager.depthMask(false);
		GlStateManager.depthFunc(514);
		GlStateManager.disableLighting();
		GlStateManager.blendFunc(768, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(RES_ITEM_GLINT);
		GlStateManager.matrixMode(5890);
		GlStateManager.pushMatrix();
		GlStateManager.scale(8.0F, 8.0F, 8.0F);
		float f = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
		GlStateManager.translate(f, 0.0F, 0.0F);
		GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
		renderModel(model, -8372020);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.scale(8.0F, 8.0F, 8.0F);
		float f1 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
		GlStateManager.translate(-f1, 0.0F, 0.0F);
		GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
		renderModel(model, -8372020);
		GlStateManager.popMatrix();
		GlStateManager.matrixMode(5888);
		GlStateManager.blendFunc(770, 771);
		GlStateManager.enableLighting();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
	}
	
	private static void renderModel(IBakedModel model, int color, ItemStack stack) {
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.setVertexFormat(DefaultVertexFormats.ITEM);
		EnumFacing[] aenumfacing = EnumFacing.values();
		int j = aenumfacing.length;
		
		for (int k = 0; k < j; ++k) {
			EnumFacing enumfacing = aenumfacing[k];
			renderQuads(worldrenderer, model.getFaceQuads(enumfacing), color, stack);
		}
		
		renderQuads(worldrenderer, model.getGeneralQuads(), color, stack);
		tessellator.draw();
	}
	
	@SuppressWarnings("rawtypes")
	private static void renderQuads(WorldRenderer renderer, List quads, int color, ItemStack stack) {
		boolean flag = color == -1 && stack != null;
		BakedQuad bakedquad;
		int j;
		
		for (Iterator iterator = quads.iterator(); iterator.hasNext(); renderQuad(renderer, bakedquad, j)) {
			bakedquad = (BakedQuad) iterator.next();
			j = color;
			
			if (flag && bakedquad.hasTintIndex()) {
				j = stack.getItem().getColorFromItemStack(stack, bakedquad.getTintIndex());
				
				if (EntityRenderer.anaglyphEnable) {
					j = TextureUtil.anaglyphColor(j);
				}
				
				j |= -16777216;
			}
		}
	}
	
	private static void renderQuad(WorldRenderer renderer, BakedQuad quad, int color) {
		renderer.addVertexData(quad.getVertexData());
		if (quad instanceof net.minecraftforge.client.model.IColoredBakedQuad)
			net.minecraftforge.client.ForgeHooksClient.putQuadColor(renderer, quad, color);
		else
			renderer.putColor4(color);
		putQuadNormal(renderer, quad);
	}
	
	private static void putQuadNormal(WorldRenderer renderer, BakedQuad quad) {
		Vec3i vec3i = quad.getFace().getDirectionVec();
		renderer.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());
	}
	
}
