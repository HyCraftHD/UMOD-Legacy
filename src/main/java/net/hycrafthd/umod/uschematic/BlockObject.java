package net.hycrafthd.umod.uschematic;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

public class BlockObject {

	private BlockPos position;
	private IBlockState state;
	private NBTTagCompound tiledata;

	public BlockObject(BlockPos position, IBlockState state, NBTTagCompound tiledata) {
		this.position = position;
		this.state = state;
		this.tiledata = tiledata;
	}

	public NBTTagCompound getTileEntityData() {
		return this.tiledata;
	}

	public BlockPos getPosition() {
		return position;
	}

	public IBlockState getState() {
		return state;
	}

	public BlockPos getPositionWithOffset(int x, int y, int z) {
		return new BlockPos(x + position.getX(), y + position.getY(), z + position.getZ());
	}

	@Override
	public String toString() {
		return this.state.getBlock().getLocalizedName() + " : " + position.toString();
	}

}