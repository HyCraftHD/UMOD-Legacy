package net.hycrafthd.umod;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.umod.api.ProcessHandler;
import net.hycrafthd.umod.event.EventDrawHUD;
import net.hycrafthd.umod.event.EventExecuteRadiation;
import net.hycrafthd.umod.event.EventGettingRadiation;
import net.hycrafthd.umod.event.EventGettingRadiationInv;
import net.hycrafthd.umod.event.EventLoadWorld;
import net.hycrafthd.umod.event.EventPlayerJoin;
import net.hycrafthd.umod.event.EventToolTip;
import net.hycrafthd.umod.network.PacketHandler;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name)
public class UMod {

	public static org.apache.logging.log4j.Logger log;
	public static KeyBinding info = new KeyBinding("Infromation", Keyboard.KEY_I, "UMod");
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		log = event.getModLog();
		new UConfig(event.getSuggestedConfigurationFile());
		new PacketHandler();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
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
		Logger.info("init(e)", "Init Mod.");
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		new UTiles();
		new URecipes();
		new UChestLoot();
		new UAchievement();
		CommonRegistryUtils.registerGuiHandler(new UGuiHandler());
	    ClientRegistry.registerKeyBinding(info);
		UReference.proxy.registerModels();
		UReference.proxy.registerRenderer();
		Logger.info("postinit(e)", "Registered Mod.");
	}

	@EventHandler
	public void serverstarting(FMLServerStartingEvent event) {
		new UCommands(event);
		Logger.info("serverstarting(e)", "Registered Mod Commands.");
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
		event.register();
		Logger.info("registerEvents()", "Registered Mod Events.");
	}

	public void registerGenerators() {
		UGeneration generation = new UGeneration();
		generation.addGenerator(new UOreGeneration(), 0);
		generation.addGenerator(new USchematicGeneration(), 0);
		generation.register();
		Logger.info("registerGenerators()", "Registered Mod Generators.");
	}

}
