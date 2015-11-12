package net.hycrafthd.umod.container;

import net.hycrafthd.umod.inventory.BaseOreInputSlot;
import net.hycrafthd.umod.inventory.BaseSlotOutput;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ContainerPulverizer extends ContainerBase{

	private static Slot input;
	private static Slot[] output = new Slot[3];
	
	public ContainerPulverizer(IInventory inv, EntityPlayer pl, World wo) {
		super(inv, pl, ((TileEntity) inv).getPos(), wo);
		input = super.addSlotToContainer(new BaseOreInputSlot(inv, 3, 30, 23));
		output[0] = super.addSlotToContainer(new BaseSlotOutput(inv, 0, 116, 24));
		output[1] = super.addSlotToContainer(new BaseSlotOutput(inv, 1, 98, 54));
		output[2] = super.addSlotToContainer(new BaseSlotOutput(inv, 2, 126, 54));
				
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return ((IInventory)ent).isUseableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		return ((Slot) this.inventorySlots.get(index)).getStack().copy();
	}
}
