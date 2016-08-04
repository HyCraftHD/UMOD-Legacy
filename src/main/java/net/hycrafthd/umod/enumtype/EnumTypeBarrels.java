package net.hycrafthd.umod.enumtype;

import net.minecraft.util.IStringSerializable;

public enum EnumTypeBarrels implements IStringSerializable {
	
	BARRELSSMALL(0, "small", 5, 9),
	BARRELSMEDIUM(1, "medium", 7, 9),
	BARRELSBIG(2, "big", 9, 9);
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getCount() {
		return x * y;
	}
	
	public static EnumTypeBarrels byID(int id) {
		if (id < 0 || id >= all.length) {
			id = 0;
		}
		return all[id];
	}
	
	private int id;
	private String name;
	private int x;
	private int y;
	
	private static final EnumTypeBarrels[] all = new EnumTypeBarrels[values().length];
	
	private EnumTypeBarrels(int id, String name, int x, int y) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	static {
		for (EnumTypeBarrels type : values()) {
			all[type.getID()] = type;
		}
	}
	
}
