package com.tony.jiandan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassDefiner extends URLClassLoader {
	
	public ClassDefiner(URL[] arg0) {
		super(arg0, Thread.currentThread().getContextClassLoader());
	}

	private static byte[] b = new byte[50000];

	Logger log = LoggerFactory.getLogger(ClassDefiner.class);

	public Class<?> define(File file) {

		FileInputStream in = null;
		int len = 0;
		try {
			in = new FileInputStream(file);
			len = in.read(b);
		} catch (FileNotFoundException e) {
			log.debug("FileNotFoundException :" + e.getMessage());
		} catch (IOException e) {
			log.debug("IOE :" + e.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.debug("IOE when closing :" + e.getMessage());
				}
			}
		}
		return this.defineClass(null, b, 0, len);
	}

}
