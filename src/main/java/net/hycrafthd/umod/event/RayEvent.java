package net.hycrafthd.umod.event;

import java.util.HashMap;

import net.hycrafthd.umod.UBlocks;
import net.hycrafthd.umod.UDamageSource;
import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.armor.RadiationArmor;
import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RayEvent {

	private HashMap<EntityPlayer, Long> timer = new HashMap<EntityPlayer, Long>();
	
	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event){
		EntityLivingBase base = event.entityLiving;
		
		if(base instanceof EntityPlayer){
			EntityPlayer sp = (EntityPlayer) base;
			if(sp.capabilities.isCreativeMode) return;
		}
		
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
			if(block != UBlocks.ores) continue;
			EnumTypeBaseStuff type = EnumTypeBaseStuff.byMetadata(block.getMetaFromState(blockcks));
			if (type.getName() == "uran") {
				base.addPotionEffect(new PotionEffect(UPotion.radiationPotion.getId(), 30, 1, true, true));
//				base.addPotionEffect(new PotionEffect(Potion.poison.getId(), 120, 2, false, false));
//				base.addPotionEffect(new PotionEffect(Potion.confusion.getId(), 120, 2, false, false));
			}
		}
		
	}
	
	@SubscribeEvent
	public void onUpdateEntity(LivingUpdateEvent event){
		EntityLivingBase base = event.entityLiving;
		if(base.isPotionActive(UPotion.radiationPotion)){
			
			if(base instanceof EntityPlayer){
				EntityPlayer sp = (EntityPlayer) base;
				if(sp.capabilities.isCreativeMode) return;
				boolean full = false;
				for(ItemStack armor : sp.inventory.armorInventory){
					if(armor != null && (armor.getItem() instanceof RadiationArmor)){
						full = true;
					}else{
						full = false;
						break;
					}
				}
				if(full){
					if(!timer.containsKey(sp)) timer.put(sp, System.currentTimeMillis());
					if(System.currentTimeMillis() - timer.get(sp) >= 100*1){
						for(ItemStack armor : sp.inventory.armorInventory){
							armor.damageItem(1, sp);
							if(armor.getItemDamage() == armor.getMaxDamage()) armor.setItem(Item.getItemFromBlock(Blocks.air));
						}
						timer.remove(sp);
					}
					return;
				}
			}
			
			PotionEffect effect = base.getActivePotionEffect(UPotion.radiationPotion);
			base.attackEntityFrom(UDamageSource.radiationDamageSource, effect.getAmplifier() + 1);
		}
	}
	
}
