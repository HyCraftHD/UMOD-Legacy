package net.hycrafthd.umod.block;

import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockOilSand extends BlockBase implements IInfectedBlock{

	public BlockOilSand() {
		super(Material.sand);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.8F);
		this.setStepSound(soundTypeSand);	
		
	}

	@Override
	public Block getNormalBlock() {
		return Blocks.sand;
	}

}
