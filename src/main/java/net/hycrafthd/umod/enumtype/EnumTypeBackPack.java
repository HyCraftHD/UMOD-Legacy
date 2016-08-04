package net.hycrafthd.umod.enumtype;

public enum EnumTypeBackPack {
	
	BACKPACKSMALL(0, "small", 3, 5),
	BACKPACKMEDIUM(1, "medium", 5, 7),
	BACKPACKBIG(2, "big", 7, 9);
	
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
	
	public static EnumTypeBackPack byMetadata(int id) {
		if (id < 0 || id >= all.length) {
			id = 0;
		}
		return all[id];
	}
	
	private int id;
	private String name;
	private int x;
	private int y;
	
	private static final EnumTypeBackPack[] all = new EnumTypeBackPack[values().length];
	
	private EnumTypeBackPack(int id, String name, int x, int y) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	static {
		for (EnumTypeBackPack type : values()) {
			all[type.getID()] = type;
		}
	}
	
}
