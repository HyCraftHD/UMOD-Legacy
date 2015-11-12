package net.hycrafthd.umod;

import java.util.ArrayList;

import net.hycrafthd.umod.API.PulverizerRecepie;
import net.minecraft.item.ItemStack;

public class UModRegistery {
	
	private static ArrayList<PulverizerRecepie> list = new ArrayList<PulverizerRecepie>();

	public static void addPulverRiecepie(PulverizerRecepie re){
		list.add(re);
	}
	
	public static ItemStack[] isRecepie(PulverizerRecepie rec){
		for(int i = 0;i < list.size();i++){
			PulverizerRecepie re = list.get(i);
			if(re.equals(rec)){
				return new ItemStack[]{re.getOutput(),re.getSecondOutput(),re.getRandomSecondoutput()};
			}
		}
		return null;
	}
}
