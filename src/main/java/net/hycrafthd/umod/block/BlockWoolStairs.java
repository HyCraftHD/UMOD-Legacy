package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;

public class BlockWoolStairs extends BlockStairs {
	
	public BlockWoolStairs(EnumDyeColor enumtype) {
		super(Blocks.wool.getDefaultState().withProperty(BlockColored.COLOR, enumtype));
		this.setUnlocalizedName("woolstair" + enumtype.getName());
		this.setCreativeTab(UReference.tab);
	}
	
}
