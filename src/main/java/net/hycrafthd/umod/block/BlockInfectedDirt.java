package net.hycrafthd.umod.block;

import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockInfectedDirt extends BlockBase implements IInfectedBlock {
	
	public BlockInfectedDirt() {
		super(Material.ground);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.dirt;
	}
}
