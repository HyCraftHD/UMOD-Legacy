package net.hycrafthd.umod.utils;

public class EnergyUtils {

	public static String translate(double d){
		String norm = ("" + d);
		if(d >= 1000 && d < 1000000){
			return norm.replace(".0", "").substring(0, norm.length() - 5) + "K";
		}
		if(d >= 1000000 && d < 1000000000){
			if(norm.contains("E")){
				String[] strs = norm.split("E");
				int i = Integer.valueOf(strs[1]) - 5;
				if(strs[0].contains(".") && !strs[0].endsWith(".0")){
				return strs[0].replace(".", "").substring(0, i) + "M";
				}else if(strs[0].endsWith(".0")){
					String str = strs[0].replace(".", "") ;
					for(int y = 0;y <= i - 3;y++){
						str += "0";
					}
					return  str + "M";
				}
				String str = strs[0].replace(".", "") ;
				for(int y = 0;y <= i;y++){
					str += "0";
				}
				return  str + "M ";
			}
			return norm.replace(".0", "").substring(0, norm.length() - 8) + "M";
		}
		return norm;
	}
	
}
