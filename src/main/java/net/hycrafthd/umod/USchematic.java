package net.hycrafthd.umod;

import net.hycrafthd.umod.schematic.Schematic;
import net.hycrafthd.umod.schematic.SchematicInfestedRuin1;


public class USchematic {

	public static Schematic ruinSchematic;
	
	public USchematic() {
		init();
	}

	private void init() {
		ruinSchematic = new SchematicInfestedRuin1();
	}
	
}
