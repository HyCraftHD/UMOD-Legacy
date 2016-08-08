package net.hycrafthd.umod.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

public class BlockSlabCreator {
	
	private BlockHalfCustomSlab slab;
	private BlockDoubleCustomSlab doubleslab;
	
	public BlockSlabCreator(IBlockState modelState, String name) {
		slab = new BlockHalfCustomSlab(modelState, "slab_" + name);
		doubleslab = new BlockDoubleCustomSlab(modelState, slab, "doubleslab_" + name);
	}
	
	public BlockHalfCustomSlab getSlab() {
		return slab;
	}
	
	public BlockDoubleCustomSlab getDoubleslab() {
		return doubleslab;
	}
	
	public Block[] createList() {
		return new Block[] { slab, doubleslab };
	}
	
}
