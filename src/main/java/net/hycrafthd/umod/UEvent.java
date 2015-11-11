package net.hycrafthd.umod;

import java.util.ArrayList;

import akka.actor.FSM.Event;
import net.hycrafthd.umod.event.RayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;

public class UEvent {
	
	private EventBus bus;
	private ArrayList<Object> events = new ArrayList<Object>();
	
	public UEvent() {
		init();
		set();
	}

	private void init(){
		bus = MinecraftForge.EVENT_BUS;
	}
	
	private void set() {
		addEvent(new RayEvent());
	}
	
	public void register() {
		for(Object event : events) bus.register(event);
	}
	
	public void addEvent(Object clazz) {
		if(events.contains(clazz)) return;
		events.add(clazz);
	}
	
	public boolean removeEvent(Object object){
		if(!events.contains(object)) return false;
		events.remove(object);
		return true;
	}
	
	public ArrayList<Object> getEvents() {
		return events;
	}
	
	public void setEvents(ArrayList<Object> events) {
		this.events = events;
	}
	
}
