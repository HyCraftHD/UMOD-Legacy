package net.hycrafthd.umod.event;

import java.util.List;
import java.util.Random;

import net.hycrafthd.umod.UPotion;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.armor.ArmorRadiation;
import net.hycrafthd.umod.block.BlockConduit;
import net.hycrafthd.umod.interfaces.IInfectedBlock;
import net.hycrafthd.umod.utils.NBTUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventGettingRadiationInv {

	public static final String TAG_MAIN = UReference.name;
	public static final String TAG_INFECTED = "isInfected";
	
	@SubscribeEvent
	public void onUpdate(LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer sp = (EntityPlayer) event.entityLiving;
			if (sp.capabilities.isCreativeMode)return;
			for (ItemStack is : sp.inventory.mainInventory) {
				if (is == null)continue;
				if (NBTUtils.isInfected(is, TAG_MAIN, TAG_INFECTED)) {
					if(new Random().nextInt(50) != 0) continue;
					addPotion(sp, 0);
					break;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onDrop(LivingUpdateEvent event){
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer sp = (EntityPlayer) event.entityLiving;
			World world = sp.worldObj;
			double xCoord = sp.posX;
			double yCoord = sp.posY;
			double zCoord = sp.posZ;
			List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(xCoord-10, yCoord-10, zCoord-10, xCoord+10, yCoord+10, zCoord+10));
			for(Entity entity : entities){
				if(entity instanceof EntityItem){
					BlockPos pos = entity.getPosition().add(0, -1, 0);
					IBlockState blockcks = world.getBlockState(pos);
					Block block = blockcks.getBlock();
					if(block instanceof IInfectedBlock){
						EntityItem entityItem = (EntityItem) entity;
						if(NBTUtils.isInfected(entityItem.getEntityItem(), TAG_MAIN, TAG_INFECTED)) continue;
						ItemStack updatedItem = NBTUtils.setInfected(entityItem.getEntityItem(), TAG_MAIN, TAG_INFECTED, true);
						entityItem.setEntityItemStack(updatedItem);
						System.out.println("Set");
					}
				}
			}
		}
	}
	
	@SubscribeEvent 
	public void tooltipEvent(ItemTooltipEvent event) {
			if (event.itemStack != null && !event.showAdvancedItemTooltips) {
				ItemStack itemStack = event.itemStack;
				if(itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(TAG_MAIN) && ((NBTTagCompound) itemStack.getTagCompound().getTag(TAG_MAIN)).hasKey(TAG_INFECTED)){
				boolean flag = ((NBTTagCompound) itemStack.getTagCompound().getTag(TAG_MAIN)).getBoolean(TAG_INFECTED);
				event.toolTip.add((flag ? EnumChatFormatting.RED:EnumChatFormatting.GREEN)  + "Is Infected " + flag);
				}
				if(Block.getBlockFromItem(itemStack.getItem()) instanceof BlockConduit && itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(NBTUtils.NBTKEY)){
					ItemStack flag = NBTUtils.getStackFromConduit(itemStack);
					event.toolTip.add(flag.getDisplayName());
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
