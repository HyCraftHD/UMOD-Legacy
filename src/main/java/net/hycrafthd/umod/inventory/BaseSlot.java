package net.hycrafthd.umod.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class BaseSlot extends Slot {
	
	private boolean visible = true;

	public BaseSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	public boolean isVisible(){
		return this.visible;
	}
	
	public void setVisible(boolean vis){
		this.visible = vis;
	}
	
}
