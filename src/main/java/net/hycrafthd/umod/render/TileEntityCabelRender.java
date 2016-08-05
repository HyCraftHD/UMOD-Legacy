package net.hycrafthd.umod.render;

import net.hycrafthd.corelib.util.LWJGLUtils;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockCable;
import net.hycrafthd.umod.item.tools.energy.ItemEnergyGlasses;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class TileEntityCabelRender extends TileRender {
	
	@Override
	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ) {
		EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		if (pl.inventory.armorInventory[3] != null && pl.inventory.armorInventory[3].getItem() instanceof ItemEnergyGlasses && p_180535_1_ instanceof IPowerProvieder) {
			// TODO Create Overlay only IO Pipes
		}
		Block blo = p_180535_1_.getWorld().getBlockState(p_180535_1_.getPos()).getBlock();
		if(blo != null && p_180535_1_ instanceof TileEntityCable && blo instanceof BlockCable){
        BlockCable cab = (BlockCable) blo;
		String name = cab.getSpirte();
		TileEntityCable pip = (TileEntityCable) p_180535_1_;
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
			boolean lr = false, ud = false, fb = false;
			RenderLocation loc = new RenderLocation(name + ".png");
			if (cup) {
				LWJGLUtils.drawBlock(loc, posX, posY + 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cdown) {
				LWJGLUtils.drawBlock(loc, posX, posY - 0.25, posZ, 0.2, 0.5, 0.2);
				ud = true;
			}
			if (cwest) {
				LWJGLUtils.drawBlock(loc, posX - 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
			}
			if (ceast) {
				LWJGLUtils.drawBlock(loc, posX + 0.25, posY, posZ, 0.5, 0.2, 0.2);
				fb = true;
			}
			if (cnorth) {
				LWJGLUtils.drawBlock(loc, posX, posY, posZ - 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			if (csouth) {
				LWJGLUtils.drawBlock(loc, posX, posY, posZ + 0.25, 0.2, 0.2, 0.5);
				lr = true;
			}
			
			if((!cdown && !ceast && !cnorth && !csouth && !cup && !cwest) || (lr && fb) || (lr && ud) || (ud && fb) || (ud && fb && lr)){
				LWJGLUtils.drawBlock(loc, posX, posY, posZ, 0.205, 0.205, 0.205);
			}			
		}
		GlStateManager.enableCull();
	}
	
}
