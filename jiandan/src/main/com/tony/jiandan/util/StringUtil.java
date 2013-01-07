package com.tony.jiandan.util;

public class StringUtil {
	
	public static String toCamelCase(String s){
		if(s == null){
			return "Null";
		}
		return Character.toUpperCase(s.charAt(0)) + s.substring(1);
	}

}
