package net.hycrafthd.umod.inventory;

import net.hycrafthd.umod.block.BlockOres;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class BaseOreInputSlot extends Slot {

	public BaseOreInputSlot(IInventory to, int index, int xPosition, int yPosition) {
		super(to, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		Block bl = Block.getBlockFromItem(stack.getItem());
		if (bl != null) {
			if (bl instanceof BlockOre || bl instanceof BlockOres) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return true;
	}

	@Override
	public void onSlotChanged() {
		super.onSlotChanged();
		this.inventory.markDirty();
	}

}
