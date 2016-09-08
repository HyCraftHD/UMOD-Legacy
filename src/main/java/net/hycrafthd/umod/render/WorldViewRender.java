package net.hycrafthd.umod.render;

import java.awt.Color;
import java.util.*;

import net.hycrafthd.corelib.util.*;
import net.hycrafthd.umod.api.*;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;

public class WorldViewRender{

	public static final WorldViewRender INSTANCE = new WorldViewRender();
	
	private WorldViewRender() {}
	
	public void render(TileEntity tileEntity, double posX, double posY, double posZ) {
		if(!(tileEntity instanceof IWorldView))return;
		final FontRenderer rend = Minecraft.getMinecraft().fontRendererObj;
		final IWorldView oven = (IWorldView) tileEntity;
		if (oven instanceof IWorldSpecialView) {
			IWorldSpecialView spview = (IWorldSpecialView) oven;
			spview.specialRender();
			if(!spview.renderMain())return;
		}
		final ArrayList<String> st = new ArrayList<String>();
		st.add(I18n.format(oven.getWorld().getBlockState(oven.getPos()).getBlock().getUnlocalizedName() + ".name"));
		if(oven instanceof IPowerProvieder && oven.showPower()){
		st.add("Energy " + ((IPowerProvieder) oven).getStoredPower() + "/" + ((IPowerProvieder) oven).getMaximalPower());
	    }
		String[] strs = oven.textToAdd();
		if(strs != null){
		for(int i = 0;i < strs.length;i++){
			st.add(strs[i]);
		}
		}
		GlStateManager.pushMatrix();
		GlStateManager.shadeModel(7425);
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.enableDepth();
		GlStateManager.enableNormalize();
		GlStateManager.color(1, 1, 1);
		final int stringSi = checkBiggestString(rend,st.toArray(new String[st.size()]));
		final int j =  stringSi / 2;
		final int j2 = stringSi + 4;
		final int stringmu = st.size() - 1;
		LWJGLUtils.drawSmThInWorld(oven.getPos(), posX, posY + ((double)stringmu/10), posZ, new Runnable() {
			
			@Override
			public void run() {
				RGBA rgb2 = new RGBA(Color.GREEN);
				rgb2.setAlpha(50);
				LWJGLUtils.drawFrame((double) (-j - 1), (double) (-1 - 0), (double) (j2 + 1), (double) (10) * stringmu, rgb2);
				
				RGBA rgb = new RGBA(Color.RED);
				rgb.setAlpha(75);
				LWJGLUtils.drawFrame((double) (-j - 1.5), (double) (-1 - 0.5), (double) (j2 + 1), (double) (10) * stringmu, rgb);
				
				RGBA rgb3 = new RGBA(Color.WHITE);
				rgb3.setAlpha(255);
				LWJGLUtils.drawFrame((double) (-j - 2), (double) (-1 - 1), (double) (j2 + 1), (double) (10) * stringmu, rgb3);
				
				Iterator<String> itar = st.iterator();
				int y = -11;
				while(itar.hasNext()){
					String s = itar.next();
					rend.drawStringWithShadow(s, -rend.getStringWidth(s) / 2, y, 0xFFFFFF);
					y += 10;
				}
				
				GlStateManager.enableDepth();
				GlStateManager.shadeModel(7424);
			}
		});
		GlStateManager.popMatrix();
	}

	private int checkBiggestString(FontRenderer re, String... args) {
		int i = 0;
		for (String str : args) {
			if (re.getStringWidth(str) > i) {
				i = re.getStringWidth(str);
			}
		}
		return i;
	}
}
