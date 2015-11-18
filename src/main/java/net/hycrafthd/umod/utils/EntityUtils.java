package net.hycrafthd.umod.utils;

import net.hycrafthd.umod.interfaces.IInfectedEntity;
import net.minecraft.entity.EntityLivingBase;

public class EntityUtils {

	public static boolean isInfectedEntity(EntityLivingBase base) {
		return (base instanceof IInfectedEntity);
	}

}
