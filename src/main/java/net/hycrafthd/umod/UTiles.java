package net.hycrafthd.umod;

import net.hycrafthd.umod.tileentity.TileEntityChargeStation;
import net.hycrafthd.umod.tileentity.TileEntityCraftFurnance;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.tileentity.TileEntitySolarPanel;
import net.hycrafthd.umod.utils.CommonRegistryUtils;

public class UTiles {

	public UTiles() {
		register();
	}

	public void register() {
		CommonRegistryUtils.registerTileEntity(TileEntityPulverizer.class, "tile.pulver.entity");
		CommonRegistryUtils.registerTileEntity(TileEntitySolarPanel.class, "tile.solar.entity");
		CommonRegistryUtils.registerTileEntity(TileEntityCable.class, "tile.pipe.entity");
		CommonRegistryUtils.registerTileEntity(TileEntityChargeStation.class, "tile.charge.entity");
		CommonRegistryUtils.registerTileEntity(TileEntityCraftFurnance.class, "tile.craftfurn.entity");
		Logger.debug(UTiles.class, "register()", "Register Tiles");
	}

}
