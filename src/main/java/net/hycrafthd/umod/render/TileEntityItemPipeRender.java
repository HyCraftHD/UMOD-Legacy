package net.hycrafthd.umod.render;

import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.ISpiritProvider;
import net.hycrafthd.umod.block.BlockItemPipe;
import net.hycrafthd.umod.tileentity.TileEntityItemPipe;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class TileEntityItemPipeRender extends TileRender{
	
	@Override
	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ) {
        EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		if(ConduitRender.render(p_180535_1_, pl, posX, posY, posZ))return;
		Block blo = p_180535_1_.getWorld().getBlockState(p_180535_1_.getPos()).getBlock();
		if(blo != null && blo instanceof BlockItemPipe){
		BlockItemPipe cab = (BlockItemPipe) blo;
		String name = ((ISpiritProvider) cab).getSpirte();
		TileEntityItemPipe pip = (TileEntityItemPipe) p_180535_1_;
		World w = p_180535_1_.getWorld();
		if(!w.isRemote)return;
		BlockPos pos = pip.getPos(); 
			if (Minecraft.isAmbientOcclusionEnabled()){
	             GlStateManager.shadeModel(7425);
	        }else{
	             GlStateManager.shadeModel(7424);
	        }
			GlStateManager.disableCull();
		    GlStateManager.blendFunc(770, 771);
	        GlStateManager.enableBlend();
			boolean csouth = pip.canConnect(w, pos.south());
			boolean cnorth = pip.canConnect(w, pos.north());
			boolean cdown = pip.canConnect(w, pos.down());
			boolean cup = pip.canConnect(w, pos.up());
			boolean ceast = pip.canConnect(w, pos.east());
			boolean cwest = pip.canConnect(w, pos.west());
			boolean lr = false, ud = false,fb = false; 
			if (cup) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY + 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cdown) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY - 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cwest) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX - 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
            }
			if (ceast) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX + 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
			}
			if (cnorth) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY, posZ - 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			if (csouth) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY, posZ + 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			
			if((!cdown && !ceast && !cnorth && !csouth && !cup && !cwest) || (lr && fb) || (lr && ud) || (ud && fb) || (ud && fb && lr)){
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY, posZ, 0.205, 0.205, 0.205);
			}
		GlStateManager.enableCull();
		
	}

	}
}
