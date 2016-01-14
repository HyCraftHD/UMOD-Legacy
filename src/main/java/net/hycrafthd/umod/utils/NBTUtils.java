package net.hycrafthd.umod.utils;

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

}
