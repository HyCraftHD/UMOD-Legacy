package net.hycrafthd.umod.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOilGlass extends BlockBase{

	public BlockOilGlass() {
		
		super(Material.glass);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGlass);
		
	}
	
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 0;
    }
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean isFullCube() {
		return true;
	}

	@Override
	public boolean isFullBlock() {
		return true;
	}
	
	@Override
	public IBlockState getStateForEntityRender(IBlockState state) {
		return this.getDefaultState();
	}
	
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
    
    protected boolean canSilkHarvest()
    {
        return true;
    }
    
}
