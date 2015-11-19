package net.hycrafthd.umod.container;

import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerFass extends ContainerBase{

	public ContainerFass(IInventory inv, EntityPlayer pl, BlockPos pos, World wo) {
		super(inv, pl, pos, wo);
		
		for (int j = 0; j < 3; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new Slot(inv, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 3)
            {
                if (!this.mergeItemStack(itemstack1, 27, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0 && index != 2)
            {
                if (ModRegistryUtils.isRecepie(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 3, 4, false))
                    {
                    	System.out.println("Not Mergable");
                        return null;
                    }
                }
                else if (index >= 4 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 4, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 4, 39, false))
            {
                return null;
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
