package net.hycrafthd.umod.container;

import net.hycrafthd.umod.inventory.BaseBatteryInputSlot;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerBattery extends ContainerBase{

	Slot sl = null;
	
	public ContainerBattery(IInventory inv, EntityPlayer pl, BlockPos pos, World wo) {
		super(inv, pl, pos, wo);
		sl = super.addSlotToContainer(new BaseBatteryInputSlot(inv, inv.getSizeInventory() - 1, 80, 30));
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		int x = this.inventorySlots.size();
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 36)
            {
                if (!this.mergeItemStack(itemstack1, 0, 36, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else 
            {
                if (sl.isItemValid(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 36, 37, false))
                    {
                        return null;
                    }
                }
                else if (index >= 0 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 37, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 37 && !this.mergeItemStack(itemstack1, 0, 30, false))
                {
                    return null;
                }
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
		}

}
