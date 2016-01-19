package net.hycrafthd.umod.render;

import java.awt.Color;

import net.hycrafthd.umod.Logger;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.block.BlockCable;
import net.hycrafthd.umod.item.tools.energy.ItemEnergyGlasses;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.utils.LWJGLUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityCabelSpecialRender extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity p_180535_1_, double posX, double posY, double posZ,
			float p_180535_8_, int p_180535_9_) {
        EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
		if(pl.inventory.armorInventory[0] != null && pl.inventory.armorInventory[0].getItem() instanceof ItemEnergyGlasses && p_180535_1_ instanceof IPowerProvieder){
			IPowerProvieder pro = (IPowerProvieder) p_180535_1_;
			LWJGLUtils.drawStringInWorld(p_180535_1_.getPos(),posX,posY,posZ, p_180535_9_, pro.getStoredPower() + "/" + pro.getMaximalPower(), new RGBA(Color.red), new RGBA(Color.red), new RGBA(Color.gray), Color.BLACK.getRGB());
		}
		
		Block blo = p_180535_1_.getWorld().getBlockState(p_180535_1_.getPos()).getBlock();
		if(blo instanceof BlockCable){
        BlockCable cab = (BlockCable) blo;
		String name = cab.getSpirte();
		TileEntityCable pip = (TileEntityCable) p_180535_1_;
		World w = Minecraft.getMinecraft().getIntegratedServer().worldServers[0];
		BlockPos pos = pip.getPos(); 
		if (pip != null && !((TileEntityCable) p_180535_1_).hasConduit()) {
			boolean csouth = pip.canConnect(w, pos.south());
			boolean cnorth = pip.canConnect(w, pos.north());
			boolean cdown = pip.canConnect(w, pos.down());
			boolean cup = pip.canConnect(w, pos.up());
			boolean ceast = pip.canConnect(w, pos.east());
			boolean cwest = pip.canConnect(w, pos.west());
			if (cup) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY + 0.25, posZ, 0.2, 0.5, 0.2);
			}
			if (cdown) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY - 0.25, posZ, 0.2, 0.5, 0.2);
			}
			if (cwest) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX - 0.25, posY, posZ, 0.5, 0.2, 0.2);
			}
			if (ceast) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX + 0.25, posY, posZ, 0.5, 0.2, 0.2);
			}
			if (cnorth) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY, posZ - 0.25, 0.2, 0.2, 0.5);
			}
			if (csouth) {
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY, posZ + 0.25, 0.2, 0.2, 0.5);
			}
			
			if(!cdown && !ceast && !cnorth && !csouth && !cup && !cwest){
				LWJGLUtils.drawBlock(new ResourceLocation(UReference.modid,"textures/blocks/block/" +  name + ".png"), posX, posY, posZ, 0.2, 0.2, 0.2);
			}
		}else if(pip != null){
			String str = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(new ItemStack(((TileEntityCable) p_180535_1_).getConduit())).getTexture().getIconName();
			LWJGLUtils.drawBlock(new ResourceLocation(str.split(":")[0],"textures/" + str.split(":")[1] + ".png"), posX, posY, posZ, 1, 1, 1);
		}
		
		}
	}
	
	

    
}
