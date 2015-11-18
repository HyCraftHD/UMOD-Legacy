package net.hycrafthd.umod.render;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.client.Minecraft;
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
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX + 0.3, posY + 0.52, posZ + 0.5);
			GlStateManager.scale(1.5, 1.5, 1.5);

			TileEntityPulverizer oven = (TileEntityPulverizer) tileEntity;	
				ItemStack ore = oven.getStackInSlot(3);
				if (ore != null)
				{
					ent.setEntityItemStack(new ItemStack(ore.getItem(),1,ore.getMetadata()));
					GL11.glPushMatrix();
					{
						WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
						renderer.setBrightness(15728880);

						Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(ent, 0.15D, -0.3D, 0.0D, 0, 0);
					}
					GL11.glPopMatrix();
				}
		}
		GlStateManager.popMatrix();
		
	}
	
	

}
