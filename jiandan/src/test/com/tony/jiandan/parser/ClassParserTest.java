package com.tony.jiandan.parser;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.persistence.Id;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { "classpath: applicationContext.xml" })
public class ClassParserTest {

	@Test
	public void testParse() {
		ClassParser cp = new ClassParser();
		ArrayList<FieldUnit> result = cp.getFields(Employee.class);
		assertEquals(3, result.size());
	}
	
	@Test
	public void testName(){
		ClassParser cp = new ClassParser();
		String result = cp.getClassName(Employee.class);
		assertEquals("com.tony.jiandan.parser.ClassParserTest.Employee", result);
	}

	private class Employee {
		private String a;

		@Id
		private Long id;

		private String name;
	}

	private class Manager extends Employee {
		private Long id;
	}
}
