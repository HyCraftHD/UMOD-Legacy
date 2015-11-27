package net.hycrafthd.umod.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import net.hycrafthd.umod.enumtype.EnumTypeChestLooting;
import net.hycrafthd.umod.uschematic.ChestLoot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ChestLootUtils {

	public static HashMap<EnumTypeChestLooting, LinkedList<ChestLoot>> chestloot = new HashMap<EnumTypeChestLooting, LinkedList<ChestLoot>>();

	public static void addStack(EnumTypeChestLooting type, ItemStack stack, int minCount, int maxCount, int weight) {
		LinkedList<ChestLoot> list;
		if (chestloot.get(type) != null) {
			list = chestloot.get(type);
		} else {
			list = new LinkedList<ChestLoot>();
		}
		ChestLoot loot = new ChestLoot(stack, minCount, maxCount, weight);

		list.add(loot);

		chestloot.put(type, list);
	}

	public static ItemStack getStack(EnumTypeChestLooting type) {
		LinkedList<ChestLoot> list;
		if (chestloot.containsKey(type)) {
			list = chestloot.get(type);
		} else {
			return null;
		}

		while (true) {

			ChestLoot loot = list.get(MathHelper.getRandomIntegerInRange(new Random(), 0, list.size() - 1));

			int weight = MathHelper.getRandomIntegerInRange(new Random(), 0, 99);

			if (weight <= loot.getWeight()) {
				return loot.getStack();
			}
		}
	}

}
