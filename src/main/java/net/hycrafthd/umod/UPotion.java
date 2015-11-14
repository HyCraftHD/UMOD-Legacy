package net.hycrafthd.umod;

import net.hycrafthd.umod.potion.RadiationPotion;
import net.minecraft.potion.Potion;

public class UPotion {

	public static Potion radiationPotion;
	
	public UPotion() {
		init();
	}

	private void init() {
		radiationPotion = new RadiationPotion();
	}
	
}
