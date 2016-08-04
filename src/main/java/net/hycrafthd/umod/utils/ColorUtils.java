package net.hycrafthd.umod.utils;

import net.hycrafthd.corelib.util.RGBA;
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
	
	public static RGBA hexToRGBA(int color) {
		int f3 = Math.round((float) (color >> 24 & 255) / 255.0F);
		int f = Math.round((float) (color >> 16 & 255) / 255.0F);
		int f1 = Math.round((float) (color >> 8 & 255) / 255.0F);
		int f2 = Math.round((float) (color & 255) / 255.0F);
		return new RGBA(f, f1, f2, f3);
	}
	
}
