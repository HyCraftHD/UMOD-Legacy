 package net.hycrafthd.umod.event;

import net.hycrafthd.umod.gui.GuiModIngame;
import net.hycrafthd.umod.render.TileEntityCabelSpecialRender;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.utils.TLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EventDrawHUD {


    public static void onIngameGuiOverwrite(){
        Minecraft.getMinecraft().ingameGUI = new GuiModIngame(Minecraft.getMinecraft());
    }
    
    public static void onIngameGuiRewrite(){
        Minecraft.getMinecraft().ingameGUI = new net.minecraftforge.client.GuiIngameForge(Minecraft.getMinecraft());
    }

    public static boolean isGuiModOverwritted(){
    	return Minecraft.getMinecraft().ingameGUI instanceof GuiModIngame;
    }
    
    @SubscribeEvent
    public void onItemChanged(PlayerTickEvent ev){
    	/*EntityPlayer pl = ev.player;
    	if(pl != null && pl.worldObj.isRemote){
    		if(pl.getCurrentEquippedItem() != null && pl.getCurrentEquippedItem().isItemEqual(new ItemStack(UItems.energydisplay)) && !isGuiModOverwritted()){
    			onIngameGuiOverwrite();
    		}else if(isGuiModOverwritted()){
    			onIngameGuiRewrite();
    		}
    	}*/
    }
    

    
}