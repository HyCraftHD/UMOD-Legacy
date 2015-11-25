package net.hycrafthd.umod;

import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.entity.EntityNukePrimed;
import net.hycrafthd.umod.entity.render.RenderInfectedCow;
import net.hycrafthd.umod.entity.render.RenderInfectedCreeper;
import net.hycrafthd.umod.entity.render.RenderNukePrimed;
import net.hycrafthd.umod.utils.CommonRegistryUtils;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UEntity {
	
	public UEntity() {
		register();
	}
	
	private void register() {
		CommonRegistryUtils.registerEntity(EntityInfectedCow.class, "InfectedCow", 16, 1, true, 0x30311f, 0x648e42, true);
		CommonRegistryUtils.registerEntity(EntityInfectedCreeper.class, "InfectedCreeper", 24, 1, true, 0x45b134, 0x164b0c, true);
		CommonRegistryUtils.registerEntity(EntityNukePrimed.class, "NukePrimed", 16, 1, true, 0x000000, 0x000000, true);
	}
}