package net.hycrafthd.umod;

import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.render.RenderInfectedCow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class UEntity {

	public UEntity() {
		init();
		register();	}
	
	private void init() {
		
	}

	private void register() {
		createEntity(EntityInfectedCow.class, new RenderInfectedCow(), "infectedcow", EnumCreatureType.MONSTER, 10, 0, 20, new BiomeGenBase[] {UBiome.infestedBiomBase}, 0x30311f, 0x648e42, true);
	}
	
	private void createEntity(Class entityClass, Render render, String entityName, EnumCreatureType type, int probability, int minSpawn, int maxSpawn, BiomeGenBase[] biomes, int solidColor, int spotColor, boolean hasSpawnEgg){
		int id = EntityRegistry.findGlobalUniqueEntityId();
		
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, id);
		EntityRegistry.registerModEntity(entityClass, entityName, id, UReference.instance, 64, 1, true);
		EntityRegistry.addSpawn(entityName, probability, minSpawn, maxSpawn, type, biomes);
		RenderingRegistry.registerEntityRenderingHandler(entityClass, render);
		
		if(hasSpawnEgg){
			EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, solidColor, spotColor));
		}
	}
	
}