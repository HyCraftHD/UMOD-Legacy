package net.hycrafthd.umod.block;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class BlockInfectedPlank extends BlockBase {

	public BlockInfectedPlank() {
		super(Material.wood);
		Blocks.fire.setFireInfo(this, 5, 20);
	}

}
