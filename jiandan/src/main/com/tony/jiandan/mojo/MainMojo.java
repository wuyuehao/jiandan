package com.tony.jiandan.mojo;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import com.tony.jiandan.generator.DaoGenerator;
import com.tony.jiandan.generator.Generator;
import com.tony.jiandan.generator.RestGenerator;
import com.tony.jiandan.model.Model;
import com.tony.jiandan.navigator.FileNavigator;
import com.tony.jiandan.parser.ClassParser;

/**
 * @goal gen
 * @phase generate-sources
 */
public class MainMojo extends AbstractMojo {

	/**
	 * The set of dependencies required by the project
	 * 
	 * @parameter default-value="${project.dependencies}"
	 * @required
	 * @readonly
	 */
	private java.util.ArrayList<Dependency> dependencies;
	/**
	 * The project
	 * 
	 * @parameter default-value="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * @parameter default-value="generated.dao"
	 */
	private String subDaoPkg;

	/**
	 * @parameter default-value="generated.services"
	 */
	private String subRestPkg;

	/**
	 * @parameter default-value="codegen,MainMojo"
	 */
	private String excludes;

	/** @parameter default-value="${localRepository}" */
	private org.apache.maven.artifact.repository.ArtifactRepository localRepository;

	public void execute() throws MojoExecutionException {

		ArrayList<URL> cp = new ArrayList<URL>();
		Set<Artifact> artifacts = project.getDependencyArtifacts();

		for (Artifact artifact : artifacts) {
			URL url = null;
			try {
				url = localRepository.find(artifact).getFile().toURL();
			} catch (MalformedURLException e) {
				// e.printStackTrace();
				getLog().info(
						"failed to find artifact : " + artifact.getArtifactId());
			}
			getLog().info("got an artifact : " + url);
			cp.add(url);
		}
		ArrayList<Generator> gens = new ArrayList<Generator>();
		gens.add(new DaoGenerator());
		gens.add(new RestGenerator());
		// gens.add(new FetchGenerator());
		// gens.add(new CriteriaGenerator());

		String baseDir = project.getBasedir().getPath() + "/target/classes/";
		String targetDir = project.getBasedir().getPath() + "/src/generated/";

		final String[] ex = excludes.split(",");

		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				for (String s : ex) {
					if (f.getName().contains(s)) {
						return false;
					}
				}
				if (f.isDirectory()) {
					return true;
				} else if (f.getName().endsWith(".class")) {
					return true;
				}
				return false;
			}
		};

		FileNavigator nav = new FileNavigator();
		nav.setFilter(filter);
		File[] classes = nav.getQulifiedFileList(new File(baseDir));

		try {
			cp.add(new File(baseDir).toURL());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}

		URLClassLoader urlCL = new URLClassLoader(cp.toArray(new URL[] {}),
				Thread.currentThread().getContextClassLoader());

		URL[] urls = urlCL.getURLs();
		for (URL url : urls) {
			getLog().info("jar in CL: " + url);
		}

		for (File file : classes) {

			String longName = file.getPath().substring(baseDir.length(),
					file.getPath().length() - ".class".length());

			String className = longName.replaceAll(File.pathSeparator, "/")
					.replaceAll("/", ".");

			// ingore non-Entity classes

			if (!isJpaEntityClass(file)) {
				continue;
			}
			Class clazz = null;
			getLog().info("defining class: " + className);

			try {
				clazz = Class.forName(className, false, urlCL); // definer.loadClass(file.getName());
			} catch (ClassNotFoundException e) {
				// e.printStackTrace();
			}

			ClassParser parser = new ClassParser();

			Model model = parser.parse(clazz);
			model.addAttribute("subDaoPkg", subDaoPkg);
			model.addAttribute("subRestPkg", subRestPkg);

			for (Generator gen : gens) {
				gen.generate(
						model,
						targetDir
								+ longName.substring(0,
										longName.lastIndexOf("/")));
			}

		}
	}

	private boolean isJpaEntityClass(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			getLog().error(e.getMessage());
		}
		byte[] b = new byte[5000];
		try {
			fis.read(b);
			String s = new String(b);
			// should be ok for most cases
			if (s.contains("Ljavax/persistence/Entity")
					&& s.contains("RuntimeVisibleAnnotations")) {
				return true;
			}
		} catch (IOException e) {
			getLog().error(e.getMessage());
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					getLog().error(e.getMessage());
				}
			}
		}
		return false;
	}
}
