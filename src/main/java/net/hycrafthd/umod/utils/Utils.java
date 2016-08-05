package net.hycrafthd.umod.utils;

import net.hycrafthd.corelib.registry.*;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.item.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Utils {
	
	public static double getDistanceAtoB(double x1, double z1, double x2, double z2) {
		double dx = x1 - x2;
		double dz = z1 - z2;
		return Math.sqrt((dx * dx + dz * dz));
	}
	
	public static void registerItem(Item item) {
		ItemRegistry.register(item, item.getUnlocalizedName().substring(5));
		OreDictionaryRegistry.register(item);
	}
	
	public static void registerBlock(Block block) {
		registerBlock(block, ItemBlockBase.class);
	}
	
	public static void registerBlock(Block block, Class<? extends ItemBlockBase> bl) {
		BlockRegistry.register(block, bl, block.getUnlocalizedName().substring(5));
		OreDictionaryRegistry.register(block);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void registerEntity(Class entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int solidColor, int spotColor, boolean hasSpawnEgg) {
		int id = EntityRegistry.findGlobalUniqueEntityId();
		
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
		EntityRegistry.registerModEntity(entityClass, entityName, id, UReference.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
		
		if (hasSpawnEgg) {
			EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, solidColor, spotColor));
		}
	}
}
