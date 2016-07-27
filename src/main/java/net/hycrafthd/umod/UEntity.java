package net.hycrafthd.umod;

import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.entity.EntityInfectedCreeper;
import net.hycrafthd.umod.entity.EntityInfectedZombie;
import net.hycrafthd.umod.entity.EntityItemPipeFX;
import net.hycrafthd.umod.entity.EntityNukePrimed;
import net.hycrafthd.umod.entity.EntityPipeFX;
import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.hycrafthd.umod.utils.CommonRegistryUtils;

public class UEntity {
	
	public UEntity() {
		register();
	}
	
	private void register() {
		CommonRegistryUtils.registerEntity(EntityInfectedCow.class, "InfectedCow", 16, 1, true, 0x30311f, 0x648e42, true);
		CommonRegistryUtils.registerEntity(EntityInfectedCreeper.class, "InfectedCreeper", 24, 1, true, 0x45b134, 0x164b0c, true);
		CommonRegistryUtils.registerEntity(EntityNukePrimed.class, "PrimedNuke", 16, 1, true, 0x000000, 0x000000, false);
		//CommonRegistryUtils.registerEntity(EntityTommahak.class, "Thommahak", 16, 1, true, 0x000000, 0x000000, false);
		CommonRegistryUtils.registerEntity(EntityInfectedZombie.class, "InfectedZombie", 24, 1, true, 0x44b517, 0x22c970, true);
		CommonRegistryUtils.registerEntity(EntityPipeFX.class, "PIPES", 16, 1, false, 0x000000, 0x000000, false);
		CommonRegistryUtils.registerEntity(EntityRailFX.class, "SWELL", 16, 1, true, 0x000000, 0x000000, false);
		CommonRegistryUtils.registerEntity(EntityItemPipeFX.class, "ItemPipe", 16, 1, false, 0x000000, 0x000000, false);
		Logger.debug(UEntity.class, "register()", "Register Entitys");
	}
}