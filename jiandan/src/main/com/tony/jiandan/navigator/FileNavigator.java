package com.tony.jiandan.navigator;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileNavigator {

	Logger log = LoggerFactory.getLogger(FileNavigator.class);

	private FileFilter filter = new FileFilter() {

		@Override
		public boolean accept(File arg0) {
			return true;
		}

	};
	
	private boolean includeDirectory = false;
	
	public void isIncludeDirectory(boolean includeDirectory){
		this.includeDirectory = includeDirectory;
	}
	

	public void setFilter(FileFilter filter) {
		this.filter = filter;
	}

	private ArrayList<File> list = new ArrayList<File>();

	private void getFiles(File baseDir) {
		for (File file : baseDir.listFiles(filter)) {
			log.debug("found :" + file);
			if (file.isDirectory()) {
				if(includeDirectory){
					list.add(file);
				}
				getFiles(file);
			} else {
				list.add(file);
			}
		}
	}

	public File[] getQulifiedFileList(File baseDir) {
		list.clear();
		getFiles(baseDir);
		log.debug("Get files:" + list.size());
		return list.toArray(new File[] {});
	}
	

}
