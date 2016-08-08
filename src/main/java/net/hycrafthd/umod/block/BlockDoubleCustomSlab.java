package net.hycrafthd.umod.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class BlockDoubleCustomSlab extends BlockCustomSlab {
	
	BlockDoubleCustomSlab(IBlockState modelState, Block slab, String name) {
		super(modelState, slab);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public boolean isDouble() {
		return true;
	}
	
}
