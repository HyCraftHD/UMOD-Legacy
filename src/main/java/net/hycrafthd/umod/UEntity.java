package net.hycrafthd.umod;

import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.hycrafthd.umod.entity.render.RenderInfectedCow;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class UEntity {
	
	public UEntity() {
		init();
		register();
	}
	
	private void init() {
		
	}

	private void register() {
		createEntity(EntityInfectedCow.class, "InfectedCow", EnumCreatureType.MONSTER, 10, 0, 20, new BiomeGenBase[] {UBiome.infestedBiomBase}, 0x30311f, 0x648e42, true);
	}
	
	private void createEntity(Class entityClass, String entityName, EnumCreatureType type, int probability, int minSpawn, int maxSpawn, BiomeGenBase[] biomes, int solidColor, int spotColor, boolean hasSpawnEgg){
		int id = EntityRegistry.findGlobalUniqueEntityId();
		
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
		EntityRegistry.registerModEntity(entityClass, entityName, id, UReference.instance, 64, 1, true);
		EntityRegistry.addSpawn(entityName, probability, minSpawn, maxSpawn, type, biomes);
		if(hasSpawnEgg){
			EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, solidColor, spotColor));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void addtoRender(){
		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedCow.class, new RenderInfectedCow());
	}
	
}