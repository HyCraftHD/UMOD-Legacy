package net.hycrafthd.umod.schematic;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public final class ChestLoot {

	private ItemStack stack;

	private int weight;

	public ChestLoot(ItemStack stack, int count, int weight) {
		this(stack, count, count, weight);
	}

	public ChestLoot(ItemStack newstack, int newminCount, int newmaxCount, int newweight) {
		this.weight = newweight;
		
		ItemStack temp = newstack.copy();
		temp.stackSize = MathHelper.getRandomIntegerInRange(new Random(), newminCount, newmaxCount);
		stack = temp.copy();
		
		System.out.println(stack);
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

}
