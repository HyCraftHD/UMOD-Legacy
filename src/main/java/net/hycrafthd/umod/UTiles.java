package net.hycrafthd.umod;

import net.hycrafthd.corelib.registry.TileEntityRegistry;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.tileentity.TileEntityChargeStation;
import net.hycrafthd.umod.tileentity.TileEntityCraftFurnance;
import net.hycrafthd.umod.tileentity.TileEntityEnergyPannel;
import net.hycrafthd.umod.tileentity.TileEntityPainter;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.tileentity.TileEntitySolarPanel;
import net.hycrafthd.umod.tileentity.rail.TileEntityRail;

public class UTiles {

	public UTiles() {
		register();
	}

	public void register() {
		TileEntityRegistry.register(TileEntityPulverizer.class, "tilepulver");
		TileEntityRegistry.register(TileEntitySolarPanel.class, "tilesolar");
		TileEntityRegistry.register(TileEntityCable.class, "tilepipe");
		TileEntityRegistry.register(TileEntityChargeStation.class, "tilecharge");
		TileEntityRegistry.register(TileEntityCraftFurnance.class, "tilecraftfurn");
		TileEntityRegistry.register(TileEntityEnergyPannel.class, "tileenergymonitor");
		TileEntityRegistry.register(TileEntityPainter.class, "tilepainter");
		TileEntityRegistry.register(TileEntityRail.class, "tilereail");
		UMod.log.debug("Register TileEntitys");
	}

}
