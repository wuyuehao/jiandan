package com.tony.jiandan.generator;


public class DaoGenerator extends GenericGenerator {

	@Override
	public String getTemplateFile() {
		return "Dao.ftl";
	}

	@Override
	public String getSubPkg() {
		return "generated/dao";
	}

	@Override
	public String getFileSuffix() {
		return "Dao.java";
	}

}
