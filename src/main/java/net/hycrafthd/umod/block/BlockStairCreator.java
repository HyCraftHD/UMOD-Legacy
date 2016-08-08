package net.hycrafthd.umod.block;

import net.minecraft.block.state.IBlockState;

public class BlockStairCreator {
	
	private BlockCustomStair stair;
	
	public BlockStairCreator(IBlockState modelState, String name) {
		stair = new BlockCustomStair(modelState, "stair_" + name);
	}
	
	public BlockCustomStair getStair() {
		return stair;
	}
	
}
