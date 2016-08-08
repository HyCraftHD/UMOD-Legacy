package net.hycrafthd.umod.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class MagicCrafterRecipe {
	
	public static ArrayList<MagicCrafterRecipe> recipes = new ArrayList<MagicCrafterRecipe>();
	private int energy;
	private ItemStack input;
	private ItemStack input2;
	private ItemStack output;
	/** add exp */
	
	public MagicCrafterRecipe(int energy, ItemStack input, ItemStack input2, ItemStack output) {
		
		this.energy = energy;
		this.input = input;
		this.input2 = input2;
		this.output = output;
		
	}
	
	/** Returns a recipe if the input is a part of it*/
	public static MagicCrafterRecipe getRecipe(ItemStack input, ItemStack input2) {
		
		for(int i=0; i<recipes.size(); i++){
			
			if((recipes.get(i).input.getItem() == input2.getItem() && recipes.get(i).input2.getItem() == input.getItem() )||
					(recipes.get(i).input.getItem() == input.getItem() && recipes.get(i).input2.getItem() == input2.getItem())){
				return recipes.get(i);
			}
			
		}
		
		return null;
	}
	
	public ItemStack getOutput(){
		return this.output;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public ItemStack getInput1(){
		return this.input;
	}
	
	public ItemStack getInput2(){
		return this.input2;
	}
	
	public static void register(MagicCrafterRecipe mcr){
		
		recipes.add(mcr);
		
	}
}
