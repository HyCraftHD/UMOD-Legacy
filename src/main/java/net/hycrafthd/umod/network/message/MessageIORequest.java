package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.api.IIOMode;
import net.hycrafthd.umod.utils.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageIORequest implements IMessage, IMessageHandler<MessageIORequest, MessageIOCallback> {
	
	public BlockPos pos;
	public EnumFacing prov;
	
	public MessageIORequest() {
	}
	
	public MessageIORequest(BlockPos pos, EnumFacing prov) {
		this.pos = pos;
		this.prov = prov;
	}
	
	@Override
	public MessageIOCallback onMessage(MessageIORequest message, MessageContext ctx) {
		World w = ctx.getServerHandler().playerEntity.worldObj;
		TileEntity ent = w.getTileEntity(message.pos);
		if(ent != null && ent instanceof IIOMode){
		if (((IIOMode) ent).hasSomefacing(message.prov) > -1) {
			return new MessageIOCallback(message.prov,((IIOMode) ent).hasSomefacing(message.prov));
		}else{
			return new MessageIOCallback(message.prov,Byte.MAX_VALUE);
		}
		}
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = NetworkUtil.getPosFromBuffer(buf);
		prov = DirectionUtils.getFacingFromShort(buf.readShort());
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		NetworkUtil.addPosToBuffer(buf, pos);
		buf.writeShort(DirectionUtils.getShortFromFacing(this.prov));
	}
	
}
