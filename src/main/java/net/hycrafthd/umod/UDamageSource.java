package net.hycrafthd.umod;

import net.hycrafthd.umod.damagesource.*;
import net.minecraft.util.DamageSource;

public class UDamageSource {
	
	public static DamageSource radiationDamageSource;
	public static DamageSource electroshock;
	public static DamageSource nuclearExplosion;
	
	public UDamageSource() {
		init();
	}
	
	private void init() {
		radiationDamageSource = new DamageSourceRadiation();
		electroshock = new DamageSourceElectroShock();
		nuclearExplosion = new DamageSourceNuclearExplosion();
		UMod.log.debug("Init DamageSources");
	}
	
}
