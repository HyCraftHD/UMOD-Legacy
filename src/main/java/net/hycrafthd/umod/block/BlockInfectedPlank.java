package net.hycrafthd.umod.block;

import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockInfectedPlank extends BlockBase implements IInfectedBlock {
	
	public BlockInfectedPlank() {
		super(Material.wood);
		this.setHarvestLevel("axe", 0);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeWood);
		Blocks.fire.setFireInfo(this, 5, 20);
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.planks;
	}
	
}
