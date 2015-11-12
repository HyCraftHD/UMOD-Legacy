package net.hycrafthd.umod;

import net.hycrafthd.umod.TileEntity.TileEntityPulverizer;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class UTiles {

	public UTiles(){
		init();
		render();
	}
	
	public void init(){
		GameRegistry.registerTileEntity(TileEntityPulverizer.class, "tile.pulver.entity");
	}
	
	public void render(){
		
	}
	
}
