package net.hycrafthd.umod.render;

import java.awt.Color;

import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockCable;
import net.hycrafthd.umod.item.tools.energy.ItemEnergyGlasses;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityCabelSpecialRender 
{
	
	public static void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ,
			float p_180535_8_, int p_180535_9_) {
        EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		if(pl.inventory.armorInventory[3] != null && pl.inventory.armorInventory[3].getItem() instanceof ItemEnergyGlasses && p_180535_1_ instanceof IPowerProvieder){
			IPowerProvieder pro = (IPowerProvieder) p_180535_1_;
			GlStateManager.enableAlpha();
			LWJGLUtils.drawStringInWorld(p_180535_1_.getPos(),posX,posY,posZ, p_180535_9_, pro.getStoredPower() + "/" + pro.getMaximalPower(), new RGBA(Color.red).setAlpha(100), new RGBA(Color.black).setAlpha(100), new RGBA(Color.gray).setAlpha(0), Color.WHITE.getRGB());
		}
		

		Block blo = p_180535_1_.getWorld().getBlockState(p_180535_1_.getPos()).getBlock();
		if(blo != null && blo instanceof BlockCable){
        BlockCable cab = (BlockCable) blo;
		String name = cab.getSpirte();
		TileEntityCable pip = (TileEntityCable) p_180535_1_;
		World w = p_180535_1_.getWorld();
		if(!w.isRemote)return;
		BlockPos pos = pip.getPos(); 
		if (pip != null && (!((TileEntityCable) p_180535_1_).hasConduit() || (pl.getCurrentEquippedItem() != null && Block.getBlockFromItem(pl.getCurrentEquippedItem().getItem()) != null && Block.getBlockFromItem(pl.getCurrentEquippedItem().getItem()) instanceof BlockCable))) {
			 if (Minecraft.isAmbientOcclusionEnabled())
	            {
	                GlStateManager.shadeModel(7425);
	            }
	          else
	          {
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
		}else if(pip != null){
			if(pl.getCurrentEquippedItem() == null || !(Block.getBlockFromItem(pl.getCurrentEquippedItem().getItem()) instanceof BlockCable)){
		    GlStateManager.enableLighting();
			LWJGLUtils.renderBlockConduit(pip.getConduit(), posX, posY, posZ);
			GlStateManager.disableLighting();
			}
		}
		GlStateManager.enableCull();
		
	}


	}
    
}
