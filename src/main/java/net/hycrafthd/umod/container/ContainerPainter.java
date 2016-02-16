package net.hycrafthd.umod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerPainter extends ContainerBase{

	public ContainerPainter(IInventory inv, EntityPlayer pl, BlockPos pos, World wo) {
		super(inv, pl, pos, wo);
		
		
		
		 int i = 0;
		 int j = 0;
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
