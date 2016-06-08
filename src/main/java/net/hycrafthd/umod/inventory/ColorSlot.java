package net.hycrafthd.umod.inventory;

import java.awt.Color;

import net.hycrafthd.umod.render.RGBA;
import net.hycrafthd.umod.utils.StringReturnment;
import net.minecraft.inventory.IInventory;

public class ColorSlot extends BaseSlot {

	public Color color;
	
	public ColorSlot(Color c,IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		color = c;
		this.setHoverColor(new RGBA(color).setAlpha(75), new RGBA(color).setAlpha(75), new RGBA(color).setAlpha(125), new RGBA(color).setAlpha(125));
		this.setStringRet(new StringReturnment() {
			
			@Override
			public String getString() {
				return "Color Slot put dye in\nFor color: " + (color.equals(Color.RED) ? "Red": (color.equals(Color.GREEN) ? "Green": color.equals(Color.blue) ? "Blue":("" + color.getRed() + "," + color.getGreen() + "," + color.getBlue())));
			}
		});
	}

	
	
	@Override
	public int getFontColor() {
		return 0xFFFFFF;
	}

}
