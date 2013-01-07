package com.tony.jiandan.generator;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tony.jiandan.model.Model;
import com.tony.jiandan.parser.ClassParser;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaoGeneratorTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	DaoGenerator gen;
	
	@Test
	public void testGenerate() throws IOException{
		this.getClass().getClassLoader();
		InputStream in = ClassLoader.getSystemResourceAsStream("jiandan.properties");
		Properties p = new Properties();
		p.load(in);
		
		Model model = new ClassParser().parse(DummyEntity.class);
		String root = p.getProperty("targetDir");
		String path = root + model.getAttribute("pkg").replace('.', '/');
		File file = gen.generate(model, path);
		assertTrue(file.exists());
	}

}
