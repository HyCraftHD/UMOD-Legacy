package net.hycrafthd.umod.container;

import java.util.Iterator;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.inventory.BaseOreInputSlot;
import net.hycrafthd.umod.inventory.BaseSlotOutput;
import net.hycrafthd.umod.utils.ModRegistryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerPulverizer extends Container{

	private static Slot input;
	private static Slot[] output = new Slot[3];
	public TileEntity ent;
	public EntityPlayer pls;
	public BlockPos pos;
	public World worldObj;
	
	public ContainerPulverizer(IInventory inv, EntityPlayer pl, World wo) {
		 this.ent = (TileEntity) inv;
		 this.pls = pl;
        this.pos = ent.getPos();
        this.worldObj = wo;
        
		output[0] = super.addSlotToContainer(new BaseSlotOutput((IInventory) ent, 0, 116, 24));
		output[1] = super.addSlotToContainer(new BaseSlotOutput((IInventory) ent, 1, 98, 54));
		output[2] = super.addSlotToContainer(new BaseSlotOutput((IInventory) ent, 2, 126, 54));
		input = super.addSlotToContainer(new BaseOreInputSlot((IInventory) ent, 3, 30, 23));	
         
         int i = 0;
         int v = 9;
         int j = 0;
         
         for (i = 0; i < 3; ++i)
         {
             for (j = 0; j < 9; ++j)
             {
             	super.addSlotToContainer(new Slot(pl.inventory, (j + (i * 9)) + v, 8 + j * 18, 84 + i * 18));
             }
         }
  
         for (i = 0; i < 9; ++i)
         {
         	super.addSlotToContainer(new Slot(pl.inventory, i, 8 + i * 18, 142));
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
                if (!this.mergeItemStack(itemstack1, 4, 39, true))
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
	
	@Override
	protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
}
