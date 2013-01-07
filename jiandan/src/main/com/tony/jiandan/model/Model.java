package com.tony.jiandan.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Model {
	
	public String getAttribute(String s);

	public ArrayList<?> getAttributes(String s);
	
	public void addAttribute(String s, String o);
	
	public void addAttributes(String s, ArrayList<?> array);
	
	public HashMap getMap();

	public void addAll(Map map);
}
