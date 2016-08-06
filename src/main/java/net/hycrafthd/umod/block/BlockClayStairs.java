package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;

public class BlockClayStairs extends BlockStairs {
	
	public BlockClayStairs() {
		super(Blocks.hardened_clay.getDefaultState());
		this.setUnlocalizedName("claystairhardened");
		this.setCreativeTab(UReference.tab);
	}
	
	public BlockClayStairs(EnumDyeColor enumtype) {
		super(Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, enumtype));
		this.setUnlocalizedName("claystair" + enumtype.getName());
		this.setCreativeTab(UReference.tab);
	}
}
