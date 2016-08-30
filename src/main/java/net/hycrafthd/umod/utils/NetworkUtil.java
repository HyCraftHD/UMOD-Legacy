package net.hycrafthd.umod.utils;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.BlockPos;

public class NetworkUtil {

	public static void addPosToBuffer(ByteBuf buf,BlockPos ps){
		buf.writeInt(ps.getX());
		buf.writeInt(ps.getY());
		buf.writeInt(ps.getZ());
	}
	
	public static BlockPos getPosFromBuffer(ByteBuf buf){
		return new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}
}
