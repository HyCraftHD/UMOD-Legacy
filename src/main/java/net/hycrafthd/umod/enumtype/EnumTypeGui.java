package net.hycrafthd.umod.enumtype;

public enum EnumTypeGui {
	
	PULVERISER(0),
	SOLARPANEL(1),
	BATTERIE(2),
	CHARGESTATION(3),
	CRAFTFURNANCE(4),
	BACKPACK(5),
	BARRELS(6),
	PAINTER(7);
	
	public int getID() {
		return id;
	}
	
	public static EnumTypeGui byID(int id) {
		if (id < 0 || id >= all.length) {
			id = 0;
		}
		return all[id];
	}
	
	private int id;
	
	private static final EnumTypeGui[] all = new EnumTypeGui[values().length];
	
	private EnumTypeGui(int id) {
		this.id = id;
	}
	
	static {
		for (EnumTypeGui type : values()) {
			all[type.getID()] = type;
		}
	}
	
}
