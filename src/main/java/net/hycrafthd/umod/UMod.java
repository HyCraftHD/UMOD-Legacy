package net.hycrafthd.umod;

import net.hycrafthd.umod.api.ProcessHandler;
import net.hycrafthd.umod.event.*;
import net.hycrafthd.umod.ext.ExtensionList;
import net.hycrafthd.umod.network.PacketHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name, dependencies="required-after:corelib")
public class UMod {
	
	public static org.apache.logging.log4j.Logger log;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		ExtensionList.onStart(event);
		new UConfig(event.getSuggestedConfigurationFile());
		new PacketHandler();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ExtensionList.onInit(event);
		new UPotion();
		new UItems();
		new UBlocks();
		new UArmor();
		new UDamageSource();
		new UBiome();
		new UEntity();
		new UTools();
		this.registerGenerators();
		this.registerEvents();
		UMod.log.info("Init Mod.");
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		ExtensionList.onPostInit(event);
		new UTiles();
		new URecipes();
		new UChestLoot();
		new UAchievement();
		net.hycrafthd.corelib.registry.NetworkRegistry.registerGuiHandler(new UGuiHandler());
		UReference.proxy.registerModels();
		UReference.proxy.registerRenderer();
		UMod.log.info("Registered Mod.");
	}
	
	@EventHandler
	public void serverstarting(FMLServerStartingEvent event) {
		ExtensionList.onServer(event);
		new UCommands(event);
		UMod.log.info("Registered Mod Commands.");
	}
	
	public void registerEvents() {
		UEvent event = new UEvent();
		event.addEvent(new EventGettingRadiation());
		event.addEvent(new EventExecuteRadiation());
		event.addEvent(new ProcessHandler());
		event.addEvent(new EventGettingRadiationInv());
		event.addEvent(new EventLoadWorld());
		event.addEvent(new EventDrawHUD());
		event.addEvent(new EventPlayerJoin());
		event.addEvent(new EventToolTip());
		event.addEvent(new EventOnTick());
		event.addEvent(new EventChestInventory());
		event.register();
		UMod.log.info("Registered Mod Events.");
	}
	
	public void registerGenerators() {
		UGeneration generation = new UGeneration();
		generation.addGenerator(new UOreGeneration(), 0);
		generation.register();
		UMod.log.info("Registered Mod Generators.");
	}
	
}
