package net.hycrafthd.umod.ext;

import net.minecraftforge.fml.common.event.*;

public interface IUmodExtension {

	public void preinit(FMLPreInitializationEvent evt);
	
	public void init(FMLInitializationEvent evt);
	
	public void postinit(FMLPostInitializationEvent evt);
	
	public void serverstarting(FMLServerStartingEvent evt);
	
	public void clientRegistery();
	
}
