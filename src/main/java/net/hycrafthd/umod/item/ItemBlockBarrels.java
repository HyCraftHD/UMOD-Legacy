package net.hycrafthd.umod.item;

import net.hycrafthd.umod.enumtype.EnumTypeBarrels;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockBarrels extends ItemBlockSubBase {
	
	public ItemBlockBarrels(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBarrels type = EnumTypeBarrels.byID(stack.getMetadata());
		return "tile.barrels" + type.getName();
	}
	
}
