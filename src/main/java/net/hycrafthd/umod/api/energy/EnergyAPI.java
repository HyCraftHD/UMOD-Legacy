package net.hycrafthd.umod.api.energy;

import java.util.ArrayList;
import java.util.Random;

import net.hycrafthd.umod.block.BlockBaseMachine;
import net.hycrafthd.umod.tileentity.TileEntityPipe;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EnergyAPI {
	
	private IPowerProvieder pro;
	private BlockPos pos;
	private World worldObj;
	
	public EnergyAPI(BlockPos pos,World w) {
		this((IPowerProvieder) w.getTileEntity(pos));
		
	}
	
	public EnergyAPI(IPowerProvieder power) {
		if(power != null){
		pro = power;
		pos = power.getPos();
		worldObj = power.getWorld();
		}else{
			throw(new IllegalArgumentException(new NullPointerException("Don't use EnergyAPI out of an IPowerProvieder")));
		}
	}
	
	public void transferEnergy(int count){
		BlockPos[] list = { pos.east(), pos.north(), pos.south(), pos.west(), pos.up(), pos.down() };
		for (int i = 0; i < list.length; i++) {
			Block b = worldObj.getBlockState(list[i]).getBlock();
			if (!(b instanceof BlockBaseMachine)) {
				TileEntity e = worldObj.getTileEntity(list[i]);
				if (e instanceof IPowerProvieder) {
					IPowerProvieder p = (IPowerProvieder) e;
					if(!(p instanceof TileEntityPipe)){
						pro.addPower(p.getPower(2));
					}else if (p.canGetPower(pos,count) && pro.canAddPower(list[i],count)) {
						pro.addPower(p.getPower(count));
					}else if(p.canGetPower(pos,p.getMaximalPower()) && pro.canAddPower(p.getPos(),p.getMaximalPower())){
						pro.addPower(p.getPower(p.getMaximalPower()));
					}else if(pro.canAddPower(p.getPos(),p.getStoredPower())){
						pro.addPower(p.getPower(p.getStoredPower()));
					}
					if (p instanceof IPipeRange && pro instanceof IPipeRange) {
						IPipeRange r = (IPipeRange) p;
						IPipeRange r2 = (IPipeRange) pro;
						if (r.getPastPipeCount() < r.getMaximalRange()) {
							r2.addBlock(r.getPastPipeCount() + 1);
						} else {
							r2.remove(1);
							r2.clearPast();
						}
					}
				}
			}
		}
	}
	
	public void tranferFromBattery(ItemStack stack){
		if(stack != null){
		if(pro.canAddPower(null, 5)){
		if(stack.attemptDamageItem(5, new Random())){
		     stack = null;
		}else{
		  pro.addPower(5);
		}
		}
		}
	}

	public void transferEnergy(){
		transferEnergy((pro.getMaximalPower() - pro.getStoredPower()));
	}
}
