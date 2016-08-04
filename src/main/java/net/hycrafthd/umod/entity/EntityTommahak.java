package net.hycrafthd.umod.entity;

import java.util.List;

import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class EntityTommahak extends Entity {
	
	private EntityLivingBase throwedby;
	private int ticksInAir;
	
	public EntityTommahak(World worldIn) {
		super(worldIn);
	}
	
	public EntityTommahak(World worldIn, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_) {
		this(worldIn);
		this.setPosition(p_i1730_2_, p_i1730_4_, p_i1730_6_);
		this.throwedby = p_i1730_8_;
	}
	
	@Override
	protected void entityInit() {
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void onUpdate() {
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		
		++this.ticksInAir;
		
		Vec3 vec3 = new Vec3(this.posX, this.posY, this.posZ);
		Vec3 vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
		vec3 = new Vec3(this.posX, this.posY, this.posZ);
		vec31 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		
		if (movingobjectposition != null) {
			vec31 = new Vec3(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		
		if (!this.worldObj.isRemote) {
			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;
			EntityLivingBase entitylivingbase = this.getThrower();
			
			for (int j = 0; j < list.size(); ++j) {
				Entity entity1 = (Entity) list.get(j);
				
				if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.ticksInAir >= 5)) {
					float f = 0.3F;
					AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand((double) f, (double) f, (double) f);
					MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
					
					if (movingobjectposition1 != null) {
						double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
						
						if (d1 < d0 || d0 == 0.0D) {
							entity = entity1;
							d0 = d1;
						}
					}
				}
			}
			
			if (entity != null) {
				movingobjectposition = new MovingObjectPosition(entity);
			}
		}
		
		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.worldObj.getBlockState(movingobjectposition.getBlockPos()).getBlock() == Blocks.portal) {
				this.setInPortal();
			} else {
				this.onImpact(movingobjectposition);
			}
		}
		
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
		
		for (this.rotationPitch = (float) (Math.atan2(this.motionY, (double) f1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
			;
		}
		
		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
			this.prevRotationPitch += 360.0F;
		}
		
		while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
			this.prevRotationYaw -= 360.0F;
		}
		
		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
			this.prevRotationYaw += 360.0F;
		}
		
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float f2 = 0.99F;
		float f3 = 0;
		
		if (this.isInWater()) {
			for (int i = 0; i < 4; ++i) {
				float f4 = 0.25F;
				this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * (double) f4, this.posY - this.motionY * (double) f4, this.posZ - this.motionZ * (double) f4, this.motionX, this.motionY, this.motionZ, new int[0]);
			}
			
			f2 = 0.8F;
		}
		
		this.motionX *= (double) f2;
		this.motionY *= (double) f2;
		this.motionZ *= (double) f2;
		this.motionY -= (double) f3;
		this.setPosition(this.posX, this.posY, this.posZ);
	}
	
	private void onImpact(MovingObjectPosition p) {
		Entity et = p.entityHit;
		if (et != null && ((et instanceof EntityPlayer && !((EntityPlayer) et).capabilities.isCreativeMode) || !(et instanceof EntityPlayer))) {
			et.setDead();
		}
		this.setDead();
	}
	
	public EntityLivingBase getThrower() {
		return throwedby;
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		
	}
	
}
