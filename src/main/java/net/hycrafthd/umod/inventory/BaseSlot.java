package net.hycrafthd.umod.inventory;

import net.hycrafthd.umod.render.RGBA;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class BaseSlot extends Slot {
	
	private boolean visible = true;
	private RGBA nstart = null;
	private RGBA nend = null;
	private RGBA start = null;
	private RGBA end = null;

	public BaseSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	public boolean isVisible(){
		return this.visible;
	}
	
	public void setVisible(boolean vis){
		this.visible = vis;
	}
	
	public void setHoverColor(RGBA nstart,RGBA nend,RGBA start,RGBA end){
		this.start = start;
		this.end = end;
		this.nstart = nstart;
		this.nend = nend;
	}
	
	public RGBA getHoverColor(int b){
		switch(b){
		case 0:
			return nstart;
		case 1:
			return nend;
		case 2:
			return start;
		case 3:
			return end;
		}
		return null;
	}
	
	public boolean hasColor(){
		return start != null && end != null;
	}
}
