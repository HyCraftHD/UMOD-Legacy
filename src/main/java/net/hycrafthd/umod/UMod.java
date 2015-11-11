package net.hycrafthd.umod;

import net.hycrafthd.umod.event.RayEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = UReference.modid, version = UReference.version, name = UReference.name)
public class UMod {

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new UItems();
		new UBlocks();
		UReference.eventManager = new UEvent();
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		UReference.eventManager.register();
		new URecipes();
		UReference.proxy.registerModels();
	}
}
