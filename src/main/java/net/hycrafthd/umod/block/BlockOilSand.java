package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockOilSand extends BlockFalling implements IInfectedBlock {
	
	public BlockOilSand() {
		super(Material.sand);
		this.setCreativeTab(UReference.blocks);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.8F);
		this.setStepSound(soundTypeSand);
	}
	
	@Override
	public Block getNormalBlock() {
		return Blocks.sand;
	}
	
}
