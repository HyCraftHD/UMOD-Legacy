package net.hycrafthd.umod.api.energy;

import java.util.ArrayList;
import java.util.Random;

import net.hycrafthd.umod.block.BlockBaseMachine;
import net.hycrafthd.umod.tileentity.TileEntityCable;
import net.hycrafthd.umod.utils.DirectionUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

@Deprecated
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
		if(!secureEnergy())return;
		BlockPos[] list = { pos.east(), pos.north(), pos.south(), pos.west(), pos.up(), pos.down() };
		for (int i = 0; i < list.length; i++) {
			Block b = worldObj.getBlockState(list[i]).getBlock();
			if (!(b instanceof BlockBaseMachine)) {
				TileEntity e = worldObj.getTileEntity(list[i]);
				if (e instanceof IPowerProvieder) {
					IPowerProvieder p = (IPowerProvieder) e;
					if(p instanceof TileEntityCable && p.canGetPower(pos,count) && pro.canAddPower(list[i],count)){
						pro.addPower(p.getPower(count));
					}else if(p instanceof TileEntityCable && p.canGetPower(pos, p.getStoredPower()) && pro.canAddPower(list[i], p.getStoredPower())){
						pro.addPower(p.getPower(p.getStoredPower()));
					}else if(p.canGetPower(pos, 2) && pro.canAddPower(list[i], 2)){
						pro.addPower(p.getPower(2));
					}
					if (p instanceof IPipeRange && pro instanceof IPipeRange) {
						IPipeRange r = (IPipeRange) p;
						IPipeRange r2 = (IPipeRange) pro;
						if (r.getPastPipeCount() < r.getMaximalRange()) {
							r2.addBlock(r.getPastPipeCount() + 1);
						} else {
							p.canGetPower(pos, i);
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

	public boolean secureEnergy(){
		if(pro.getStoredPower() < 0){
			pro.setEnergy(0);
			return false;
		}else if(pro.getStoredPower() > pro.getMaximalPower()){
			pro.setEnergy(pro.getMaximalPower());
			return false;
		}
		return true;
	}
	
	public void transferEnergy(){
		transferEnergy((pro.getMaximalPower() - pro.getStoredPower()));
	}
}
