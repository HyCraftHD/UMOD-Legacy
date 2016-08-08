package net.hycrafthd.umod.utils;

import java.util.ArrayList;

import net.hycrafthd.umod.api.*;
import net.minecraft.item.ItemStack;

public class ModRegistryUtils {
	
	private static ArrayList<PulverizerRecepie> list = new ArrayList<PulverizerRecepie>();
	private static ArrayList<CraftSmeltRecepie> craftlist = new ArrayList<CraftSmeltRecepie>();
	private static ArrayList<CraftSmeltRecepieShapless> craftlist2 = new ArrayList<CraftSmeltRecepieShapless>();
	
	public static void addMagicCrafterRecipe(MagicCrafterRecipe mcr){
		MagicCrafterRecipe.register(mcr);
	}
	
	public static void addPulverRiecepie(PulverizerRecepie re) {
		list.add(re);
	}
	
	public static ItemStack[] isRecepie(ItemStack rec) {
		if (rec == null) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			PulverizerRecepie re = list.get(i);
			if (re.getInput().isItemEqual(rec)) {
				return new ItemStack[] { re.getOutput(), re.getSecondOutput(), re.getRandomSecondoutput() };
			}
		}
		return null;
	}
	
	public static void addCraftSmeltRecepie(CraftSmeltRecepie re) {
		craftlist.add(re);
	}
	
	public static void addCraftSmeltRecepieShapless(CraftSmeltRecepieShapless re) {
		craftlist2.add(re);
	}
	
	public static ItemStack isCraftSmelt(ItemStack[] line1, ItemStack[] line2, ItemStack[] line3) {
		ItemStack[] all = { line1[0], line1[1], line1[2], line2[0], line2[1], line2[2], line3[0], line3[1], line3[2] };
		ItemStack stack = isShaplesCraftSmelt(all);
		if (stack != null) {
			return stack;
		}
		for (int i = 0; i < craftlist.size(); i++) {
			CraftSmeltRecepie re = craftlist.get(i);
			int treffer = 0;
			for (int j = 0; j < 3; j++) {
				if (!re.getLine1()[j].isItemEqual(line1[j])) {
					treffer++;
				}
			}
			for (int j = 0; j < 3; j++) {
				if (!re.getLine2()[j].isItemEqual(line2[j])) {
					treffer++;
				}
			}
			for (int j = 0; j < 3; j++) {
				if (!re.getLine3()[j].isItemEqual(line3[j])) {
					treffer++;
				}
			}
			if (treffer == 9) {
				return re.getOutput();
			}
		}
		return stack;
	}
	
	private static ItemStack isShaplesCraftSmelt(ItemStack[] all) {
		int treffer = 0;
		for (int i = 0; i < craftlist2.size(); i++) {
			CraftSmeltRecepieShapless re = craftlist2.get(i);
			for (int j = 0; j < all.length; j++) {
				ItemStack sta = all[j];
				for (int x = 0; x < re.getItemsRequied().length; x++) {
					if (re.getItemsRequied()[x].isItemEqual(sta)) {
						treffer++;
					}
				}
			}
			if (treffer == re.getItemsRequied().length) {
				return re.getOutput().copy();
			}
		}
		return null;
	}
}
