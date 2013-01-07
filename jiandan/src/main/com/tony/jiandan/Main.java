package com.tony.jiandan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.tony.jiandan.generator.DaoGenerator;
import com.tony.jiandan.generator.FetchGenerator;
import com.tony.jiandan.generator.Generator;
import com.tony.jiandan.generator.PomGenerator;
import com.tony.jiandan.generator.RestGenerator;
import com.tony.jiandan.generator.SpringContextGenerator;
import com.tony.jiandan.model.Model;
import com.tony.jiandan.model.SimpleModel;

public class Main {

	static public boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	public static void main(String[] args) throws IOException {
		InputStream in = ClassLoader
				.getSystemResourceAsStream("jiandan.properties");
		Properties p = new Properties();
		p.load(in);

		String srcDir = p.getProperty("srcDir");
		String projectName = p.getProperty("projectName");
		String basePkg = p.getProperty("basePkg");
		final String targetDir = p.getProperty("targetDir");
		final String workingDir = p.getProperty("workingDir");

		// clear the target dir
		deleteDirectory(new File(targetDir));

		Model simpleModel = new SimpleModel();
		simpleModel.addAttribute("projectName", projectName);
		simpleModel.addAttribute("basePkg", basePkg);
		// generate pom
		new PomGenerator().generate(simpleModel, targetDir);

		// generate applicationContext
		new SpringContextGenerator().generate(simpleModel, targetDir);

		// generate src code
		new File(workingDir).mkdirs();

		File base = new File(srcDir);

		ArrayList<Generator> gens = new ArrayList<Generator>();
		gens.add(new DaoGenerator());
		gens.add(new RestGenerator());
		gens.add(new FetchGenerator());
		// gens.add(new CriteriaGenerator());

//		JpaFileNavigator nav = new JpaFileNavigator(gens);
//		
//		nav.setTargetDir(targetDir);
//
//		nav.setWorkingDir(targetDir);
//		
//		CompilerUtil.compile(workingDir, nav.getAllJavaFileList(base));
//
//		nav.navigate(base);
		
//		ClassDefiner definer = new ClassDefiner(new URL[]{new File(srcDir).toURL()});
//		
//		JpaClassFileNavigator nav = new JpaClassFileNavigator(gens, targetDir, definer);
//		nav.navigate(base);

	}

	
}
