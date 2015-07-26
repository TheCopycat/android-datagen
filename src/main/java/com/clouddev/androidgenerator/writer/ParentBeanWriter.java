package com.clouddev.androidgenerator.writer;

import java.io.File;

import com.clouddev.androidgenerator.model.Database;

public class ParentBeanWriter extends ParentWriter {

	public ParentBeanWriter(Database database) {
		super(database);
	}

	@Override
	protected String getTemplate() {
		String template = "ParentBean.java.ftl";
		System.out.println("Reading template from file : "+template);
		return template;
	}

	@Override
	protected String getOutputFile() {
		String output = database.getPackageName().replace('.', File.separatorChar)+
				File.separator+DATA_PATH+
				File.separator+BEAN_PATH+
				File.separator+"ParentBean"+FILE_EXTENSION;
		System.out.println("Output file to : "+output);
		return output;
	}

}
