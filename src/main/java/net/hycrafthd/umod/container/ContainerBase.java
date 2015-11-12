package net.hycrafthd.umod.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ContainerBase extends Container{

	public TileEntity ent;
	public EntityPlayer pls;
	public BlockPos pos;
	public World worldObj;
	
	public ContainerBase(IInventory inv,EntityPlayer pl,BlockPos pos,World wo) {
		  this.ent = (TileEntity) inv;
		  this.pls = pl;
          this.pos = pos;
          this.worldObj = wo;
          
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
          this.detectAndSendChanges();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
	public boolean canMergeSlot(ItemStack p_94530_1_, Slot p_94530_2_) {
		return true;
	}
}
