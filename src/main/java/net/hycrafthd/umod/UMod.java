package net.hycrafthd.umod;

import org.apache.logging.log4j.Logger;

import net.hycrafthd.umod.event.EventExecuteRadiation;
import net.hycrafthd.umod.event.EventNearByInfectedBlock;
import net.hycrafthd.umod.event.EventRenderOverlaybyhavingRadiation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name)
public class UMod {

	public static Logger log;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		log = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new USchematic();
		new UPotion();
		new UItems();
		new UBlocks();
		new UArmor();
		new UBiome();
		new UDamageSource();
		registerEvents();
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		new UTiles();
		new URecipes();
		GameRegistry.registerWorldGenerator(new UWorldGeneration(), 0);
		UReference.eventManager.register();
		FMLCommonHandler.instance().bus().register(new EventRenderOverlaybyhavingRadiation());
		NetworkRegistry.INSTANCE.registerGuiHandler(UReference.modid, new UGuiHandler());
		UReference.proxy.registerModels();
	}

	public void registerEvents() {
		UReference.eventManager.addEvent(new EventNearByInfectedBlock());
		UReference.eventManager.addEvent(new EventExecuteRadiation());
		UReference.eventManager.addEvent(new EventRenderOverlaybyhavingRadiation());
	}

}
