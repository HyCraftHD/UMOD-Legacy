package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.container.ContainerChargeStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class GuiChargstation extends GuiBase {
	
	public GuiChargstation(EntityPlayer pl, IInventory tile) {
		super(new ResourceLocation(UReference.modid, "textures/gui/charge.png"), null, null, pl, tile, new ContainerChargeStation(tile, pl, ((TileEntity) tile).getPos(), ((TileEntity) tile).getWorld()));
	}
	
	@Override
	public void addToBox(GuiCombobox box2) {
	}
	
	@Override
	public void onIOModeSwitched() {
	}
}
