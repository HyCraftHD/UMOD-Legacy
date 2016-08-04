package net.hycrafthd.umod;

import net.hycrafthd.corelib.util.ICustomGuiHandler;
import net.hycrafthd.umod.api.energy.IPowerProvieder;
import net.hycrafthd.umod.container.*;
import net.hycrafthd.umod.enumtype.*;
import net.hycrafthd.umod.gui.*;
import net.hycrafthd.umod.inventory.InventoryBackPack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class UGuiHandler implements ICustomGuiHandler {

	@SuppressWarnings("incomplete-switch")
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos p = new BlockPos(x, y, z);
		TileEntity ent = world.getTileEntity(p);

		switch (EnumTypeGui.byID(ID)) {
		case PULVERISER:
			return new ContainerPulverizer((IInventory) ent, player, world);
		case CHARGESTATION:
			return new ContainerChargeStation((IInventory) ent, player, p, world);
		case CRAFTFURNANCE:
			return new ContainerCraftFurnace((IInventory) ent, player, p, world);
		case BACKPACK:
			ItemStack itemstack = player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem().equals(UItems.backpack)) {
				EnumTypeBackPack type = EnumTypeBackPack.byMetadata(itemstack.getMetadata());
				InventoryBackPack inventory = new InventoryBackPack(itemstack, player, type.getCount());
				return new ContainerBackPack(inventory, player.inventory, type);
			}
			break;
		case BARRELS:
			//return new ContainerBarrels(player.get);
			break;
		case PAINTER:
			return new ContainerPainter((IInventory) ent, player, world);
		}
		return null;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos p = new BlockPos(x, y, z);
		TileEntity ent = world.getTileEntity(p);

		switch (EnumTypeGui.byID(ID)) {
		case PULVERISER:
			return new GuiPulverizer(player, (IInventory) ent, world, p);
		case SOLARPANEL:
			return new GuiSolarPanel(world, (IPowerProvieder) ent);
		case BATTERIE:
			return new GuiBattery(world, (IPowerProvieder) ent, player, p, 0);
		case CHARGESTATION:
			return new GuiChargstation(player, (IInventory) ent);
		case CRAFTFURNANCE:
			return new GuiCraftFurnance(player, (IInventory) ent, p, world);
		case BACKPACK:
			ItemStack itemstack = player.inventory.getCurrentItem();
			if (itemstack != null && itemstack.getItem().equals(UItems.backpack)) {
				EnumTypeBackPack type = EnumTypeBackPack.byMetadata(itemstack.getMetadata());
				InventoryBackPack inventory = new InventoryBackPack(itemstack, player, type.getCount());
				return new GuiBackPack(inventory, player.inventory, type);
			}
			break;
		case PAINTER:
			return new GuiPainter(player, (IInventory) ent, new ContainerPainter((IInventory) ent, player, world));
		}
		return null;

	}

	@Override
	public String getMod() {
		return UReference.modid;
	}

}
