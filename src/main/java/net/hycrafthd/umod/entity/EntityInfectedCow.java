package net.hycrafthd.umod.entity;

import net.hycrafthd.umod.UItems;
import net.hycrafthd.umod.interfaces.IInfectedEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityInfectedCow extends EntityMob implements IInfectedEntity {

	public EntityInfectedCow(World worldIn) {
		super(worldIn);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(2, this.field_175455_a);
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));

		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (itemstack != null && itemstack.getItem() == Items.bucket && !player.capabilities.isCreativeMode) {
			if (itemstack.stackSize-- == 1) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(UItems.infectedmilk));
			} else if (!player.inventory.addItemStackToInventory(new ItemStack(UItems.infectedmilk))) {
				player.dropPlayerItemWithRandomChoice(new ItemStack(UItems.infectedmilk, 1, 0), false);
			}

			return true;
		} else {
			return super.interact(player);
		}
	}

	@Override
	public Item getDropItem() {
		return UItems.infectedleather;
	}

	@Override
	public void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
		int j = this.rand.nextInt(3) + this.rand.nextInt(1 + p_70628_2_);
		int k;

		for (k = 0; k < j; ++k) {
			this.dropItem(UItems.infectedleather, 1);
		}

		j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + p_70628_2_);

		for (k = 0; k < j; ++k) {
			this.dropItem(UItems.infectedbeef, 1);
		}
	}

	@Override
	public String getLivingSound() {
		return "mob.cow.say";
	}

	@Override
	public String getHurtSound() {
		return "mob.cow.hurt";
	}

	@Override
	public String getDeathSound() {
		return "mob.cow.hurt";
	}

	@Override
	public void playStepSound(BlockPos p_180429_1_, Block p_180429_2_) {
		this.playSound("mob.cow.step", 0.15F, 1.0F);
	}

	@Override
	public float getSoundVolume() {
		return 0.4F;
	}

}
