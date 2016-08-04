package net.hycrafthd.umod.item;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockBlocks extends ItemBlockSubBase {
	
	public ItemBlockBlocks(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(stack.getMetadata());
		return "tile.block" + type.getName();
	}
	
}
