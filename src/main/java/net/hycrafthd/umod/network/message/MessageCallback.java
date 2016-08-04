package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.gui.GuiBase;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessageCallback implements IMessage, IMessageHandler<MessageCallback, IMessage> {
	
	private EnumFacing face;
	private int mode;
	
	public MessageCallback() {
	}
	
	public MessageCallback(EnumFacing fc, int mode) {
		this.face = fc;
		this.mode = mode;
	}
	
	@Override
	public IMessage onMessage(MessageCallback message, MessageContext ctx) {
		GuiScreen sc = Minecraft.getMinecraft().currentScreen;
		if (sc != null && sc instanceof GuiBase) {
			GuiBase bs = (GuiBase) sc;
			bs.onCallBack(message.getFace(), message.getMode());
		}
		return this;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.face = DirectionUtils.getFacingFromShort(buf.readShort());
		this.mode = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(DirectionUtils.getShortFromFacing(face));
		buf.writeInt(mode);
	}
	
	public EnumFacing getFace() {
		return face;
	}
	
	public void setFace(EnumFacing face) {
		this.face = face;
	}
	
	public int getMode() {
		return mode;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
}
