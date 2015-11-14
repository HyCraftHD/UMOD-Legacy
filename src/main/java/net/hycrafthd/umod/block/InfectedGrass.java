package net.hycrafthd.umod.block;

import net.hycrafthd.umod.armor.RadiationArmor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class InfectedGrass extends BlockBase {

	public InfectedGrass() {
		super(Material.grass);
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
				if (sp.capabilities.isCreativeMode)
					return;
				boolean full = false;
				for (ItemStack armor : sp.inventory.armorInventory) {
					if (armor != null && (armor.getItem() instanceof RadiationArmor)) {
						full = true;
					} else {
						full = false;
						break;
					}
				}
				if (full)
					return;
			}

			base.addPotionEffect(new PotionEffect(Potion.poison.getId(), 120, 3, false, false));
			base.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 120, 2, false, false));
		}
		super.onLanded(world, entity);
	}

}
