package net.hycrafthd.umod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;

public class UReference {
	
	public static final String modid = "umod";
	public static final String name = "UMOD";
	public static final String version = "alpha0.3";
	public static final String mcversion = "1.8";
	
	public static final String resource = UReference.modid + ":";
	
	@Instance(UReference.modid)
	public static UMod instance = new UMod();
	
	@SidedProxy(serverSide = "net.hycrafthd.umod.CommonProxy", clientSide = "net.hycrafthd.umod.ClientProxy", modId = UReference.modid)
	public static CommonProxy proxy = new CommonProxy();
	
	public static CreativeTabs maschines = new UTab();
	public static CreativeTabs blocks = new CreativeTabs("blocks") {
		
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(UBlocks.blocks);
		}
	};
	public static CreativeTabs magic = new CreativeTabs("magic") {
		
		@Override
		public Item getTabIconItem() {
			return UItems.magic_diamond;
		}
	};
	public static CreativeTabs infected = new CreativeTabs("infected") {
		
		@Override
		public Item getTabIconItem() {
			return UItems.infectedmilk;
		}
	};
	public static CreativeTabs rail = new CreativeTabs("rail") {
		
		@Override
		public Item getTabIconItem() {
			return UItems.railplacer;
		}
	};
	public static CreativeTabs things = new CreativeTabs("things") {
		
		@Override
		public Item getTabIconItem() {
			return UItems.backpack;
		}
	};
}
