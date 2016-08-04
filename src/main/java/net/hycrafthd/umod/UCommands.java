package net.hycrafthd.umod;

import net.hycrafthd.umod.command.CommandConduit;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class UCommands {
	
	public UCommands(FMLServerStartingEvent e) {
		register(e);
	}
	
	private void register(FMLServerStartingEvent e) {
		// e.registerServerCommand(new CommandUSchematic());
		e.registerServerCommand(new CommandConduit());
	}
	
}
