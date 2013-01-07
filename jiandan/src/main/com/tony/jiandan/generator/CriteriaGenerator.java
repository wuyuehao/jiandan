package com.tony.jiandan.generator;

public class CriteriaGenerator extends GenericGenerator{

	@Override
	protected String getTemplateFile() {
		return "ESP-Criteria.ftl";
	}

	@Override
	protected String getSubPkg() {
		return "generated/criteria";
	}

	@Override
	protected String getFileSuffix() {
		return "Criteria.java";
	}

}
