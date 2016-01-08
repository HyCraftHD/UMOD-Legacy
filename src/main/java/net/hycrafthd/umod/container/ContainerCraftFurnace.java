package net.hycrafthd.umod.container;

import net.hycrafthd.umod.inventory.BaseCraftSlot;
import net.hycrafthd.umod.inventory.BaseSlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerCraftFurnace extends ContainerBase {

	public ContainerCraftFurnace(IInventory inv, EntityPlayer pl, BlockPos pos, World wo) {
		super(inv, pl, pos, wo);
				
		 int i = 0;
         int j = 0;
         
         for (i = 0; i < 3; ++i)
         {
             for (j = 0; j < 3; ++j)
             {
             	super.addSlotToContainer(new BaseCraftSlot(inv, j + (i * 3), 25 + j * 18, 20 + i * 18));
             }
         }
  
         super.addSlotToContainer(new BaseSlotOutput(inv, 9, 135, 38));
         
         int v = 9;
         
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

}
