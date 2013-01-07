package com.tony.jiandan.mojo;

import java.io.File;
import java.io.FileFilter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import com.tony.jiandan.navigator.FileNavigator;
import com.tony.jiandan.util.FileUtil;

/**
 * 
 * @author wuyuehao
 * @goal clean
 * 
 */
public class CleanMojo extends AbstractMojo {

	/**
	 * The project
	 * 
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		File baseDir = project.getBasedir();
		FileNavigator nav = new FileNavigator();
		nav.isIncludeDirectory(true);
		File[] dirs = nav.getQulifiedFileList(baseDir);

		for (File dir : dirs) {
			getLog().info("found : " + dir.getPath());
			if (dir.isDirectory() && dir.getName().equals("generated")) {
				getLog().info("deleting : " + dir.getPath());
				FileUtil.deleteDirectory(dir);
			}
		}

	}

}
