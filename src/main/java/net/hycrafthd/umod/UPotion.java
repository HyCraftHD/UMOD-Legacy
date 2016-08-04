package net.hycrafthd.umod;

import java.lang.reflect.Field;
import java.util.*;

import net.hycrafthd.umod.potion.PotionRadiation;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class UPotion {

	public static Potion radiationPotion;
	
	public UPotion() {
		init();
	}
	
	private void init() {
		radiationPotion = new PotionRadiation();
		UMod.log.debug("Init Potions");
	}
	
	@SuppressWarnings("unchecked")
	public static int getHighestID(){
		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.getName().equals("field_180150_I")) {
		            f.setAccessible(true);
                    HashMap<ResourceLocation, Potion> potions = (HashMap<ResourceLocation, Potion>) f.get(Potion.class);
                    ArrayList<Integer> ints = new ArrayList<Integer>();
                    for(Potion potion : potions.values()){
                       ints.add(new Integer(potion.getId()));
                    }
                    for(int i = 0;i < ints.size();i++){
                      if(!ints.contains(new Integer(i))){
                    	  return i;
                      }
                    }
                    return ints.size();
				}
			} catch (Exception e) {
				UMod.log.error("Severe error, please report this to the mod author:");
				UMod.log.trace("getHighestID", e);
			}
		}
		return 0;
	}
	
}
