package net.hycrafthd.umod.uschematic;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

	public ItemStack getStack() {
		ItemStack returnstack = new ItemStack(stack.getItem(), this.getCount(), stack.getMetadata());

		if (stack.getTagCompound() != null) {
			returnstack.setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
		}

		return returnstack;
	}

	public int getWeight() {
		return weight;
	}

	private int getCount() {
		return MathHelper.getRandomIntegerInRange(new Random(), minCount, maxCount);
	}
	
	@Override
	public String toString() {
		return this.stack.toString() + " : " + this.weight;
	}

}
