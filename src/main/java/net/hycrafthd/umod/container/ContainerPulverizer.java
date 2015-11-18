package net.hycrafthd.umod.container;

import java.util.Iterator;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.inventory.BaseOreInputSlot;
import net.hycrafthd.umod.inventory.BaseSlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
}
