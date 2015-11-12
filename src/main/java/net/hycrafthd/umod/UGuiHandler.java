package net.hycrafthd.umod;

import net.hycrafthd.umod.Gui.GuiPulverizer;
import net.hycrafthd.umod.Gui.Container.ContainerPulverizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class UGuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos p = new BlockPos(x, y, z);
		TileEntity ent = world.getTileEntity(p);
		switch (ID) {
		case 0:
			return new ContainerPulverizer((IInventory) ent, player, world);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos p = new BlockPos(x, y, z);
		TileEntity ent = world.getTileEntity(p);
		switch (ID) {
		case 0:
			return new GuiPulverizer(player, (IInventory) ent, world);
		}
		return null;
	}

}
