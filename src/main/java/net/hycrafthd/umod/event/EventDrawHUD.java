 package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.gui.GuiModIngame;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
    	EntityPlayer pl = ev.player;
    	if(pl != null && pl.worldObj.isRemote){
    		if(pl.getCurrentEquippedItem() != null && pl.getCurrentEquippedItem().isItemEqual(new ItemStack(UItems.energydisplay)) && !isGuiModOverwritted()){
    			onIngameGuiOverwrite();
    		}else if(isGuiModOverwritted()){
    			onIngameGuiRewrite();
    		}
    	}
    }
    
}