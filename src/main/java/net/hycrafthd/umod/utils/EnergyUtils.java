package net.hycrafthd.umod.utils;

import net.hycrafthd.umod.api.IPowerProvieder;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EnergyUtils {

	public static IPowerProvieder getNeighbourPowerProvider(BlockPos p, World w) {
		if (w.getTileEntity(p.east()) instanceof IPowerProvieder) {
			return (IPowerProvieder) w.getTileEntity(p.east());
		}
		if (w.getTileEntity(p.south()) instanceof IPowerProvieder) {
			return (IPowerProvieder) w.getTileEntity(p.south());
		}
		if (w.getTileEntity(p.north()) instanceof IPowerProvieder) {
			return (IPowerProvieder) w.getTileEntity(p.north());
		}
		if (w.getTileEntity(p.west()) instanceof IPowerProvieder) {
			return (IPowerProvieder) w.getTileEntity(p.west());
		}
		if (w.getTileEntity(p.up()) instanceof IPowerProvieder) {
			return (IPowerProvieder) w.getTileEntity(p.up());
		}
		if (w.getTileEntity(p.down()) instanceof IPowerProvieder) {
			return (IPowerProvieder) w.getTileEntity(p.down());
		}
		return null;
	}

	public static int inUE(int ip) {
		return ip / 2 * 50;
	}

}
