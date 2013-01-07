package com.tony.jiandan;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tony.jiandan.model.Model;
import com.tony.jiandan.parser.ClassParser;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MainTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	Configuration cfg; 
	
	@Test
	public void testMain() throws IOException, TemplateException{
		ClassParser cp = new ClassParser();
		Model model = cp.parse(Main.class);
		
		System.out.println(MainTest.class.getResource("/template"));
		
		cfg.setClassForTemplateLoading(Main.class, "/template");
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		/* 在整个应用的生命周期中,这个工作你可以执行多次 */
		/* 获取或创建模板*/
		Template temp = cfg.getTemplate("Dao.ftl");
		/* 创建数据模型 */
		Map root = new HashMap();
		root.put("longName", model.getAttribute("longName"));
		root.put("shortName", model.getAttribute("shortName"));
		root.put("pkg", model.getAttribute("pkg"));
		/* 将模板和数据模型合并 */
		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);
		out.flush();
	}
	

}
