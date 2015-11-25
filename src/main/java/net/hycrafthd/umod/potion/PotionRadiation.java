package net.hycrafthd.umod.potion;

import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.UReference;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class PotionRadiation extends Potion {

	public PotionRadiation() {
		super(UPotion.getHighestID(), new ResourceLocation(UReference.resource + "radiation"), true, 0x2ee14b);
	}

	@Override
	public String getName() {
		return StatCollector.translateToLocal("potion.radiation.name");
	}

}