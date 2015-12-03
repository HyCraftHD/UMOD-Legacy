package net.hycrafthd.umod.network;

import net.hycrafthd.umod.network.message.MessagePowerBase;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	
	public static final SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper("umod");
	
	public PacketHandler() {
           INSTANCE.registerMessage(MessagePowerBase.class, MessagePowerBase.class, 0, Side.SERVER);
	}

}
