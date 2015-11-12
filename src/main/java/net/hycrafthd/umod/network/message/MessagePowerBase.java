package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePowerBase implements IMessage,IMessageHandler<MessagePowerBase, IMessage>{

	@Override
	public void fromBytes(ByteBuf buf) {
	
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
	}

	@Override
	public IMessage onMessage(MessagePowerBase message, MessageContext ctx) {
		return null;
	}

	
	
}
