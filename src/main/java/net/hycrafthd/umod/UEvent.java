package net.hycrafthd.umod;

import net.hycrafthd.umod.event.RayEvent;
import net.minecraftforge.common.MinecraftForge;

public class UEvent {

	public UEvent() {
		register();
	}

	private void register() {
		MinecraftForge.EVENT_BUS.register(new RayEvent());
	}

}
