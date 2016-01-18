package net.hycrafthd.umod.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.utils.LWJGLUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public class TileEntityPulverizerSpecialRender extends TileEntitySpecialRenderer{
	
	private EntityItem ent = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ,
			float p_180535_8_, int p_180535_9_) {
		TileEntityPulverizer oven = (TileEntityPulverizer) tileEntity;	
		ItemStack ore = oven.getStackInSlot(3);
		if (ore != null)
		{
		RGBA rgba = new RGBA(Color.ORANGE);
		RGBA rgba2 = new RGBA(Color.RED);
		rgba.setAlpha(125);
		rgba2.setAlpha(125);
		LWJGLUtils.drawStringInWorld(tileEntity.getPos(),posX, posY, posZ, p_180535_9_, ore.getDisplayName(),rgba,rgba2,new RGBA(Color.gray),0x3ADF00);
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
	
	
	
}
