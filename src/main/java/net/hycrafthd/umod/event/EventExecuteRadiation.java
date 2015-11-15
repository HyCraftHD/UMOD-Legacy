package net.hycrafthd.umod.event;

import java.util.HashMap;

import net.hycrafthd.umod.UDamageSource;
import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.armor.ArmorRadiation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventExecuteRadiation {

	private HashMap<EntityPlayer, Long> timer = new HashMap<EntityPlayer, Long>();

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
					if (System.currentTimeMillis() - timer.get(sp) >= 1000 * 8) {
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

				if (sp.worldObj.rand.nextInt(200) == 0) {
					sp.getFoodStats().setFoodLevel(sp.getFoodStats().getFoodLevel() - 1);
				}
			}
		}
	}

}
