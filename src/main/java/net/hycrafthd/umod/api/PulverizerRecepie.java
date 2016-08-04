package net.hycrafthd.umod.api;

import net.hycrafthd.umod.UItems;
import net.minecraft.item.ItemStack;
import scala.util.Random;

public class PulverizerRecepie {
	
	private ItemStack input;
	private ItemStack output;
	private ItemStack randomoutut;
	private boolean randomoutput;
	
	public PulverizerRecepie(ItemStack input, ItemStack output, ItemStack randomoutut, boolean randomoutput) {
		this.input = input;
		this.output = output;
		this.randomoutut = randomoutut;
	}
	
	public ItemStack getInput() {
		return input;
	}
	
	public ItemStack getOutput() {
		return output;
	}
	
	public ItemStack getRandomSecondoutput() {
		
		if (randomoutput) {
			int i = new Random().nextInt(10);
			if (i > 5) {
				return randomoutut;
			}
			
			return null;
			
		} else {
			
			return randomoutut;
		}
		
	}
	
	public ItemStack getSecondOutput() {
		return new ItemStack(UItems.cdust);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PulverizerRecepie) {
			PulverizerRecepie re = (PulverizerRecepie) obj;
			if (input != null && re.getInput() != null && re.getInput().isItemEqual(this.input)) {
				return true;
			}
		}
		return false;
	}
}
