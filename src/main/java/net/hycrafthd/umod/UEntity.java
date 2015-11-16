package net.hycrafthd.umod;

import net.hycrafthd.umod.entity.EntityInfectedCow;
import net.hycrafthd.umod.utils.CommonRegistryUtils;

public class UEntity {

	public UEntity() {
		register();
	}

	private void register() {
		CommonRegistryUtils.registerEntity(EntityInfectedCow.class, "InfectedCow", 16, 1, true, 0x30311f, 0x648e42, true);
	}

}