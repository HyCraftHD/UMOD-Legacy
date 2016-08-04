package net.hycrafthd.umod.enumtype;

import net.minecraft.util.IStringSerializable;

public enum EnumTypeTransformer implements IStringSerializable {
	
	LOW_TRANSFORMER(0, "low"),
	MEDIUM_TRANSFORMER(1, "medium"),
	HIGH_TRANSFORMER(2, "high"),
	EXTREM_TRANSFORMER(3, "extrem"),
	ULTRA_TRANSFORMER(4, "ultra");
	
	public int getMetadata() {
		return this.meta;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public static EnumTypeTransformer byMetadata(int meta) {
		if (meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}
		
		return META_LOOKUP[meta];
	}
	
	private final int meta;
	private final String name;
	
	private static final EnumTypeTransformer[] META_LOOKUP = new EnumTypeTransformer[values().length];
	
	private EnumTypeTransformer(int meta, String name) {
		
		this.meta = meta;
		this.name = name;
		
	}
	
	static {
		for (EnumTypeTransformer type : values()) {
			META_LOOKUP[type.getMetadata()] = type;
		}
	}
}
