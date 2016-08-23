package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.hycrafthd.umod.tileentity.TileEntityMagicCrafter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class BlockMagicCrafter extends BlockContainer{

	public BlockMagicCrafter() {
		super(Material.piston);
		this.setCreativeTab(UReference.magic);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote){
			
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if(tileentity instanceof TileEntityMagicCrafter){
				playerIn.openGui(UReference.instance, EnumTypeGui.MAGIC_CRAFTER.getID(), worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
			
		}
		
		return false;
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityMagicCrafter();
	}
	
	@Override
	public int getRenderType() {
		return 3; //default = 3
	}
	
}
