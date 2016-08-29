package net.hycrafthd.umod.container;

import java.awt.Color;

import net.hycrafthd.corelib.util.*;
import net.hycrafthd.umod.api.ISliderEntry;
import net.hycrafthd.umod.block.BlockConduit;
import net.hycrafthd.umod.inventory.*;
import net.hycrafthd.umod.item.ItemBackPack;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerPainter extends ContainerBase {
	
	public ContainerPainter(IInventory inv, EntityPlayer pl, World wo) {
		super(inv, pl, ((TileEntity) inv).getPos(), wo, true, true);
		super.addSlotToContainer(new ColorSlot(Color.red, (IInventory) ent, 0, 13, 11));
		super.addSlotToContainer(new ColorSlot(Color.green, (IInventory) ent, 1, 13, 32));
		super.addSlotToContainer(new ColorSlot(Color.blue, (IInventory) ent, 2, 13, 53));
		super.addSlotToContainer(new BaseCraftSlot((IInventory) ent, 3, 146, 11));
		super.addSlotToContainer(new BaseCraftSlot((IInventory) ent, 4, 146, 32));

		int i = 0;
		int v = 9;
		int j = 0;
		
		for (i = 0; i < 3; ++i) {
			for (j = 0; j < 9; ++j) {
				super.addSlotToContainer(new Slot(pl.inventory, (j + (i * 9)) + v, 8 + j * 18, 84 + i * 18));
			}
		}
		for (i = 0; i < 9; ++i) {
			super.addSlotToContainer(new Slot(pl.inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		ISliderEntry sl = (ISliderEntry) inventoryIn;
		if(this.inventorySlots.get(3) != null && this.inventorySlots.get(3) instanceof Slot && ((Slot)this.inventorySlots.get(3)).getHasStack()){
			if(((Slot)this.inventorySlots.get(3)).getStack().getItem() instanceof ItemBackPack){
			ColorUtils.setColor(((Slot)this.inventorySlots.get(3)).getStack(), new RGBA(sl.getValueFromId(0),sl.getValueFromId(1),sl.getValueFromId(2),sl.getValueFromId(3)).toAWTColor().getRGB());
			}
			if(Block.getBlockFromItem(((Slot)this.inventorySlots.get(3)).getStack().getItem()) != null && Block.getBlockFromItem(((Slot)this.inventorySlots.get(3)).getStack().getItem()) instanceof BlockConduit){
				
			}
		}
	}
}
