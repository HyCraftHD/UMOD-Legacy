package net.hycrafthd.umod.item.tools.emerald;

import net.hycrafthd.umod.UReference;
import net.minecraft.item.ItemPickaxe;

public class ItemEmeraldPickaxe extends ItemPickaxe{

	public ItemEmeraldPickaxe(ToolMaterial material) {
		super(material);
		setCreativeTab(UReference.tab);
	}

}
