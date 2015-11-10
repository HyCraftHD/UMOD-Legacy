package net.hycrafthd.umod.event;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RayEvent {

	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event){
		EntityLivingBase base = event.entityLiving;
		
		World world = base.worldObj;
		
		double x = base.posX;
		double y = base.posY;
		double z = base.posZ;
		
		double xRange = 3;
		double yRange = 3;
		double zRange = 3;
		
		for(double xPos = x - xRange; xPos <=x + xRange; xPos++)
		for(double yPos = y - yRange; yPos <=y + yRange; yPos++)
		for(double zPos = z - zRange; zPos <=z + zRange; zPos++){
			if(world.isRemote) continue;
			BlockPos pos = new BlockPos(xPos, yPos, zPos);
			IBlockState blockcks = world.getBlockState(pos);
			Block block = blockcks.getBlock();
			EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(block.getMetaFromState(blockcks));
			if (type.getName() == "uran") {
				base.addPotionEffect(new PotionEffect(Potion.poison.getId(), 120, 2, false, false));
				base.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 120, 2, false, false));
			}
		}
	}
	
}
