package net.hycrafthd.umod.api;

import net.minecraft.item.ItemStack;

public interface IConduitProvider {
	
	public ItemStack getConduit();
	
	public boolean hasConduit();
	
    public void setConduit(ItemStack b);
}
