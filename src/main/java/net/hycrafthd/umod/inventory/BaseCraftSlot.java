package net.hycrafthd.umod.inventory;

import java.awt.Color;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BaseCraftSlot extends BaseSlot {
	
	public BaseCraftSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Craft Slot\nPut Items in the right order in\nto Craft/Smelt somthing";
			}
		});
		
		RGBA rgb = new RGBA(Color.orange);
		RGBA rgb2 = new RGBA(Color.orange);
		rgb2.setAlpha(255);
		rgb.setAlpha(25);
		this.setHoverColor(rgb, rgb, rgb2, rgb2);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return true;
	}
	
}
