package net.hycrafthd.umod.enumtype;

import net.minecraft.util.IStringSerializable;

public enum EnumTypeBaseStuff implements IStringSerializable {
	
	ALUMINIUM(0, "aluminium", 3.0F, 1),
	COPPER(1, "copper", 3.0F, 1),
	LEAD(2, "lead", 4.0F, 2),
	MANGAN(3, "mangan", 3.0F, 1),
	MERCURY(4, "mercury", 3.0F, 2),
	NICKEL(5, "nickel", 3.0F, 2),
	PLATINUM(6, "platinum", 5.0F, 3),
	SILICIUM(7, "silicium", 3.0F, 2),
	SILVER(8, "silver", 3.0F, 1),
	SULPHUR(9, "sulphur", 2.0F, 1),
	TIN(10, "tin", 3.0F, 1),
	URAN(11, "uran", 4.0F, 3),
	ZINC(12, "zinc", 3.0F, 1);
	
	public int getMetadata() {
		return this.meta;
	}
	
	public int getOreHarvestLevel() {
		return this.harvestlevelore;
	}
	
	public float getOreHardness() {
		return this.hardnessore;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}
	
	public static EnumTypeBaseStuff byMetadata(int meta) {
		if (meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}
		
		return META_LOOKUP[meta];
	}
	
	private final int meta;
	private final String name;
	private final int harvestlevelore;
	private final float hardnessore;
	
	private static final EnumTypeBaseStuff[] META_LOOKUP = new EnumTypeBaseStuff[values().length];
	
	private EnumTypeBaseStuff(int meta, String name, float orehardness, int oreharvestlevel) {
		this.meta = meta;
		this.name = name;
		this.hardnessore = orehardness;
		this.harvestlevelore = oreharvestlevel;
	}
	
	static {
		for (EnumTypeBaseStuff type : values()) {
			META_LOOKUP[type.getMetadata()] = type;
		}
	}
	
}
