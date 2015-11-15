package net.hycrafthd.umod.schematic;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public final class ChestLoot {

	private ItemStack stack;

	private int weight;

	private int minCount;
	private int maxCount;

	public ChestLoot(ItemStack stack, int count, int weight) {
		this(stack, count, count, weight);
	}

	public ChestLoot(ItemStack newstack, int newminCount, int newmaxCount, int newweight) {
		this.weight = newweight;
		this.minCount = newminCount;
		this.maxCount = newmaxCount;
		this.stack = newstack;
	}

	@Override
	public String toString() {
		return this.stack.toString() + " : " + this.weight;
	}

	public ItemStack getStack() {
		return stack;
	}

	public int getWeight() {
		return weight;
	}

	public int getCount() {
		return MathHelper.getRandomIntegerInRange(new Random(), minCount, maxCount);
	}

}
