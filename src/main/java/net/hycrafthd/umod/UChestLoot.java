package net.hycrafthd.umod;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import net.hycrafthd.umod.enumtype.EnumTypeBaseStuff;
import net.hycrafthd.umod.enumtype.EnumTypeChestLooting;
import net.hycrafthd.umod.schematic.ChestLoot;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class UChestLoot {

	public static HashMap<EnumTypeChestLooting, LinkedList<ChestLoot>> chestloot = new HashMap<EnumTypeChestLooting, LinkedList<ChestLoot>>();

	public UChestLoot() {
		add();
	}

	private void add() {
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.diamond), 1, 1, 5);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Blocks.obsidian), 3, 6, 10);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.iron_pickaxe), 1, 1, 8);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.iron_axe), 1, 1, 8);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.iron_sword), 1, 1, 8);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.iron_shovel), 1, 1, 8);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.stone_pickaxe), 1, 1, 15);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.stone_axe), 1, 1, 15);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.stone_sword), 1, 1, 15);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.stone_shovel), 1, 1, 15);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(UBlocks.ores, 1, EnumTypeBaseStuff.URAN.getMetadata()), 1, 3, 50);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(Items.coal), 4, 9, 30);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(UBlocks.infectedLog), 6, 14, 30);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(UBlocks.infectedPlank), 14, 19, 20);
		this.addStack(EnumTypeChestLooting.INFESTEDRUIN1, new ItemStack(UItems.infectedcrop), 4, 10, 30);

	}

	private void addStack(EnumTypeChestLooting type, ItemStack stack, int minCount, int maxCount, int weight) {
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

		ChestLoot loot = list.get(MathHelper.getRandomIntegerInRange(new Random(), 0, list.size() - 1));

		while (true) {
			int weight = MathHelper.getRandomIntegerInRange(new Random(), 0, 99);

			if (weight <= loot.getWeight()) {
				ItemStack stack = loot.getStack();
				loot.getStack().stackSize = loot.getCount();
				System.out.println(stack);
				return stack;
			}
		}
	}

}
