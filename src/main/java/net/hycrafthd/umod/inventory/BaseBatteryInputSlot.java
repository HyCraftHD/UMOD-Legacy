package net.hycrafthd.umod.inventory;

import net.hycrafthd.corelib.util.RGBA;
import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.utils.StringMethod;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BaseBatteryInputSlot extends BaseSlot {
	
	public BaseBatteryInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		RGBA back = new RGBA(255, 0, 0, 255);
		RGBA nback = new RGBA(255, 0, 0, 25);
		this.setStringRet(new StringMethod() {
			
			@Override
			public String getString() {
				return "Batteryslot\nPut a Battery in to Load the Maschine";
			}
		});
		this.setHoverColor(nback, nback, back, back);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.isItemEqual(new ItemStack(UItems.battery));
	}
	
}
