package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.IEnergyMessage;
import net.hycrafthd.umod.tileentity.TileEntitySolarPanel;
import net.hycrafthd.umod.utils.EnergyUtils;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSolarPanel extends BlockBase implements ITileEntityProvider, IEnergyMessage {

	public BlockSolarPanel() {
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySolarPanel();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		playerIn.openGui(UReference.modid, 1, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess w, BlockPos pos, IBlockState state, EnumFacing side) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public boolean isFullBlock() {
		return true;
	}

	@Override
	public boolean isSolidFullCube() {
		return true;
	}

	@Override
	public boolean isFullCube() {
		return true;
	}

	@Override
	public String getMessage() {
		return "Products " + EnergyUtils.inUE(3) + "UE/t";
	}
}
