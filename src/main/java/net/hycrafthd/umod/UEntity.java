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
import net.hycrafthd.umod.utils.CommonRegistryUtils;

public class UEntity {
	
	public UEntity() {
		init();
		register();
	}
	
	private void init() {
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void addtoRender(){
		RenderingRegistry.registerEntityRenderingHandler(EntityInfectedCow.class, new RenderInfectedCow());
	}
	


	private void register() {
		CommonRegistryUtils.registerEntity(EntityInfectedCow.class, "InfectedCow", 16, 1, true, 0x30311f, 0x648e42, true);
	}

}