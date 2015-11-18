package net.hycrafthd.umod;

import net.hycrafthd.umod.command.CommandSchematic;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class UCommands {

	public UCommands(FMLServerStartingEvent e) {
		register(e);
	}

	private void register(FMLServerStartingEvent e) {
		e.registerServerCommand(new CommandSchematic());
	}

}
