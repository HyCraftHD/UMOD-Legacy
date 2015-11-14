package net.hycrafthd.umod;

import net.hycrafthd.umod.potion.RadiationDamageSource;
import net.minecraft.util.DamageSource;

public class UDamageSource {

	public static DamageSource radiationDamageSource;
	
	public UDamageSource() {
		init();
	}

	private void init() {
		radiationDamageSource = new RadiationDamageSource();
	}
	
}
