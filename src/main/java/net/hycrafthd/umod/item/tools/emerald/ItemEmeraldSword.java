package net.hycrafthd.umod.item.tools.emerald;

import net.hycrafthd.umod.UReference;
import net.minecraft.item.ItemSword;

public class ItemEmeraldSword extends ItemSword{

	public ItemEmeraldSword(ToolMaterial material) {
		super(material);
		setCreativeTab(UReference.tab);
	}

}
