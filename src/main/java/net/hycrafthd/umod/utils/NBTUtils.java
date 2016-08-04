package net.hycrafthd.umod.utils;

import net.hycrafthd.umod.UMod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtils {
	
	public static boolean isInfected(ItemStack itemStack, String main_Tag, String tag) {
		if (itemStack.getTagCompound() != null) {
			NBTTagCompound data = itemStack.getTagCompound().getCompoundTag(main_Tag);
			return data.getBoolean(tag);
		}
		return false;
	}
	
	public static ItemStack setInfected(ItemStack itemStack, String main_Tag, String tag, boolean value) {
		NBTTagCompound data = new NBTTagCompound();
		data.setBoolean(tag, value);
		itemStack.setTagInfo(main_Tag, data);
		return itemStack;
	}
	
	public static void removeCustomName(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");
			
			if (nbttagcompound1.hasKey("Name")) {
				nbttagcompound1.removeTag("Name");
			}
		}
	}
	
	public static final String NBTKEY = "conduitstack";
	
	public static void addStackToConduit(ItemStack stack, ItemStack stack2) {
		if (!hasStackOnCoinduit(stack)) {
			NBTTagCompound comp = new NBTTagCompound();
			stack2.writeToNBT(comp);
			stack.setTagInfo(NBTKEY, comp);
		} else {
			UMod.log.warn("The Stack(key " + NBTKEY + ") has a Conduit");
		}
	}
	
	public static boolean hasStackOnCoinduit(ItemStack stack) {
		return stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBTKEY);
	}
	
	public static ItemStack getStackFromConduit(ItemStack stack) {
		if (stack.getTagCompound() != null && stack.getTagCompound().hasKey(NBTKEY)) {
			return ItemStack.loadItemStackFromNBT(stack.getTagCompound().getCompoundTag(NBTKEY));
		}
		UMod.log.warn("Return Null as Stack(key " + NBTKEY + ") From Conduit this isn't a good thing!");
		return null;
	}
	
}
