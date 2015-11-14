package net.hycrafthd.umod.block;

import net.hycrafthd.umod.UPotion;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockInfectedDirt extends BlockBase {

	public BlockInfectedDirt() {
		super(Material.ground);
		this.setHarvestLevel("spade", 2);
		this.setHardness(0.6F);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	public void onLanded(World world, Entity entity) {
		if (entity instanceof EntityLivingBase && !world.isRemote) {
			EntityLivingBase base = (EntityLivingBase) entity;

			if (base instanceof EntityPlayer) {
				EntityPlayer sp = (EntityPlayer) base;
				if (sp.capabilities.isCreativeMode) return;
			}

//			base.addPotionEffect(new PotionEffect(Potion.poison.getId(), 120, 3, false, false));
//			base.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 120, 2, false, false));
			base.addPotionEffect(new PotionEffect(UPotion.radiationPotion.getId(), 30, 1, true, true));
		}
		super.onLanded(world, entity);
	}

}
