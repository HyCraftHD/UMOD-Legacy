package net.hycrafthd.umod.utils;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ColorUtils {

	public static int getColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3)) {
				return nbttagcompound1.getInteger("color");
			}
		}
		return 4860944;
	}

	public static void removeColor(ItemStack stack) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound != null) {
			NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

			if (nbttagcompound1.hasKey("color")) {
				nbttagcompound1.removeTag("color");
			}
		}
	}

	public static void setColor(ItemStack stack, int color) {
		NBTTagCompound nbttagcompound = stack.getTagCompound();
		if (nbttagcompound == null) {
			nbttagcompound = new NBTTagCompound();
			stack.setTagCompound(nbttagcompound);
		}
		NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

		if (!nbttagcompound.hasKey("display", 10)) {
			nbttagcompound.setTag("display", nbttagcompound1);
		}
		nbttagcompound1.setInteger("color", color);
	}

}
