package net.hycrafthd.umod;

import net.hycrafthd.umod.tileentity.TileEntityPipe;
import net.hycrafthd.umod.tileentity.TileEntityPulverizer;
import net.hycrafthd.umod.tileentity.TileEntitySolarPanel;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UTiles {

	public UTiles(){
		init();
		render();
	}
	
	public void init(){
		GameRegistry.registerTileEntity(TileEntityPulverizer.class, "tile.pulver.entity");
		GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "tile.solar.entity");
		GameRegistry.registerTileEntity(TileEntityPipe.class, "tile.pipe.entity");
	}
	
	public void render(){
		
	}
	
}
