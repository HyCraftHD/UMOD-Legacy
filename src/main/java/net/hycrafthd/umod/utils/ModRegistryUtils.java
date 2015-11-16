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
	
	public static void  registerBlockPipe(Block pipe,String text){		
		ImmutableList hallo = ((BlockPipe)pipe).getState().getValidStates();
		generateJsons(hallo, (BlockPipe) pipe,text);
		generateBlockState(hallo, (BlockPipe) pipe);
	}
	
    private static FileWriter wr;
	
	private static void generateBlockState(ImmutableList hallo,BlockPipe pipe){
		File file =  new File(System.getProperty("user.dir") + "/__PIPEBASES");
		  if(file.exists()){
		    	if(!file.delete()){
		    		UMod.log.error("Das Programm kann auf den Angegebenen Pfad Nicht zu greifen");
		    	}
		  }
		  file.mkdir();
	    File theFile = new File(file.getPath() + "/"+pipe.getUnlocalizedName().replace("tile.", "") + ".json");
	    try {
			theFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			wr = new FileWriter(theFile);
	        String s = String.valueOf('"');
			wr.write("{"+s+
					 "variants" +s+": {");
				    wr.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
        String s = String.valueOf('"');
		for(int i = 0;i < hallo.size();i++){
			StringBuilder build = new StringBuilder();
			build.append(Block.blockRegistry.getNameForObject(pipe));
			String theString = ((BlockState.StateImplementation)hallo.get(i)).toString().substring(build.length() + 1, ((BlockState.StateImplementation)hallo.get(i)).toString().length() - 1);
			try {
				wr.write(s+""+theString+""+s+": {"+s+ "model"+s+":"+s+ UReference.resource+  pipe.getUnlocalizedName().replace("tile.", "") + "/" + pipe.getUnlocalizedName().replace("tile.", "") + "_" + i  +s+" },");
				wr.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		     UMod.log.info("Generate BlockState:" + pipe.getUnlocalizedName().replace("tile.", "") + "-" + theString);
		}
		try {
			wr.write(s+"normal"+s+":{"+s+"model"+s+":"+s+UReference.resource+pipe.getUnlocalizedName().replace("tile.", "") + "_0"+s+"}}}");
			wr.flush();
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void generateJsons(ImmutableList hallo,BlockPipe pipe,String texture){
		File output = new File(System.getProperty("user.dir") + "/__PIPEGENS");
	    if(output.exists()){
	    	if(!output.delete()){
	    		UMod.log.error("Das Programm kann auf den Angegebenen Pfad Nicht zu greifen");
	    	}
	    }
	    File opt = new File(output.getPath() + "/" + pipe.getUnlocalizedName().replace("tile.", ""));
	    boolean b = opt.mkdirs();
	    while(b){
	    b = opt.mkdir();
	    }
		for(int i = 0;i < hallo.size();i++){
			StringBuilder build = new StringBuilder();
			build.append(Block.blockRegistry.getNameForObject(pipe));
			String theString = ((BlockState.StateImplementation)hallo.get(i)).toString().substring(build.length() + 1, ((BlockState.StateImplementation)hallo.get(i)).toString().length() - 1);
	    	output.mkdir();
		    File theFile = new File(opt.getPath() + "/" + pipe.getUnlocalizedName().replace("tile.", "") + "_" + i + ".json");
            try {
				theFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    String[] splittet = theString.split(",");
		    
			boolean csouth = Boolean.parseBoolean(splittet[3].split("=")[1]);
			boolean cnorth = Boolean.parseBoolean(splittet[2].split("=")[1]);
			boolean cdown = Boolean.parseBoolean(splittet[0].split("=")[1]);
			boolean cup = Boolean.parseBoolean(splittet[4].split("=")[1]);
			boolean ceast = Boolean.parseBoolean(splittet[1].split("=")[1]);
			boolean cwest = Boolean.parseBoolean(splittet[5].split("=")[1]);
	        float anfangunten = 6.4F;
	        float anfnagoben = 9.6F;
	        float anfangX = 6.4F;
	        float endeX = 9.6F;
	        float anfangZ = 6.4F;
	        float endeZ = 9.6F;
	        
	        ArrayList<SideBoolSet> bo = new ArrayList<SideBoolSet>();
	        
	        if(cup){
	        	anfnagoben = 16F;
	        	bo.add(new SideBoolSet(cup, EnumFacing.UP));
	        }
	        if(cdown){
	        	anfangunten = 0F;
	        	bo.add(new SideBoolSet(cdown, EnumFacing.DOWN));
	        }
	        if(cwest){
	        	anfangX = 0F;
	        	bo.add(new SideBoolSet(cwest, EnumFacing.WEST));
	        }
	        if(ceast){
	        	endeX = 16F;
	        	bo.add(new SideBoolSet(ceast, EnumFacing.EAST));
	        }
	        if(cnorth){
	        	anfangZ = 0F;
	        	bo.add(new SideBoolSet(cnorth, EnumFacing.NORTH));
	        }
	        if(csouth){
	        	endeZ = 16F;
	        	bo.add(new SideBoolSet(csouth, EnumFacing.SOUTH));
	        }		
	        if(bo.size() > 2){
	        	
	        }else{
	        	writeJson(theFile, texture, anfangX, anfangunten, anfangZ, endeX, anfnagoben, endeZ);
	        }
		     UMod.log.info("Generate BlockJson:" + pipe.getUnlocalizedName().replace("tile.", "") + "-" + theString);
			
		}
		
	}
	
	private static void writeJson(File theFile,String texture,double x,double y,double z,double ex,double ey,double ez){
		 String s = String.valueOf('"');
	        try {
				@SuppressWarnings("resource")
				FileWriter wr = new FileWriter(theFile);
				wr.write("{"+s+"textures" +s+ ": {"+s+"-1"+s+ ":" +s+texture+s+","+s+"particle"+s+":"+s+texture+s+"},");
				wr.write(" " + s + "elements" + s + ": [{"+ s + "name" + s + ": "+s+"Cube"+s+","+s+"from"+s+": [ " + x + ", "+y+", "+z+" ]," + s +"to"+s+": [ "+ex+", "+ey+", "+ez+" ]," +s+ 
						  "faces" + s+  ": {"
		   +s+ "north" +s+": {" +s+ "texture"+s+":"+s+ "#-1" +s+", "+s+"uv" +s+ ": [ 0.0, 0.0, 16.0, 16.0 ] },"
        +s+ "east" +s+": {" +s+ "texture"+s+":"+s+ "#-1" +s+", "+s+"uv" +s+ ": [ 0.0, 0.0, 16.0, 16.0 ] },"
        +s+ "south" +s+": {" +s+ "texture"+s+":"+s+ "#-1" +s+", "+s+"uv" +s+ ": [ 0.0, 0.0, 16.0, 16.0 ] },"
        +s+ "west" +s+": {" +s+ "texture"+s+":"+s+ "#-1" +s+", "+s+"uv" +s+ ": [ 0.0, 0.0, 16.0, 16.0 ] },"
        +s+ "up" +s+": {" +s+ "texture"+s+":"+s+ "#-1" +s+", "+s+"uv" +s+ ": [ 0.0, 0.0, 16.0, 16.0 ] },"
        +s+ "down" +s+": {" +s+ "texture"+s+":"+s+ "#-1" +s+", "+s+"uv" +s+ ": [ 0.0, 0.0, 16.0, 16.0 ] }"+
        " }}]}");
				wr.flush();
			} catch (IOException e) {
				e.printStackTrace();
		
			}
	}
}
