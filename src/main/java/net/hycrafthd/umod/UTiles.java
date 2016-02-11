package net.hycrafthd.umod;

import net.hycrafthd.umod.tileentity.TileEntityChargeStation;
import net.hycrafthd.umod.tileentity.TileEntityCraftFurnance;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.tileentity.TileEntitySolarPanel;
import net.hycrafthd.umod.utils.CommonRegistryUtils;

public class UTiles {

	public UTiles() {
		register();
	}

	public void register() {
		CommonRegistryUtils.registerTileEntity(TileEntityPulverizer.class, "tilepulver");
		CommonRegistryUtils.registerTileEntity(TileEntitySolarPanel.class, "tilesolar");
		CommonRegistryUtils.registerTileEntity(TileEntityCable.class, "tilepipe");
		CommonRegistryUtils.registerTileEntity(TileEntityChargeStation.class, "tilecharge");
		CommonRegistryUtils.registerTileEntity(TileEntityCraftFurnance.class, "tilecraftfurn");
		CommonRegistryUtils.registerTileEntity(TileEntityEnergyPannel.class, "tileenergymonitor");
		Logger.debug(UTiles.class, "register()", "Register TileEntitys");
	}

}
