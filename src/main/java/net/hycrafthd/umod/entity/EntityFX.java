package net.hycrafthd.umod.entity;

import net.hycrafthd.umod.UMod;
import net.minecraft.entity.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class EntityFX extends EntityHanging {
		
	public EntityFX(World w) {
		super(w);
		this.setSize(1F, 1F);
	}
	
	public EntityFX(World worldIn,BlockPos p) {
		super(worldIn,p);
		this.field_174860_b = EnumFacing.NORTH;
        this.setSize(1F, 1F);
        this.setRotation(0, 0);
		this.setEntityBoundingBox(new AxisAlignedBB(p, p.add(1, 1, 1)));
		this.setPosition((double) p.getX(), (double) p.getY(), (double) p.getZ());
	}
    
	@Override
	public boolean isEntityInvulnerable(DamageSource p_180431_1_) {
		return true;
	}
	
	@Override
	public int getWidthPixels() {
		return 0;
	}
	
	@Override
	public int getHeightPixels() {
		return 0;
	}
	
	@Override
	public void onBroken(Entity p_110128_1_) {
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public void onUpdate() {
		 if(this.isDead)return;
		 this.prevPosX = this.posX;
	     this.prevPosY = this.posY;
	     this.prevPosZ = this.posZ;
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	public BlockPos getPosition() {
		return super.getPosition();
	}
	
	public void setPosition(double x, double y, double z) {
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		BlockPos blockpos = this.hangingPosition;
		this.hangingPosition = new BlockPos(x, y, z);
		
		if (!this.hangingPosition.equals(blockpos)) {
			this.isAirBorne = true;
		}
		//UMod.log.info(this.hangingPosition.toString());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompund) {}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompund){}
	
}
