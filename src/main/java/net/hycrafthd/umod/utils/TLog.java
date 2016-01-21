package net.hycrafthd.umod.utils;

import net.hycrafthd.umod.Logger;

public class TLog {

	public static void warn(String str){
		String thread = Thread.currentThread().getName();
		if(thread == null || thread == ""){
			thread = "Thread" + Thread.currentThread().getId();
		}
		Logger.warn(thread, str);
	}
	
	public static void catchError(String str,Exception el){
		String thread = Thread.currentThread().getName();
		if(thread == null || thread == ""){
			thread = "Thread" + Thread.currentThread().getId();
		}
		Logger.error(thread, str);
		StackTraceElement[] list = el.getStackTrace();
		for(int i = 0;i < list.length;i++){
			Logger.error(list[i].getMethodName(), list[i].toString());
		}
	}

}
