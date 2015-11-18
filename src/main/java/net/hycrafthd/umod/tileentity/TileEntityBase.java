package net.hycrafthd.umod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IInteractionObject;

public abstract class TileEntityBase extends TileEntity implements ISidedInventory,IInteractionObject{
	
	public String customname = null;

	@Override
	public boolean hasCustomName() {
		return customname != null;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}
	
	@Override
	public IChatComponent getDisplayName() {
		return new ChatComponentText(this.getName());
	}
}
