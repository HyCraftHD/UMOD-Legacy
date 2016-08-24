package net.hycrafthd.umod.event;

import net.hycrafthd.umod.api.energy.TunnelHolder;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventOnTick {

	@SubscribeEvent
	public void ticking(TickEvent ev){
		if(!ev.type.equals(TickEvent.Type.SERVER))return;
		for(int i = 0; i < TunnelHolder.getMax();i++){
			TunnelHolder.getUETunnel(i).onTick();
		}
	}
	
}
