package net.hycrafthd.umod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class EntityPipeFX extends EntityHanging{

	
	public EntityPipeFX(World w) {
         super(w);
         this.setSize(1F, 1F);
         this.field_174860_b = EnumFacing.NORTH;

	}
	
	public EntityPipeFX(World worldIn,BlockPos p) {
		super(worldIn,p);
		this.setSize(1F, 1F);
        this.field_174860_b = EnumFacing.NORTH;
        this.setPosition((double)p.getX() + 0.5D, (double)p.getY() + 0.5D, (double)p.getZ() + 0.5D);
		this.setSize(1F, 1F);
        this.setEntityBoundingBox(new AxisAlignedBB(p, p.add(1, 1, 1)));
        this.setRotation(0, 180);
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
	public void onBroken(Entity p_110128_1_) {}
	
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
	
	 public void setPosition(double x, double y, double z)
	 {
	        this.posX = x;
	        this.posY = y;
	        this.posZ = z;
	        BlockPos blockpos = this.hangingPosition;
	        this.hangingPosition = new BlockPos(x, y, z);

	        if (!this.hangingPosition.equals(blockpos))
	        {
	            this.isAirBorne = true;
	        }
	 }

	
	@Override
	public void readFromNBT(NBTTagCompound tagCompund) {
		 this.hangingPosition = new BlockPos(tagCompund.getInteger("TileX"), tagCompund.getInteger("TileY"), tagCompund.getInteger("TileZ"));
	        EnumFacing enumfacing;

	        if (tagCompund.hasKey("Direction", 99))
	        {
	            enumfacing = EnumFacing.getHorizontal(tagCompund.getByte("Direction"));
	            this.hangingPosition = this.hangingPosition.offset(enumfacing);
	        }
	        else if (tagCompund.hasKey("Facing", 99))
	        {
	            enumfacing = EnumFacing.getHorizontal(tagCompund.getByte("Facing"));
	        }
	        else
	        {
	            enumfacing = EnumFacing.getHorizontal(tagCompund.getByte("Dir"));
	        }
	}
}
