package net.hycrafthd.umod;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class UEvent {
	
	private EventBus bus;
	private ArrayList<Object> events;
	
	public UEvent() {
		init();
	}
	
	private void init() {
		this.bus = MinecraftForge.EVENT_BUS;
		this.events = new ArrayList<Object>();
	}
	
	public boolean addEvent(Object event) {
		if (events.contains(event))
			return false;
		events.add(event);
		UMod.log.debug("Add Mod Event to List");
		return true;
	}
	
	public boolean removeEvent(Object event) {
		if (!events.contains(event))
			return false;
		events.remove(event);
		UMod.log.debug("Remove Mod Event from List");
		return true;
	}
	
	public void register() {
		for (Object event : events) {
			bus.register(event);
			FMLCommonHandler.instance().bus().register(event);
		}
		UMod.log.debug("All Events in the List registered");
	}
	
	public void setEvents(ArrayList<Object> events) {
		this.events = events;
	}
	
	public ArrayList<Object> getEvents() {
		return events;
	}
	
}
