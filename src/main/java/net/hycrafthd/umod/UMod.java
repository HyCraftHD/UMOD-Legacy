package net.hycrafthd.umod;

import net.hycrafthd.umod.event.EventExecuteRadiation;
import net.hycrafthd.umod.event.EventNearByInfectedBlock;
import net.hycrafthd.umod.event.EventRenderOverlaybyhavingRadiation;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name)
public class UMod {

	public static Logger log;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		log = event.getModLog();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new UPotion();
		new UItems();
		new UBlocks();
		new UArmor();
		new UDamageSource();
		new UEntity();
		registerEvents();
		registerGenerators();
		new UBiome();
		this.registerGenerators();
		this.registerEvents();
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		new UTiles();
		new URecipes();
		new UChestLoot();
		CommonRegistryUtils.registerGuiHandler(new UGuiHandler());
		UReference.proxy.registerModels();

	}

	public void registerEvents() {
		UEvent event = new UEvent();
		event.addEvent(new EventNearByInfectedBlock());
		event.addEvent(new EventExecuteRadiation());
		event.addEvent(new EventRenderOverlaybyhavingRadiation());
		event.register();

	}

	public void registerGenerators() {
		UGeneration generation = new UGeneration();
		generation.addGenerator(new UOreGeneration(), 0);
		generation.addGenerator(new USchematicGeneration(), 0);
		generation.register();
	}

}
