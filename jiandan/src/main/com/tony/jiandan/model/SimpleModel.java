package com.tony.jiandan.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleModel implements Model {

	private HashMap<String, String> singleAtt = new HashMap<String, String>();

	private HashMap<String, ArrayList<?>> multiAtt = new HashMap<String, ArrayList<?>>();

	@Override
	public String getAttribute(String s) {
		return singleAtt.get(s);
	}

	@Override
	public ArrayList<?> getAttributes(String s) {
		return multiAtt.get(s);
	}

	@Override
	public void addAttribute(String s, String o) {
		singleAtt.put(s, o);

	}

	@Override
	public void addAttributes(String s, ArrayList<?> array) {
		multiAtt.put(s, array);

	}

	@Override
	public HashMap getMap() {
		HashMap map = new HashMap();
		map.putAll(singleAtt);
		map.putAll(multiAtt);
		return map;
	}

	@Override
	public void addAll(Map map) {
		this.singleAtt.putAll(map);
	}

}
