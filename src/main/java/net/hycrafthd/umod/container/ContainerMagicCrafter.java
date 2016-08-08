package net.hycrafthd.umod.container;

import net.hycrafthd.umod.inventory.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerMagicCrafter extends ContainerBase {
	
	public ContainerMagicCrafter(IInventory inv, EntityPlayer pl,BlockPos p, World wo) {
		super(inv, pl, p, wo);
		int i = -18;
		int j;
		int k;
		
		this.addSlotToContainer(new BaseSlot(inv, 0, 56, 51));
		this.addSlotToContainer(new BaseSlot(inv, 1, 79, 58));
		this.addSlotToContainer(new BaseSlot(inv, 2, 102, 51));
		this.addSlotToContainer(new BaseSlotOutput(inv, 3, 79, 17));
		
		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(pl.inventory, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));
			}
		}
		
		for (j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(pl.inventory, j, 8 + j * 18, 160 + i));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public Slot getSlot(int slotId) {
		return super.getSlot(slotId);
	}
	
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (index < 27) {
				if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 27, false)) {
				return null;
			}
			
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}
		
		return itemstack;
	}
	
}
