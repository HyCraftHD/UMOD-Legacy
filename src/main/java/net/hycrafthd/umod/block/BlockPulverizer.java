package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.UUtils;
import net.hycrafthd.umod.api.IEnergyMessage;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockPulverizer extends BlockBaseMachine implements IEnergyMessage{

	public BlockPulverizer() {
		super();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPulverizer();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		playerIn.openGui(UReference.modid, 0, world, pos.getX(), pos.getY(), pos.getZ());
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

	@Override
	public String getMessage() {
		return "Needs " + UUtils.inUE(10) +"UE/t";
	}

}
