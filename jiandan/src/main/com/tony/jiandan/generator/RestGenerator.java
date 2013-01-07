package com.tony.jiandan.generator;


public class RestGenerator extends GenericGenerator{

	@Override
	public String getTemplateFile() {
		return "RestService.ftl";
	}

	@Override
	public String getSubPkg() {
		return "generated/services";
	}

	@Override
	public String getFileSuffix() {
		return "Service.java";
	}

}
