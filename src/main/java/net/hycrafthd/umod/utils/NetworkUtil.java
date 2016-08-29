package net.hycrafthd.umod.utils;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.BlockPos;

public class NetworkUtil {

	public static void addPosToBuffer(ByteBuf buf,BlockPos ps){
		buf.writeByte(ps.getX());
		buf.writeByte(ps.getY());
		buf.writeByte(ps.getZ());
	}
	
	public static BlockPos getPosFromBuffer(ByteBuf buf){
		return new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}
}
