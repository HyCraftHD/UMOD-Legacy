package net.hycrafthd.umod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class UTab extends CreativeTabs{

	public UTab() {
		super("utab");
	}

	@Override
	public Item getTabIconItem() {
		return Items.diamond;
	}

}
