package net.hycrafthd.umod.block;

import java.util.Random;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.energy.IEnergyMessage;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockPulverizer extends BlockBaseMachine implements IEnergyMessage {

	public BlockPulverizer() {
		super();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityPulverizer();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote){
		playerIn.openGui(UReference.modid, EnumTypeGui.PULVERISER.getID(), world, pos.getX(), pos.getY(), pos.getZ());
        }
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
	public String getMessage(int n) {
		return "Needs 10UE/t";
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		worldIn.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX(), pos.getX(), pos.getX(), 0, 0, 0, 120);
	}

}
