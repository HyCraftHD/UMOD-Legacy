package net.hycrafthd.umod.block;

import net.minecraft.block.material.Material;

public class BlockOilGlass extends BlockBase{

	public BlockOilGlass() {
		super(Material.glass);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGlass);
	}
	
    public boolean isFullCube()
    {
        return false;
    }
	
}
