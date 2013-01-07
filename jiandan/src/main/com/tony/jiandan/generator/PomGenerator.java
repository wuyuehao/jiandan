package com.tony.jiandan.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tony.jiandan.model.Model;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PomGenerator implements Generator {

	Logger log = LoggerFactory.getLogger(RestGenerator.class);

	@Override
	public File generate(Model model, String path) {
		Configuration cfg = ConfigurationFactory.getConfiguration();
		Template temp = null;
		try {
			temp = cfg.getTemplate("pom.ftl");
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}

		final File file = new File(path + "/mvn/pom.xml");
		log.debug("creating:" + file);
		if (file.exists()) {
			log.debug("File exist: " + file.getName());
		} else {
			try {
				log.debug("mkdirs: " + file.getParentFile().mkdirs());
				Writer out = new FileWriter(file);
				temp.process(model.getMap(), out);
				out.flush();
			} catch (IOException e) {
				log.error("IOE: " + e.getLocalizedMessage());
			} catch (TemplateException e) {
				log.error("Template Exception: " + e.getLocalizedMessage());
			}
		}
		return file;
	}

}
