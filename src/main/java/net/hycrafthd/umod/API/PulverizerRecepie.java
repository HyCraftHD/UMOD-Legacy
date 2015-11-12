package net.hycrafthd.umod.API;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import scala.util.Random;

public class PulverizerRecepie {
	
	private ItemStack input;
	private ItemStack output;
	private ItemStack randomoutut;

	public PulverizerRecepie(ItemStack input,ItemStack output,ItemStack randomoutut) {
		this.input = input;
		this.output = output;
		this.randomoutut = randomoutut;
	}
	
	public ItemStack getInput(){
		return input;
	}
	
	public ItemStack getOutput(){
		return output;
	}
	
	public ItemStack getRandomSecondoutput(){
		int i = new Random().nextInt(10);
		if(i > 5){
			return randomoutut;
		}
		return null;
	}
	
	public ItemStack getSecondOutput() {
		return new ItemStack(Blocks.cobblestone);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PulverizerRecepie){
			PulverizerRecepie re = (PulverizerRecepie) obj;
			if(input != null && re.getInput() != null && re.getInput().isItemEqual(this.input)){
				return true;
			}
		}
		return false;
	}
}
