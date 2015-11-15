package net.hycrafthd.umod.event;

import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventNearByInfectedBlock {

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

}
