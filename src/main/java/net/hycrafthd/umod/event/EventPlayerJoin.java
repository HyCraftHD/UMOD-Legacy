package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UAchievement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventPlayerJoin {
	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		player.addStat(UAchievement.firstjoin, 1);
	}
	
}
