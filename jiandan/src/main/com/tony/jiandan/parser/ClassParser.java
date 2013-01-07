package com.tony.jiandan.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tony.jiandan.model.Model;
import com.tony.jiandan.model.SimpleModel;
import com.tony.jiandan.util.StringUtil;


public class ClassParser {
	
	
	Logger log = LoggerFactory.getLogger(ClassParser.class);
	
	
	public Model parse(Class clazz){
		Model model = new SimpleModel();
		model.addAttributes("fields", getFields(clazz));
		
		String longName = getClassName(clazz);
		int index = longName.lastIndexOf('.');
		
		String shortName = longName.substring(index+1);
		String pkg = longName.substring(0, index);
		model.addAttribute("longName", longName);
		model.addAttribute("shortName", shortName);
		model.addAttribute("pkg", pkg);
		
		return model;
	}
	
	public ArrayList<FieldUnit> getFields(Class clazz){
		ArrayList<FieldUnit> array = new ArrayList<FieldUnit>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if(name.startsWith("this$")){
				continue;
			}
			
			FieldUnit fieldUnit = new FieldUnit();
			fieldUnit.setName(name);
			fieldUnit.setType(field.getType().getName());
			fieldUnit.setGetter("get" + StringUtil.toCamelCase(name));
			fieldUnit.setSetter("set" + StringUtil.toCamelCase(name));
			if(field.getType() == Boolean.class){
				fieldUnit.setDefaultValue("true");
			}
			array.add(fieldUnit);
			
		}
		return array;
	}
	
	
	public String getClassName(Class clazz){
		return clazz.getCanonicalName();
	}
}
