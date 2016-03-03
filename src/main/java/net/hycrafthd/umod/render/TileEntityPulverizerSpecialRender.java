package net.hycrafthd.umod.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.gui.GuiRescources;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.utils.LWJGLUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPulverizerSpecialRender extends TileEntitySpecialRenderer{
	
	public int time = 0;
	private EntityItem ent = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D);

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, final double posX, final double posY, final double posZ,
			float p_180535_8_, final int p_180535_9_) {
		
		GlStateManager.pushMatrix();
		final TileEntityPulverizer oven = (TileEntityPulverizer) tileEntity;	
		ItemStack ore = oven.getStackInSlot(3);
		
		final FontRenderer rend = Minecraft.getMinecraft().getRenderManager().getFontRenderer();
	  byte b0 = 0;
	  String st = "No Ore Detected";
		if (ore != null)
		{
		b0 = 8;
		GlStateManager.pushMatrix();
		if(oven.isBurning){
			if(time >= 360){
				time = 0;
			}
		GlStateManager.translate(posX + 0.5, posY + 0.1, posZ + 0.5);
		GlStateManager.rotate(time*2, 0F, 1.0F, 0F);
        LWJGLUtils.drawTexturedCube(new GuiRescources("laser.png"), -0.025,0, -0.025, 0.05, 0.8, 0.05);
        time++;
		}
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		{
			st = ore.getDisplayName();
			GlStateManager.translate(posX + 0.5, posY, posZ + 0.5);
			GlStateManager.scale(1.5, 1.5, 1.5);

					ent.setEntityItemStack(new ItemStack(ore.getItem(),1,ore.getMetadata()));
			        GlStateManager.enableRescaleNormal();
					GL11.glPushMatrix();
					{
						WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
						renderer.setBrightness(15728880);
						GlStateManager.rotate(-(time), 0F, 1F, 0F);
 						Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0, 0);
						
					}
					GL11.glPopMatrix();
		}
		GlStateManager.popMatrix();
		
	   }
          
		GL11.glPushMatrix();
		final String str = st;
		GlStateManager.disableLighting();
		LWJGLUtils.drawSmThInWorld(oven.getPos(), posX, posY + 0.4, posZ, new Runnable() {
			
			@Override
			public void run() {
				String energy = "Energy " + ((IPowerProvieder)oven).getStoredPower() + "/" + ((IPowerProvieder)oven).getMaximalPower();
				String stat = "Progress " + oven.getField(0) + "/100";
				GlStateManager.shadeModel(7425);
		        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
		        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		        GlStateManager.enableDepth();
		        GlStateManager.color(1, 1, 1);
		        int j = checkBiggestString(rend, energy,str,stat) / 2;
		        int j2 = checkBiggestString(rend, energy,str,stat) + 4;
		        int stringmu = 3;
				RGBA rgb2 = new RGBA(Color.GREEN);
				rgb2.setAlpha(50);
				LWJGLUtils.drawFrame((double)(-j - 1), (double)(-1 - 0), (double)(j2 + 1), (double)(8 + 2)*stringmu, rgb2);
				
				RGBA rgb = new RGBA(Color.RED);
				rgb.setAlpha(75);
			    LWJGLUtils.drawFrame((double)(-j - 1.5), (double)(-1 - 0.5), (double)(j2 + 1), (double)(8 + 2)*stringmu, rgb);
			   
			    RGBA rgb3 = new RGBA(Color.WHITE);
			    rgb3.setAlpha(255);
			    LWJGLUtils.drawFrame((double)(-j - 2), (double)(-1 - 1), (double)(j2 + 1), (double)(8 + 2)*stringmu, rgb3);
			    
		        String name = I18n.format(oven.getWorld().getBlockState(oven.getPos()).getBlock().getUnlocalizedName()+ ".name");
		       
			    rend.drawStringWithShadow(name, -rend.getStringWidth(name) / 2, -14, 0xFFFFFF);
			    rend.drawStringWithShadow(str, -rend.getStringWidth(str) / 2, -1, 0xFFFFFF);
			    rend.drawStringWithShadow(energy, -rend.getStringWidth(energy) / 2, 9, 0xFFFFFF);
			    rend.drawStringWithShadow(stat, -rend.getStringWidth(stat) / 2, 19, 0xFFFFFF);
			    
			    GlStateManager.enableDepth();
		        GlStateManager.enableLighting();
			    GlStateManager.shadeModel(7424);
			}
		});
	    GL11.glPopMatrix();
	    GL11.glNormal3f(0, 0, 0);
	    GlStateManager.enableDepth();
        GlStateManager.enableLighting();
	    GlStateManager.shadeModel(7424);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
  }
	
  private int checkBiggestString(FontRenderer re,String... args){
	  int i = 0;
	  for(String str  : args){
		  if(re.getStringWidth(str) > i){
			  i = re.getStringWidth(str);
		  }
	  }
	return i;
  }
	
	
	
}
