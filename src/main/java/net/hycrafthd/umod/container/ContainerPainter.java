package net.hycrafthd.umod.container;

import java.awt.Color;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.inventory.BaseSlotOutput;
import net.hycrafthd.umod.inventory.ColorSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerPainter extends ContainerBase{

	public ContainerPainter(IInventory inv, EntityPlayer pl, BlockPos pos, World wo) {
		super(inv, pl, pos, wo,true,true);
		 
		 super.addSlotToContainer(new ColorSlot(Color.red, inv, 0, 13, 11));
		 super.addSlotToContainer(new ColorSlot(Color.green, inv, 1, 13, 32));
		 super.addSlotToContainer(new ColorSlot(Color.blue, inv, 2, 13, 53));
         super.addSlotToContainer(new BaseSlotOutput(inv, 3, 146, 10));
         int i = 0;
         int v = 9;
         int j = 0;
         
         for (i = 0; i < 3; ++i)
         {
             for (j = 0; j < 9; ++j)
             {
             	super.addSlotToContainer(new Slot(pl.inventory, (j + (i * 9)) + v + 4, 8 + j * 18, 84 + i * 18));
             }
         }
         for (i = 0; i < 9; ++i)
         {
         	super.addSlotToContainer(new Slot(pl.inventory, i + 4, 8 + i * 18, 142));
         }
	}

}
