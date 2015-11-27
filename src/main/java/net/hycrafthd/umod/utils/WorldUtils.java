package net.hycrafthd.umod.utils;

import net.minecraft.block.BlockAir;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldUtils {
	
	public static boolean isBlockover(World w,BlockPos p){
		for(int i = 1;i < 250;i++){
			if(!(w.getBlockState(p.up(i)).getBlock() instanceof BlockAir)){
				return false;
			}
		}
		return true;
	}

	public static boolean isNight(World w){
		return w.getTotalWorldTime() % 20L == 0L;
	}
}
