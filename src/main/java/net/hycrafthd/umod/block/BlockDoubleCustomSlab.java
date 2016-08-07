package net.hycrafthd.umod.block;

import net.minecraft.block.state.IBlockState;

public class BlockDoubleCustomSlab extends BlockCustomSlab {
	
	public BlockDoubleCustomSlab(IBlockState modelState, String name) {
		super(modelState);
		this.setUnlocalizedName(name);
	}
	
	@Override
	public boolean isDouble() {
		return true;
	}
	
}
