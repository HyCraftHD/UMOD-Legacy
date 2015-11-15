package net.hycrafthd.umod.event;

import java.util.HashMap;

import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.UDamageSource;
import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.armor.ArmorRadiation;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventRay {

	private HashMap<EntityPlayer, Long> timer = new HashMap<EntityPlayer, Long>();

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		EntityLivingBase base = event.entityLiving;

		if (base instanceof EntityPlayer) {
			EntityPlayer sp = (EntityPlayer) base;
			if (sp.capabilities.isCreativeMode)
				return;
		}

		World world = base.worldObj;

		double x = base.posX;
		double y = base.posY;
		double z = base.posZ;

		double range = 3;

		double xRange = range;
		double yRange = range;
		double zRange = range;

		for (double xPos = x - xRange; xPos <= x + xRange; xPos++) {
			for (double yPos = y - yRange; yPos <= y + yRange; yPos++) {
				for (double zPos = z - zRange; zPos <= z + zRange; zPos++) {
					if (world.isRemote)
						continue;
					BlockPos pos = new BlockPos(xPos, yPos, zPos);
					IBlockState blockcks = world.getBlockState(pos);
					Block block = blockcks.getBlock();
					if (block == UBlocks.ores || block == UBlocks.infectedDirt || block == UBlocks.infectedGrass || block == UBlocks.infectedFruit || block == UBlocks.infectedLeave || block == UBlocks.infectedLog || block == UBlocks.infectedPlank || block == UBlocks.infectedSapling) {
						if (block == UBlocks.ores) {
							EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(block.getMetaFromState(blockcks));
							if (type.getName() == "uran") {
								addPotion(base, 1);
							}
						} else {
							addPotion(base, 0);
						}
					} else {
						continue;
					}

				}
			}
		}

	}

	private void addPotion(EntityLivingBase base, int amplifier) {
		base.addPotionEffect(new PotionEffect(UPotion.radiationPotion.getId(), 10, amplifier, false, true));
	}

	@SubscribeEvent
	public void onUpdateEntity(LivingUpdateEvent event) {
		EntityLivingBase base = event.entityLiving;
		if (base.isPotionActive(UPotion.radiationPotion)) {

			if (base instanceof EntityPlayer) {
				EntityPlayer sp = (EntityPlayer) base;
				if (sp.capabilities.isCreativeMode)
					return;
				boolean full = false;
				for (ItemStack armor : sp.inventory.armorInventory) {
					if (armor != null && (armor.getItem() instanceof ArmorRadiation)) {
						full = true;
					} else {
						full = false;
						break;
					}
				}
				if (full) {
					if (!timer.containsKey(sp))
						timer.put(sp, System.currentTimeMillis());
					if (System.currentTimeMillis() - timer.get(sp) >= 200 * 1) {
						sp.inventory.damageArmor(1);
						timer.remove(sp);
					}
					return;
				}
			}

			PotionEffect effect = base.getActivePotionEffect(UPotion.radiationPotion);
			base.attackEntityFrom(UDamageSource.radiationDamageSource, effect.getAmplifier() + 0.5F);
			if (base instanceof EntityPlayer) {
				EntityPlayer sp = (EntityPlayer) base;

				if (sp.worldObj.rand.nextInt(30) == 0) {
					sp.getFoodStats().setFoodLevel(sp.getFoodStats().getFoodLevel() - 1);
				}
			}
		}
	}

}
