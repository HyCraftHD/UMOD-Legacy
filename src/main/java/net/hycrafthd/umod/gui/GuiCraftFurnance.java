package net.hycrafthd.umod.gui;

import net.hycrafthd.umod.container.ContainerCraftFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCraftFurnance extends GuiBase {

	public GuiCraftFurnance(EntityPlayer pl, IInventory tile, BlockPos pos, World wo) {
		super(new GuiRescources("craftfurn.png"), new GuiRescources("battery.png"), new GuiRescources("IOMode.png"), pl, tile, new ContainerCraftFurnace(tile, pl, pos, wo));
	}

	@Override
	public void addToBox(GuiCombobox box2) {
		box2.getItems().add("Choose");
		box2.getItems().add("Output");
	}

	@Override
	public void onIOModeSwitched() {
		// TODO Auto-generated method stub
	}

}
