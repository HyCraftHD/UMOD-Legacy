package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.gui.GuiBase;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageIOCallback implements IMessage, IMessageHandler<MessageIOCallback, IMessage> {
	
	public EnumFacing face;
	public int item;
	
	public MessageIOCallback() {
	}
	
	public MessageIOCallback(EnumFacing fc, int item) {
		this.face = fc;
		this.item = item;
	}
	
	@Override
	public IMessage onMessage(MessageIOCallback message, MessageContext ctx) {
		GuiScreen sc = Minecraft.getMinecraft().currentScreen;
		if (sc != null && sc instanceof GuiBase) {
			GuiBase bs = (GuiBase) sc;
			bs.checkAndAdd(message.face, message.item);
		}
		return null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.face = DirectionUtils.getFacingFromShort(buf.readShort());
		this.item = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(DirectionUtils.getShortFromFacing(face));
		buf.writeInt(item);
	}
	
}
