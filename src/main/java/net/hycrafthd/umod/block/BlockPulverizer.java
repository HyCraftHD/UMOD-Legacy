package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPulverizer extends BlockBaseMachine{

	public BlockPulverizer() {
		super();
		this.setUnlocalizedName("pulver");
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPulverizer();
	}
	
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
    		EnumFacing side, float hitX, float hitY, float hitZ) {
    	playerIn.openGui(UReference.modid, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
    	return true;
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
    
}
