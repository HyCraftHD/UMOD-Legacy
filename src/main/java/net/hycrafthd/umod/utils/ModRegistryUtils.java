package net.hycrafthd.umod.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.common.collect.ImmutableList;

import net.hycrafthd.umod.UMod;
import net.hycrafthd.umod.UReference;
import net.hycrafthd.umod.api.PulverizerRecepie;
import net.hycrafthd.umod.api.SideBoolSet;
import net.hycrafthd.umod.block.BlockPipe;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class ModRegistryUtils {
	
	private static ArrayList<PulverizerRecepie> list = new ArrayList<PulverizerRecepie>();

	public static void addPulverRiecepie(PulverizerRecepie re){
		list.add(re);
	}
	
	public static ItemStack[] isRecepie(ItemStack rec){
		if(rec == null){
			return null;
		}
		for(int i = 0;i < list.size();i++){
			PulverizerRecepie re = list.get(i);
			if(re.getInput().isItemEqual(rec)){
				return new ItemStack[]{re.getOutput(),re.getSecondOutput(),re.getRandomSecondoutput()};
			}
		}
		return null;
	}
	
}
