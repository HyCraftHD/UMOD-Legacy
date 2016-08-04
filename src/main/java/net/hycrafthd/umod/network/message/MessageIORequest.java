package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.api.IIOMode;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageIORequest implements IMessage ,IMessageHandler<MessageIORequest, IMessage>{

	public BlockPos pos;
	public int mode;
	
    public MessageIORequest() {}
	
	public MessageIORequest(BlockPos pos,int mode){
		this.pos = pos;
		this.mode = mode;
	}
	
	@Override
	public IMessage onMessage(MessageIORequest message, MessageContext ctx) {
		World w = ctx.getServerHandler().playerEntity.worldObj;
		TileEntity ent = w.getTileEntity(message.pos);
		if(ent instanceof IIOMode){
			((IIOMode) ent).request(message.mode);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = new BlockPos(buf.readInt(),buf.readInt(),buf.readInt());
		mode = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeInt(mode);
	}

}
