package net.hycrafthd.umod.block;

import net.minecraft.block.state.IBlockState;

public class BlockHalfCustomSlab extends BlockCustomSlab {
	
	public BlockHalfCustomSlab(IBlockState modelState, String name) {
		super(modelState);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public boolean isDouble() {
		return false;
	}
	
}
