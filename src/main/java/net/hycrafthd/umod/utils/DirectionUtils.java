package net.hycrafthd.umod.utils;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class DirectionUtils {

	public static EnumFacing getDirectory(BlockPos pos1, BlockPos pos2) {
		if (pos1.getY() > pos2.getY()) {
			return EnumFacing.DOWN;
		} else if (pos1.getY() < pos2.getY()) {
			return EnumFacing.UP;
		}
		if (pos1.getX() > pos2.getX()) {
			return EnumFacing.EAST;
		} else if (pos1.getX() < pos2.getX()) {
			return EnumFacing.WEST;
		}
		if (pos1.getZ() > pos2.getZ()) {
			return EnumFacing.SOUTH;
		} else if (pos1.getZ() < pos2.getZ()) {
			return EnumFacing.NORTH;
		}
		return EnumFacing.EAST;
	}
	
	public static BlockPos getPosfromFacing(EnumFacing face,BlockPos p){
		switch(face){
		case DOWN:
			return p.down(); 
		case EAST:
			return p.east();
		case NORTH:
			return p.north();
		case SOUTH:
			return p.south();
		case UP:
			return p.up();
		case WEST:
			return p.west();
		default:
			break;
		}
		return null;
	}

}
