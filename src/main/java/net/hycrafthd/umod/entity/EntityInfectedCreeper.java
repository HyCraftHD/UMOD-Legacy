package net.hycrafthd.umod.entity;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.interfaces.IInfectedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAICreeperSwell;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.base.Predicate;

public class EntityInfectedCreeper extends EntityCreeper implements IInfectedEntity {

	private int lastActiveTime;
	private int timeSinceIgnited;
	private int fuseTime = 20;
	private int explosionRadius = 6;

	@SuppressWarnings("rawtypes")
	public EntityInfectedCreeper(World worldIn) {
		super(worldIn);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAICreeperSwell(this));
		this.tasks.addTask(2, this.field_175455_a);
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, new Predicate() {
			public boolean func_179958_a(Entity p_179958_1_) {
				return p_179958_1_ instanceof EntityOcelot;
			}

			@Override
			public boolean apply(Object p_apply_1_) {
				return this.func_179958_a((Entity) p_apply_1_);
			}
		}, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false, new Class[0]));
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
	}

	@Override
	public Item getDropItem() {
		return Items.gunpowder;
	}

	@Override
	public void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
		int gunSize = 1;
		if (this.rand.nextInt(2) == 0) {
			int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + p_70628_2_);
			this.entityDropItem(new ItemStack(UItems.dusts, j, EnumTypeBaseStuff.URAN.getMetadata()), 0.0F);
			System.out.println("Drop");
		}
		if (this.rand.nextInt(2) == 0)
			gunSize = 2;
		this.dropItem(Items.gunpowder, gunSize);
	}

	@Override
	public void entityInit() {
		super.entityInit();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);

		if (this.dataWatcher.getWatchableObjectByte(17) == 1) {
			tagCompound.setBoolean("powered", true);
		}

		tagCompound.setShort("Fuse", (short) this.fuseTime);
		tagCompound.setByte("ExplosionRadius", (byte) this.explosionRadius);
		tagCompound.setBoolean("ignited", this.func_146078_ca());
	}

	/**
	 * (abstract) public helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund) {
		super.readEntityFromNBT(tagCompund);
		this.dataWatcher.updateObject(17, Byte.valueOf((byte) (tagCompund.getBoolean("powered") ? 1 : 0)));

		if (tagCompund.hasKey("Fuse", 99)) {
			this.fuseTime = tagCompund.getShort("Fuse");
		}

		if (tagCompund.hasKey("ExplosionRadius", 99)) {
			this.explosionRadius = tagCompund.getByte("ExplosionRadius");
		}

		if (tagCompund.getBoolean("ignited")) {
			this.func_146079_cb();
		}
	}

	@Override
	public void onUpdate() {
		if (this.isEntityAlive()) {
			this.lastActiveTime = this.timeSinceIgnited;

			if (this.func_146078_ca()) {
				this.setCreeperState(1);
			}

			int i = this.getCreeperState();

			if (i > 0 && this.timeSinceIgnited == 0) {
				this.playSound("creeper.primed", 1.0F, 0.5F);
			}

			this.timeSinceIgnited += i;

			if (this.timeSinceIgnited < 0) {
				this.timeSinceIgnited = 0;
			}

			if (this.timeSinceIgnited >= this.fuseTime) {
				this.timeSinceIgnited = this.fuseTime;
				this.explode();
			}
		}

		super.onUpdate();
	}

	private void explode() {
		if (!this.worldObj.isRemote) {
			boolean flag = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
			float f = this.getPowered() ? 1.5F : 0.5F;
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, this.explosionRadius * f, flag);
			this.setDead();
		}
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (itemstack != null && itemstack.getItem() == Items.flint_and_steel) {
			this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.ignite", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
			player.swingItem();

			if (!this.worldObj.isRemote) {
				this.func_146079_cb();
				itemstack.damageItem(1, player);
				return true;
			}
		}

		return super.interact(player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCreeperFlashIntensity(float p_70831_1_) {
		return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (this.fuseTime - 2);
	}

	@Override
	public String getHurtSound() {
		return "mob.creeper.say";
	}

	@Override
	public String getDeathSound() {
		return "mob.creeper.death";
	}

	@Override
	public int getCreeperState() {
		return this.dataWatcher.getWatchableObjectByte(16);
	}

	@Override
	public void setCreeperState(int p_70829_1_) {
		this.dataWatcher.updateObject(16, Byte.valueOf((byte) p_70829_1_));
	}

	@Override
	public boolean getPowered() {
		return this.dataWatcher.getWatchableObjectByte(17) == 1;
	}

	@Override
	public boolean func_146078_ca() {
		return this.dataWatcher.getWatchableObjectByte(18) != 0;
	}

	@Override
	public void func_146079_cb() {
		this.dataWatcher.updateObject(18, Byte.valueOf((byte) 1));
	}

}
