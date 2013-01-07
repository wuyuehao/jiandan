package com.tony.jiandan.util;

import java.io.File;
import java.util.Arrays;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompilerUtil {
	
	static Logger log = LoggerFactory.getLogger(CompilerUtil.class);

	public static void compile(String workingDir, File[] files) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnostics, null, null);
		String[] opts = new String[] { "-d", workingDir };
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(files);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				diagnostics, Arrays.asList(opts), null, compilationUnits);

		if (!task.call()) {
			for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
				// log.error("Code: " + diagnostic.getCode() + "\nKind: "
				// + diagnostic.getKind() + "\nPosition: "
				// + diagnostic.getPosition()
				// + "\nStart Position: "
				// + diagnostic.getStartPosition()
				// + "\nEnd Position: "
				// + diagnostic.getEndPosition() + "\nSource: "
				// + diagnostic.getSource() + "\nMessage: "
				// + diagnostic.getMessage(null));
				log.info("Failed to Compile : " + diagnostic.getMessage(null));
			}
			return;
		}
	}

}
