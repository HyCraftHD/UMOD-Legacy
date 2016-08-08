package net.hycrafthd.umod.world.explosion;

import java.util.Random;

import net.hycrafthd.umod.api.ProcessHandler;
import net.hycrafthd.umod.interfaces.IProcess;
import net.hycrafthd.umod.utils.URegistryUtils;
import net.minecraft.world.World;

public class NuclearExplosion implements IProcess {
	
	private World worldObj;
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private float power;
	private Random random = new Random();
	
	private double expansion = 0;
	private boolean isDead = false;
	
	public NuclearExplosion(World world, int x, int y, int z, float power) {
		this.worldObj = world;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.power = power;
		isDead = world.isRemote;
	}
	
	@Override
	public void updateProcess() {
		
		int OD = (int) expansion;
		int ID = OD - 1;
		int size = (int) expansion;
		
		for (int x = xCoord - size; x < xCoord + size; x++) {
			for (int z = zCoord - size; z < zCoord + size; z++) {
				double dist = URegistryUtils.getDistanceAtoB(x, z, xCoord, zCoord);
				if (dist < OD && dist >= ID) {
					float tracePower = power - (float) (expansion / 10D);
					tracePower *= 1.5F + ((random.nextFloat() - 0.5F) * 0.1);
					ProcessHandler.addProcess(new NuclearExplosionTrace(worldObj, x, yCoord, z, tracePower, random));
				}
			}
		}
		
		isDead = expansion >= power * 10;
		expansion += 1;
		if (isDead()) {
			System.out.println("Fertig!");
		}
	}
	
	@Override
	public boolean isDead() {
		return isDead;
	}
}