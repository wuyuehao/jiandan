package com.tony.jiandan.generator;

import java.io.File;

import com.tony.jiandan.model.Model;

public interface Generator {
	
	public File generate(Model model, String basePath);
	

}
