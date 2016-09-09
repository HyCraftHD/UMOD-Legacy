package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.api.ISliderPro;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageSliderGet implements IMessage,IMessageHandler<MessageSliderGet, IMessage>{

	public int id,val;
	
	public MessageSliderGet() {
	}
	
	public MessageSliderGet(int id,int val) {
           this.id = id;
           this.val = val;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	     this.id = buf.readInt();
	     this.val = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(val);
	}

	@Override
	public IMessage onMessage(MessageSliderGet message, MessageContext ctx) {
		if(ctx.side.isClient()) {
			GuiScreen sc = Minecraft.getMinecraft().currentScreen;
			if (sc instanceof ISliderPro) {
				ISliderPro sli = (ISliderPro) sc;
				sli.set(message.id, message.val);
			}
		}
		return null;
	}

}
