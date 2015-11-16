package net.hycrafthd.umod;

import net.hycrafthd.umod.world.GenInfectedTree;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;

public class UReference {

	public static final String modid = "umod";
	public static final String name = "UMOD";
	public static final String version = "alpha0.1";

	public static final String resource = UReference.modid + ":";
	public static final float PIPE_MIN_POS = 0.4F;
	public static final float PIPE_MAX_POS = 0.6F;

	@Instance(UReference.modid)
	public static UMod instance = new UMod();

	@SidedProxy(serverSide = "net.hycrafthd.umod.CommonProxy", clientSide = "net.hycrafthd.umod.ClientProxy", modId = UReference.modid)
	public static CommonProxy proxy = new CommonProxy();

	public static CreativeTabs tab = new UTab();

	public static UEvent eventManager = new UEvent();

}
