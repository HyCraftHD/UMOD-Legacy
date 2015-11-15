package net.hycrafthd.umod;

import net.hycrafthd.umod.api.DamageSourceElectroShock;
import net.hycrafthd.umod.potion.DamageSourceRadiation;
import net.minecraft.util.DamageSource;

public class UDamageSource {

	public static DamageSource radiationDamageSource;
	public static DamageSource electroshock;
	
	public UDamageSource() {
		init();
	}

	private void init() {
		radiationDamageSource = new DamageSourceRadiation();
		electroshock = new DamageSourceElectroShock();
	}
	
}
