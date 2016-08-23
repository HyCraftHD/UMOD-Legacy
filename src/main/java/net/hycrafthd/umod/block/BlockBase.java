package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block {
	
	public BlockBase(Material mat) {
		super(mat);
		this.setCreativeTab(UReference.blocks);
	}
	
}
