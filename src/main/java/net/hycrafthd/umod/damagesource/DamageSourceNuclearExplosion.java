package net.hycrafthd.umod.damagesource;

import net.minecraft.util.DamageSource;

public class DamageSourceNuclearExplosion extends DamageSource {
	
	public DamageSourceNuclearExplosion() {
		super("nuclearexplosion");
	}
	
	@Override
	public DamageSource setExplosion() {
		return super.setExplosion();
	}
	
	@Override
	public DamageSource setDamageBypassesArmor() {
		return super.setDamageBypassesArmor();
	}
	
	@Override
	public DamageSource setDamageIsAbsolute() {
		return super.setDamageIsAbsolute();
	}
	
}
