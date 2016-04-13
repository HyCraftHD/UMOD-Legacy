 package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.gui.GuiModIngame;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventDrawHUD {
    
    @SubscribeEvent
    public void onItemChanged(RenderGameOverlayEvent ev){
    	EntityPlayer pl = Minecraft.getMinecraft().thePlayer;
    	if(pl != null && pl.worldObj.isRemote){
    		if(pl.getCurrentEquippedItem() != null && pl.getCurrentEquippedItem().isItemEqual(new ItemStack(UItems.energydisplay))){
    		      GuiModIngame.renderGameOverlay(ev.partialTicks,ev.resolution);
    		}
    	}
    }
    

    
}