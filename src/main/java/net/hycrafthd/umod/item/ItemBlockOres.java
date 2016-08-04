package net.hycrafthd.umod.item;

import net.hycrafthd.umod.block.*;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockOres extends ItemBlockSubBase {
	
	public ItemBlockOres(Block block) {
		super(block);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(stack.getMetadata());
		if (block instanceof BlockOres) {
			return "tile.ore" + type.getName();
		} else if (block instanceof BlockNetherOres) {
			return "tile.netherore" + type.getName();
		} else {
			return "";
		}
	}
	
}
