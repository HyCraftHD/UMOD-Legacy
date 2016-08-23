package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

public class BlockCustomStair extends BlockStairs {
	
	BlockCustomStair(IBlockState modelState, String name) {
		super(modelState);
		this.setUnlocalizedName(name);
		this.setCreativeTab(UReference.blocks);
	}
	
}
