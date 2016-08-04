package net.hycrafthd.umod.block;

import net.minecraft.block.*;
import net.minecraft.init.Blocks;

public class BlockStoneStairs extends BlockStairs {
	
	public BlockStoneStairs(BlockStone.EnumType enumtype) {
		super(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, enumtype));
		this.setUnlocalizedName("stonestair" + enumtype.getName());
	}
	
}
