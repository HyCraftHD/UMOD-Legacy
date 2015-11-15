package net.hycrafthd.umod.tileentity;

import net.hycrafthd.umod.api.IPlugabel;
import net.hycrafthd.umod.api.IPowerProvieder;
import net.hycrafthd.umod.block.BlockBaseMachine;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TileEntityPipe extends TileEntity implements IPlugabel,IPowerProvieder{
	
	public int Maximum_Power;
	public int stored;

	public TileEntityPipe(int maxpower) {
		Maximum_Power = maxpower;
	}
	
	@Override
	public boolean canConnect(IBlockAccess w, BlockPos p) {
		TileEntity et = w.getTileEntity(p);
		if(et instanceof IPowerProvieder){
			return true;
		}
		return false;
	}
	

	@Override
	public void update() {
		BlockPos ea = pos.east();
		BlockPos no = pos.north();
		BlockPos so = pos.south();
		BlockPos we = pos.west();
		BlockPos up = pos.up();
		BlockPos dow = pos.down();
	   
		Block b = worldObj.getBlockState(ea).getBlock();
        TileEntity e = worldObj.getTileEntity(ea);
		if(e instanceof IPowerProvieder && !(b instanceof BlockBaseMachine)){
			IPowerProvieder p = (IPowerProvieder) e;
			if(p.canGetPower(Maximum_Power) && this.canAddPower(Maximum_Power)){
				stored += p.getPower(Maximum_Power);
			}
		}
	}

	@Override
	public int getStoredPower() {
		return stored;
	}

	@Override
	public void addPower(int power) {}

	@Override
	public int getPower(int powerneed) {
	    stored -= powerneed;
		return powerneed;
	}

	@Override
	public boolean canGetPower(int power) {
		if(power <= Maximum_Power && power - stored >= 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean canAddPower(int power) {
		if(!hasPower() && power + stored <= Maximum_Power){
		return true;
		}
		return false;
	}

	@Override
	public int getMaximalPower() {
		return Maximum_Power;
	}

	@Override
	public boolean isWorking() {
		return true;
	}

	@Override
	public String getErrorMessage() {
		return null;
	}

	@Override
	public boolean hasPower() {
		return stored > 0;
	}

}
