package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IConduitBlock;
import net.minecraft.block.material.Material;

public class BlockConduit extends BlockBase implements IConduitBlock {
	
	public BlockConduit() {
		super(Material.iron);
		this.setCreativeTab(UReference.maschines);
	}
	
}
