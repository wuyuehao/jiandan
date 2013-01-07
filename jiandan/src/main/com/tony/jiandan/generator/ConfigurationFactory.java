package com.tony.jiandan.generator;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public class ConfigurationFactory {

	private static Configuration cfg = null;

	public static Configuration getConfiguration() {
		if (cfg == null) {
			cfg = new Configuration();
			cfg.setClassForTemplateLoading(ConfigurationFactory.class,
					"/template");
			cfg.setObjectWrapper(new DefaultObjectWrapper());
		}
		return cfg;
	}
}
