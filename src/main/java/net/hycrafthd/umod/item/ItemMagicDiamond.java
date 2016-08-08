package net.hycrafthd.umod.item;

import net.hycrafthd.umod.api.Crystal;

public class ItemMagicDiamond extends Crystal{
	
	public ItemMagicDiamond(){
		this.setMaxStackSize(1);
	}

	@Override
	public int energyUnits() {
		return 1;
	}
	
}
