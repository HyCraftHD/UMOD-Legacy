package net.hycrafthd.umod.entity;

import java.util.Random;

import net.hycrafthd.umod.api.ProcessHandler;
import net.hycrafthd.umod.world.explosion.NuclearExplosion;
import net.minecraft.entity.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.*;

public class EntityNukePrimed extends Entity {

	public static int fuseSec;
	public static int nukePower;

	public int fuse;
	private EntityLivingBase tntPlacedBy;

	public EntityNukePrimed(World worldIn) {
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
	}

	public EntityNukePrimed(World worldIn, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_) {
		this(worldIn);
		this.setPosition(p_i1730_2_, p_i1730_4_, p_i1730_6_);
		float f = (float) (Math.random() * Math.PI * 2.0D);
		this.motionX = -((float) Math.sin(f)) * 0.02F;
		this.motionY = 0.20000000298023224D;
		this.motionZ = -((float) Math.cos(f)) * 0.02F;
		this.fuse = fuseSec * 20;
		this.prevPosX = p_i1730_2_;
		this.prevPosY = p_i1730_4_;
		this.prevPosZ = p_i1730_6_;
		this.tntPlacedBy = p_i1730_8_;
	}

	@Override
	protected boolean canTriggerWalking() {
		return false;
	}

	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;

		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.fuse-- <= 0) {
			this.setDead();

			if (!this.worldObj.isRemote) {
				this.explode();
			}
		} else {
			this.handleWaterMovement();
			for (int i = 0; i < 10; i++) {
				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						double d = MathHelper.getRandomDoubleInRange(new Random(), 0, 0.2D);
						try{
							spawnParticle(this.worldObj, EnumParticleTypes.SMOKE_LARGE, true, this.posX, this.posY - 0.5D, this.posZ, x * d, 0.5D, z * d, new int[0]);
						}catch(NoSuchMethodError e){}
					}
				}
			}
		}
	}

	private void explode() {
		
		if(!worldObj.getGameRules().getGameRuleBooleanValue("allowExplosion")) return;
		
		BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
		float power = 2F + (((7000) / 10369F) * 18F);
		ProcessHandler.addProcess(new NuclearExplosion(this.worldObj, pos.getX(), pos.getY(), pos.getZ(), power));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		tagCompound.setByte("Fuse", (byte) this.fuse);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		this.fuse = tagCompund.getByte("Fuse");
	}

	public EntityLivingBase getTntPlacedBy() {
		return this.tntPlacedBy;
	}

	@Override
	public float getEyeHeight() {
		return 0.0F;
	}

	@Override
	protected void entityInit() {
		this.fuse = fuseSec * 20;
	}
	
	@SideOnly(Side.CLIENT)
	public void spawnParticle(World worldIn, EnumParticleTypes particleType, boolean b, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int[] p_175688_14_){
		worldIn.spawnParticle(particleType, b, xCoord, yCoord, zCoord, xOffset, yOffset, zOffset, p_175688_14_);
	}
	
}
