package net.hycrafthd.umod.ext;

import java.util.ArrayList;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

public class ExtensionList {

	private static ArrayList<Extension> ex = new ArrayList<Extension>();
	
	public static void onStart(FMLPreInitializationEvent evt){
		onInit();
		for(Extension e : ex){
			for(ModContainer c : Loader.instance().getModList()){
				if(c.getModId().equals(e.getId())){
					e.init();
					e.getExtension().preinit(evt);
					break;
				}
			}
		}
	}
	
	public static void onInit(FMLInitializationEvent ev){
		for(Extension e : ex){
				if(e.isLoaded()){
					e.getExtension().init(ev);
				}
		}
	}
	
	public static void onPostInit(FMLPostInitializationEvent ev){
		for(Extension e : ex){
			if(e.isLoaded()){
				e.getExtension().postinit(ev);
			}
		}
	}
	
	public static void onClientProxy(){
		for(Extension e : ex){
			if(e.isLoaded()){
				e.getExtension().clientRegistery();
			}
	    }
	}
	
	public static void onServer(FMLServerStartingEvent ev){
		for(Extension e : ex){
			if(e.isLoaded()){
				e.getExtension().serverstarting(ev);
			}
	    }
	}
	
	private static void onInit(){
//		ex.add(new Extension("Divex", "cdm", DiveceModExtension.class));
	}
	
}
