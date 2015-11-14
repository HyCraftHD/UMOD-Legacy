package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UReference;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

public class InfectedLog extends BlockLog {

	public InfectedLog() {
		this.setCreativeTab(UReference.tab);
		this.setHarvestLevel("axe", 2);
		this.setHardness(2.0F);
		this.setStepSound(soundTypeWood);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

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

	public int getMetaFromState(IBlockState state) {
		return InfectedLog.SwitchEnumAxis.AXIS_LOOKUP[((InfectedLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal()];

	}

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
}
