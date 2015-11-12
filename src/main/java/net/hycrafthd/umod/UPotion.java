package net.hycrafthd.umod;

import net.hycrafthd.umod.block.BlockBlocks;
import net.hycrafthd.umod.block.BlockOres;
import net.hycrafthd.umod.item.ItemBlockBlocks;
import net.hycrafthd.umod.item.ItemBlockOres;
import net.hycrafthd.umod.potion.ContaminatedPotion;
import net.minecraft.block.Block;

public class UPotion {

	public static ContaminatedPotion contaminatedPotion;
	
	public UPotion() {
		register();
	}

	private void register() {
		contaminatedPotion = new ContaminatedPotion();
	}
}
