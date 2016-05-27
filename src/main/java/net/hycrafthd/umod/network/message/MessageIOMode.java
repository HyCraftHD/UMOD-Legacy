package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.api.IIOMode;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageIOMode implements IMessage,IMessageHandler<MessageIOMode, IMessage> {

	public BlockPos pos;
	public EnumFacing ei,eo;
	
	public MessageIOMode() {
		
	}
	
	public MessageIOMode(BlockPos pos,EnumFacing fac,EnumFacing face) {
		ei = fac;
		eo = face;
		this.pos = pos;
	}
	
	@Override
	public IMessage onMessage(MessageIOMode message, MessageContext ctx) {
		World w = ctx.getServerHandler().playerEntity.worldObj;
		TileEntity ent = w.getTileEntity(message.pos);
		if(ent instanceof IIOMode){
			IIOMode est = (IIOMode) ent;
			est.setEnumInput(message.ei);
			est.setEnumOutput(message.eo);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		ei = DirectionUtils.getFacingFromShort(buf.readShort());
		eo = DirectionUtils.getFacingFromShort(buf.readShort());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeShort(DirectionUtils.getShortFromFacing(ei));
		buf.writeShort(DirectionUtils.getShortFromFacing(eo));
	}

}
