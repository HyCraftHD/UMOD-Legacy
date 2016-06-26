package net.hycrafthd.umod.utils;

import net.minecraft.block.BlockAir;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

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
		WorldServer server = MinecraftServer.getServer().worldServers[0];
		return !server.isDaytime();
	}
}
