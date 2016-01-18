package net.hycrafthd.umod.render;

import java.awt.Color;

import net.hycrafthd.umod.UArmor;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockCable;
import net.hycrafthd.umod.utils.LWJGLUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

public class TileEntityCabelSpecialRender extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ,
			float p_180535_8_, int p_180535_9_) {
        EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		if(pl.getCurrentArmor(0) != null && pl.getCurrentArmor(0).isItemEqual(new ItemStack(UArmor.energyglasses)) && p_180535_1_ instanceof IPowerProvieder){
			IPowerProvieder pro = (IPowerProvieder) p_180535_1_;
			LWJGLUtils.drawStringInWorld(p_180535_1_.getPos(),posX,posY,posZ, p_180535_9_, pro.getStoredPower() + "/" + pro.getMaximalPower(), new RGBA(Color.red), new RGBA(Color.red), new RGBA(Color.gray), Color.BLACK.getRGB());
		}
		
		BlockCable blo = (BlockCable) p_180535_1_.getWorld().getBlockState(p_180535_1_.getPos()).getBlock();
		
		String name = blo.getSpirte();
		LWJGLUtils.drawTexturedCube(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY, posZ, 1, 1, 1);
	}

}
