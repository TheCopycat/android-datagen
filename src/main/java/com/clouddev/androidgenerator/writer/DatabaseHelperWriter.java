package com.clouddev.androidgenerator.writer;

import java.io.File;

import com.clouddev.androidgenerator.model.Database;

public class DatabaseHelperWriter extends ParentWriter {

	public DatabaseHelperWriter(Database database) {
		super(database);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTemplate() {
		String template = "DatabaseHelper.java.ftl";
		System.out.println("Reading template from file : "+template);
		return template;
	}

	@Override
	protected String getOutputFile() {
		String output = database.getPackageName().replace('.', File.separatorChar)+
				File.separator+"datas"+
				File.separator+database.getClassName()+"DatabaseHelper"+FILE_EXTENSION;
		System.out.println("Output file to : "+output);
		return output;
	}

}
