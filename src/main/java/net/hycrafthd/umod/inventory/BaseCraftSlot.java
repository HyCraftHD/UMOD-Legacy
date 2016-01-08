package net.hycrafthd.umod.inventory;

import java.awt.Color;

import net.hycrafthd.umod.render.RGBA;
import net.hycrafthd.umod.utils.StringReturnment;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BaseCraftSlot extends BaseSlot{

	public BaseCraftSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.setStringRet(new StringReturnment() {
			
			@Override
			public String getString() {
				return "Craft Slot\nPut Items in the right order in\nto Craft/Smelt somthing";
			}
		});
		
		RGBA rgb = new RGBA(Color.orange);
		RGBA rgb2 = new RGBA(Color.orange);
		rgb2.setAlpha(125);
		rgb.setAlpha(50);
		this.setHoverColor(rgb, rgb, rgb2, rgb2);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return true;
	}
	
}
