package net.hycrafthd.umod.item.tools.emerald;

import net.hycrafthd.umod.UReference;
import net.minecraft.item.ItemSpade;

public class ItemEmeraldSpade extends ItemSpade{

	public ItemEmeraldSpade(ToolMaterial material) {
		super(material);
		setCreativeTab(UReference.tab);
	}

}
