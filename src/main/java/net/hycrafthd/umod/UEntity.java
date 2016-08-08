package net.hycrafthd.umod;

import net.hycrafthd.umod.entity.*;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.utils.URegistryUtils;

public class UEntity {
	
	public UEntity() {
		register();
	}
	
	private void register() {
		URegistryUtils.registerEntity(EntityInfectedCow.class, "InfectedCow", 16, 1, true, 0x30311f, 0x648e42, true);
		URegistryUtils.registerEntity(EntityInfectedCreeper.class, "InfectedCreeper", 24, 1, true, 0x45b134, 0x164b0c, true);
		URegistryUtils.registerEntity(EntityNukePrimed.class, "PrimedNuke", 16, 1, true, 0, 0, false);
		// Utils.registerEntity(EntityTommahak.class, "Thommahak", 16, 1, true, 0, 0, false);
		URegistryUtils.registerEntity(EntityInfectedZombie.class, "InfectedZombie", 24, 1, true, 0x44b517, 0x22c970, true);
		URegistryUtils.registerEntity(EntityRailFX.class, "SWELL", 16, 1, true, 0, 0, false);
		URegistryUtils.registerEntity(EntityFX.class, "TileFX", 16, 1, true, 0, 0, false);
		UMod.log.debug("Register Entitys");
	}
}