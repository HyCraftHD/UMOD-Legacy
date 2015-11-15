package net.hycrafthd.umod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.common.collect.ImmutableList;

import net.hycrafthd.umod.api.PulverizerRecepie;
import net.hycrafthd.umod.block.BlockPipe;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
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
	
	private static void  registerBlockPipe(Block pipe,String text){		
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
	    File theFile = new File(file.getPath() + "/blockpipereg.json");
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
				wr.write(s+""+theString+""+s+": {"+s+ "model"+s+":"+s+ "factorymod:"+  pipe.getUnlocalizedName().replace("tile.", "") + "_" + i  +s+" },");
				wr.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		     UMod.log.info("Generate BlockState:" + pipe.getUnlocalizedName().replace("tile.", "") + "-" + theString);
		}
		try {
			wr.write(s+"normal:{"+s+"model"+s+":"+s+"factorymod:"+pipe.getUnlocalizedName().replace("tile.", "") + "_0"+s+"}}}");
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
		for(int i = 0;i < hallo.size();i++){
			StringBuilder build = new StringBuilder();
			build.append(Block.blockRegistry.getNameForObject(pipe));
			String theString = ((BlockState.StateImplementation)hallo.get(i)).toString().substring(build.length() + 1, ((BlockState.StateImplementation)hallo.get(i)).toString().length() - 1);
	    	output.mkdir();
		    File theFile = new File(output.getPath() + "/" + pipe.getUnlocalizedName().replace("tile.", "") + "_" + i + ".json");
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
	        float anfangunten = 4.8F;
	        float anfnagoben = 11.2F;
	        float anfangX = 4.8F;
	        float endeX = 11.2F;
	        float anfangZ = 4.8F;
	        float endeZ = 11.2F;
	        
	        if(cup){
	        	anfnagoben = 16F;
	        }
	        if(cdown){
	        	anfangunten = 0F;
	        }
	        if(cwest){
	        	anfangX = 0F;
	        }
	        if(ceast){
	        	endeX = 16F;
	        }
	        if(cnorth){
	        	anfangZ = 0F;
	        }
	        if(csouth){
	        	endeZ = 16F;
	        }		
	        String s = String.valueOf('"');
	        try {
				@SuppressWarnings("resource")
				FileWriter wr = new FileWriter(theFile);
				wr.write("{"+s+"textures" +s+ ": {"+s+"-1"+s+ ":" +s+texture+s+","+s+"particle"+s+":"+s+texture+s+"},");
				wr.write(" " + s + "elements" + s + ": [{"+ s + "name" + s + ": "+s+"Cube"+s+","+s+"from"+s+": [ " + anfangX + ", "+anfangunten+", "+anfangZ+" ]," + s +"to"+s+": [ "+endeX+", "+anfnagoben+", "+endeZ+" ]," +s+
            "faces" + s+  ": {"+s+ "north" +s+": {" +s+ "texture"+s+":"+s+ "#-1" +s+", "+s+"uv" +s+ ": [ 0.0, 0.0, 16.0, 16.0 ] },"
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
		     UMod.log.info("Generate BlockJson:" + pipe.getUnlocalizedName().replace("tile.", "") + "-" + theString);
			
		}
		
	}
}
