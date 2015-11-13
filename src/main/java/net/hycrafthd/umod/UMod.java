package net.hycrafthd.umod;

import net.hycrafthd.umod.event.RayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name)
public class UMod {

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new UItems();
		new UBlocks();
		new UArmor();
		new UBiom();
		new UEvent();
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		new UTiles();
		new URecipes();
		NetworkRegistry.INSTANCE.registerGuiHandler(UReference.modid, new UGuiHandler());
		UReference.proxy.registerModels();
	}
}
