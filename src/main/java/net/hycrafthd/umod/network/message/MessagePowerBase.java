package net.hycrafthd.umod.network.message;

import io.netty.buffer.ByteBuf;
import net.hycrafthd.umod.tileentity.TileEntityChargeStation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public class MessagePowerBase implements IMessage, IMessageHandler<MessagePowerBase, IMessage> {
	
	public int x, y, z;
	public boolean mode;
	
	public MessagePowerBase() {
	}
	
	public MessagePowerBase(int x, int y, int z, boolean mode) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.mode = mode;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		mode = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeBoolean(mode);
	}
	
	@Override
	public IMessage onMessage(MessagePowerBase message, MessageContext ctx) {
		World w = ctx.getServerHandler().playerEntity.worldObj;
		TileEntity ent = w.getTileEntity(new BlockPos(x, y, z));
		if (ent instanceof TileEntityChargeStation) {
			((TileEntityChargeStation) ent).setMode(mode);
		}
		return null;
	}
	
}
