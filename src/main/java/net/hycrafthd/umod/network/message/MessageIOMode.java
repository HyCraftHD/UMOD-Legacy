package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.api.IIOMode;
import net.hycrafthd.umod.utils.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageIOMode implements IMessage, IMessageHandler<MessageIOMode, IMessage> {
	
	public BlockPos pos;
	public EnumFacing ei, eo;
	
	public MessageIOMode() {
		
	}
	
	public MessageIOMode(BlockPos pos, EnumFacing fac, EnumFacing face) {
		ei = fac;
		eo = face;
		this.pos = pos;
	}
	
	@Override
	public IMessage onMessage(MessageIOMode message, MessageContext ctx) {
		World w = ctx.getServerHandler().playerEntity.worldObj;
		TileEntity ent = w.getTileEntity(message.pos);
		if (ent instanceof IIOMode) {
			IIOMode est = (IIOMode) ent;
			est.setEnumInput(message.ei);
			est.setEnumOutput(message.eo);
		}
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = NetworkUtil.getPosFromBuffer(buf);
		ei = DirectionUtils.getFacingFromShort(buf.readShort());
		eo = DirectionUtils.getFacingFromShort(buf.readShort());
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		NetworkUtil.addPosToBuffer(buf, pos);
		buf.writeShort(DirectionUtils.getShortFromFacing(ei));
		buf.writeShort(DirectionUtils.getShortFromFacing(eo));
	}
	
}
