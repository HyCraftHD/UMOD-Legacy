package net.hycrafthd.umod.VIA;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.util.Vec3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VIAFile extends File{

	public String File;
	private boolean Proceed;
	/**
	 * 
	 */
	private static final long serialVersionUID = 165456468846L;
	
	@SideOnly(Side.CLIENT)
	public VIAFile(String arg0) {
		super(arg0);
		try {
			DataInputStream stream = new DataInputStream(new FileInputStream(this));
			ArrayList<Byte> ar = new ArrayList<Byte>();
			while(stream.available() > 0){
				ar.add((byte) stream.read());
			}
			byte[] bty = new byte[ar.size()];
			int i = 0;
			for(Byte b : ar){
				bty[i] = b;
				i++;
			}
			File = new String(bty);
			stream.close();
			Proceed = true;
		} catch (FileNotFoundException e) {
			Proceed = false;
		} catch (IOException e) {
			Proceed = false;
		}
	}

	public Vertex interpretVertex(int gr){
		if(!Proceed)return null;		
		return new Vertex(getPoint(gr, 0), getPoint(gr, 1), getPoint(gr, 2), getPoint(gr, 3));
	}
	
	public String[] splitGroups(){
		if(!Proceed)return null;
		String[] arrgs = File.split("VGS:");
		String ret;
		if(arrgs.length < 2){
			ret = arrgs[0];
		}else{
			ret = arrgs[1];
		}
		String[] args = ret.split(":VGE")[0].split("VG:");
		ArrayList<String> strs = new ArrayList<String>();
		for (String string : args) {
			string = string.replace(" ", "").replace(String.format("%n"), "");
			if(string != "" || string != null){
				strs.add(string);
			}
		}
		String[] retu = new String[strs.size()];
		int i = 0;
		for (String string : strs) {
			retu[i] = string;
			i++;
		}
        return args;
	}
	
	public String[] splitPoints(int vg){
		if(!Proceed)return null;
		String group = splitGroups()[vg + 1];
		String[] arr = group.trim().split("verPoint");
		ArrayList<String> strs = new ArrayList<String>();
		for (String string : arr) {
			string = string.trim();
			String ref = string.replace(")", "~").split("~")[0].replace("(", "");
			if(ref != null && !ref.isEmpty())
			strs.add(ref);
		}
		String[] retu = new String[strs.size()];
		int i = 0;
		for (String string : strs) {
			retu[i] = string;
			i++;
		}
		return retu;
	}
	
	public Vec3 getPoint(int vg,int i){
		String[] args = splitPoints(vg)[i].split(",");
		return new Vec3(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
	}
	
	public int getMaxGroups(){
		return splitGroups().length - 1;
	}
	
	public int getMaxPointsOfGroup(int gr){
		return splitPoints(gr).length - 1;
	}
}
