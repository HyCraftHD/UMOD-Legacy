package net.hycrafthd.umod.network;

import net.hycrafthd.umod.network.message.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("umod");
	
	public PacketHandler() {
		// INSTANCE.registerMessage(MessagePowerBase.class, MessagePowerBase.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageIOMode.class, MessageIOMode.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MessageIORequest.class, MessageIORequest.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageCallback.class, MessageCallback.class, 2, Side.SERVER);
	}
	
}
