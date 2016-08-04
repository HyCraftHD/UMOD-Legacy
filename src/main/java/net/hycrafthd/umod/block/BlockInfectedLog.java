package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.*;
import net.minecraft.init.Blocks;

public class BlockInfectedLog extends BlockLog implements IInfectedBlock {

	public BlockInfectedLog() {
		this.setCreativeTab(UReference.tab);
		this.setHarvestLevel("axe", 1);
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypeWood);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		Blocks.fire.setFireInfo(this, 5, 5);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState iblockstate = this.getDefaultState();

		switch (meta) {
		case 0:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
			break;
		case 1:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
			break;
		case 2:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
			break;
		default:
			iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return iblockstate;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return BlockInfectedLog.SwitchEnumAxis.AXIS_LOOKUP[((BlockInfectedLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal()];

	}

	@Override
	public BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { LOG_AXIS });
	}

	static final class SwitchEnumAxis {
		static final int[] AXIS_LOOKUP = new int[BlockLog.EnumAxis.values().length];

		static {
			try {
				AXIS_LOOKUP[BlockLog.EnumAxis.X.ordinal()] = 1;
			} catch (NoSuchFieldError var3) {
				;
			}

			try {
				AXIS_LOOKUP[BlockLog.EnumAxis.Z.ordinal()] = 2;
			} catch (NoSuchFieldError var2) {
				;
			}

			try {
				AXIS_LOOKUP[BlockLog.EnumAxis.NONE.ordinal()] = 3;
			} catch (NoSuchFieldError var1) {
				;
			}
		}
	}

	@Override
	public Block getNormalBlock() {
		return Blocks.log;
	}
}
