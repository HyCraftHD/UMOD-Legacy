package net.hycrafthd.umod;

import net.hycrafthd.umod.schematic.Schematic;

public class USchematic {

	public static Schematic testSchematic;
	
	public USchematic() {
		init();
	}

	private void init() {
		testSchematic = new Schematic("Test2.schematic");
	}
	
}
