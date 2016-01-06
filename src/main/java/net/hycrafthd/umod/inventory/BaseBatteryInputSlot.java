package net.hycrafthd.umod.inventory;

import net.hycrafthd.umod.UItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class BaseBatteryInputSlot extends BaseSlot{

	public BaseBatteryInputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
    }
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.isItemEqual(new ItemStack(UItems.battery));
	}

}
