package net.hycrafthd.umod.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class EventCraftFromMachine extends Event {
	
	public TileEntity ent;
	public BlockPos pos;
	public World worldObj;
	
	public EventCraftFromMachine(TileEntity ent, BlockPos pos, World world) {
		this.ent = ent;
		this.pos = pos;
		this.worldObj = world;
	}
	
}
