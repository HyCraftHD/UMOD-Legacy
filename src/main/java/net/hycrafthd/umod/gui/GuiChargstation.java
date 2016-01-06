package net.hycrafthd.umod.gui;

import java.io.IOException;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerChargeStation;
import net.hycrafthd.umod.enumtype.EnumTypeGui;
import net.hycrafthd.umod.tileentity.TileEntityChargeStation;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiChargstation extends GuiBase {

	public GuiChargstation(EntityPlayer pl, IInventory tile) {
		super(new ResourceLocation(UReference.modid, "textures/gui/charge.png"),null, null,pl, tile, new ContainerChargeStation(tile, pl, ((TileEntity)tile).getPos(), ((TileEntity)tile).getWorld()));
	}
}
