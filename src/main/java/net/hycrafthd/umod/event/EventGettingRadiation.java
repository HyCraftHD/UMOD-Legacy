package net.hycrafthd.umod.event;

import java.util.Random;

import net.hycrafthd.umod.UBiome;
import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.armor.ArmorRadiation;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.hycrafthd.umod.utils.GenerationUtils;
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

public class EventGettingRadiation {

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

		if (GenerationUtils.getBiomeGenForCoords(world, base.getPosition(), UBiome.infestedBiomBase)) {
			addPotion(base, 0);
		}

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
					if (!GenerationUtils.getBiomeGenForCoords(world, pos, UBiome.infestedBiomBase)) {
						if (block == UBlocks.ores) {
							EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(block.getMetaFromState(blockcks));
							if (type.getName() == "uran") {
								if (new Random().nextInt(50) == 0) {
									addPotion(base, 1);
								}
							}
						} else if (block instanceof IInfectedBlock) {
							if (new Random().nextInt(200) == 0) {
								addPotion(base, 0);
							}
						}
					}
					continue;
				}
			}
		}
	}

	private void addPotion(EntityLivingBase base, int amplifier) {
		if (base instanceof EntityPlayer) {
			EntityPlayer sp = (EntityPlayer) base;
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
				base.addPotionEffect(new PotionEffect(UPotion.radiationPotion.getId(), 10, amplifier, false, false));
				return;
			}
		}
		base.addPotionEffect(new PotionEffect(UPotion.radiationPotion.getId(), 10, amplifier, false, true));
	}
}
