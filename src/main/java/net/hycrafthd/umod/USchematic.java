package net.hycrafthd.umod;

import net.hycrafthd.umod.schematic.Schematic;

public class USchematic {

	public static Schematic ruinSchematic;
	
	public USchematic() {
		init();
	}

	private void init() {
		ruinSchematic = new Schematic("Ruin.schematic");
	}
	
}
