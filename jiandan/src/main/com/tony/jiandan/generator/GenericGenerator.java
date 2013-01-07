package com.tony.jiandan.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tony.jiandan.model.Model;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class GenericGenerator implements Generator {

	Logger log = LoggerFactory.getLogger(Generator.class);

	protected abstract String getTemplateFile();

	protected abstract String getSubPkg();

	protected abstract String getFileSuffix();

	@Override
	public File generate(Model model, String srcDir) {
//		InputStream in = ClassLoader
//				.getSystemResourceAsStream("jiandan.properties");
//		Properties p = new Properties();
//		try {
//			p.load(in);
//		} catch (IOException e) {
//			log.debug("Failed to read jiandan.properties");
//		} finally {
//			if (in != null) {
//				try {
//					in.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		model.addAll(p);

		Configuration cfg = ConfigurationFactory.getConfiguration();
		Template temp = null;
		try {
			temp = cfg.getTemplate(getTemplateFile());
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
		}

		final File file = new File(srcDir + "/" + getSubPkg() + "/"
				+ model.getAttribute("shortName") + getFileSuffix());
		log.info("creating:" + file);
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
