package net.hycrafthd.umod.block;

import net.minecraft.block.material.Material;

public class InfectedLog extends BlockBase{

	public InfectedLog() {
		super(Material.wood);
		this.setHarvestLevel("axe", 2);
        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
	}
}
