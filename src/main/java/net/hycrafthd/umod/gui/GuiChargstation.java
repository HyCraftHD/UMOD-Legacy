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
		super(new ResourceLocation(UReference.modid, "textures/gui/charge.png"), pl, tile, new ContainerChargeStation(tile, pl, ((TileEntity)tile).getPos(), ((TileEntity)tile).getWorld()));
	}
	
	@Override
	public void initGui() {
		super.initGui();
		GuiButton btn = new GuiButton(3, 190, 20,100,20, ((TileEntityChargeStation)ent).getMode() ? "Load Item":"Load in System");
		buttonList.add(btn);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		switch(button.id){
		case 3:
			TileEntityChargeStation st = ((TileEntityChargeStation)ent);
			((TileEntityChargeStation)ent).setMode(!st.getMode());
		button.displayString = ((TileEntityChargeStation)ent).getMode() ? "Load Item":"Load in System";
		break;
		}
	}
	
}
