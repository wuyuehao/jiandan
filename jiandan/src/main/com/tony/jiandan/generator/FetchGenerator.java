package com.tony.jiandan.generator;

public class FetchGenerator extends GenericGenerator {

	@Override
	protected String getTemplateFile() {
		return "ESP-Fetch.ftl";
	}

	@Override
	protected String getSubPkg() {
		return "generated/fetch";
	}

	@Override
	protected String getFileSuffix() {
		return "Fetch.java";
	}

}
