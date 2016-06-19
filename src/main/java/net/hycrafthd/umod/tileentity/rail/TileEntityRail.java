package net.hycrafthd.umod.tileentity.rail;

import net.hycrafthd.umod.entity.rail.EntityRailFX;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRail extends TileEntity implements IUpdatePlayerListBox{

	public boolean isn = false;
	
	@Override
	public void update() {
		if(!isn)init();
	}

	private void init() {
		this.worldObj.spawnEntityInWorld(new EntityRailFX(worldObj, pos));
		isn = true;
	}
    
	
	
}
