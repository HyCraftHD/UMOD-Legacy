package net.hycrafthd.umod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;

public class UReference {
	
	public static final String modid = "umod";
	public static final String name = "UMOD";
	public static final String version = "alpha0.1";
	
	public static final String resource = UReference.modid + ":";
	
	@Instance(UReference.modid)
	public static UMod instance = new UMod();

	@SidedProxy(serverSide = "net.hycrafthd.umod.CommonProxy", clientSide = "net.hycrafthd.umod.ClientProxy", modId = UReference.modid)
	public static CommonProxy proxy = new CommonProxy();
	
	public static CreativeTabs tab = new UTab();

	public static UEvent eventManager = new UEvent();
	
}
