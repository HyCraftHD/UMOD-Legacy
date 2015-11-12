package net.hycrafthd.umod.potion;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class ContaminatedPotion extends Potion{

	public ContaminatedPotion() {
		super(24, new ResourceLocation(""), true, 0x17b835);
	}

	@Override
	public String getName() {
		return "Contaminated";
	}
	
}
