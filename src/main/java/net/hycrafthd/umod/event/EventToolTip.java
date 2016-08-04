package net.hycrafthd.umod.event;

import org.lwjgl.input.Keyboard;

import net.hycrafthd.umod.block.BlockConduit;
import net.hycrafthd.umod.item.ItemEnergyDisplay;
import net.hycrafthd.umod.utils.NBTUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventToolTip {
	
	@SubscribeEvent
	public void tooltipEvent(ItemTooltipEvent event) {
		if (event.itemStack != null && !event.showAdvancedItemTooltips) {
			ItemStack itemStack = event.itemStack;
			if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(ItemEnergyDisplay.NBT_TAG)) {
				event.toolTip.add("Bounded");
			}
			if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(EventGettingRadiationInv.TAG_MAIN) && ((NBTTagCompound) itemStack.getTagCompound().getTag(EventGettingRadiationInv.TAG_MAIN)).hasKey(EventGettingRadiationInv.TAG_INFECTED)) {
				boolean flag = ((NBTTagCompound) itemStack.getTagCompound().getTag(EventGettingRadiationInv.TAG_MAIN)).getBoolean(EventGettingRadiationInv.TAG_INFECTED);
				event.toolTip.add((flag ? EnumChatFormatting.RED : EnumChatFormatting.GREEN) + "Is Infected " + flag);
			}
			if (Block.getBlockFromItem(itemStack.getItem()) instanceof BlockConduit && itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(NBTUtils.NBTKEY)) {
				ItemStack flag = NBTUtils.getStackFromConduit(itemStack);
				event.toolTip.add(flag.getDisplayName());
			}
		}
		ItemStack itemStack = event.itemStack;
		if (event.itemStack != null && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("NBTS")) {
			NBTTagCompound comp = itemStack.getTagCompound().getCompoundTag("NBTS");
			for (Object str : comp.getKeySet()) {
				event.toolTip.add((String) str);
			}
		} else if (event.itemStack != null && itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("NBTS")) {
			event.toolTip.add("Press SHIFT for more Informations");
		}
	}
	
}
