package net.hycrafthd.umod.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class RadiationPotion extends Potion {

	public RadiationPotion() {
		super(24, new ResourceLocation(""), true, 0x2ee14b);
	}
	
	@Override
	public String getName() {
		return StatCollector.translateToLocal("potion.radiation.name");
	}
}