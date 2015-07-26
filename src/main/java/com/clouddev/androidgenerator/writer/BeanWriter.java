package com.clouddev.androidgenerator.writer;

import java.io.File;

import com.clouddev.androidgenerator.model.Database;
import com.clouddev.androidgenerator.model.Table;

public class BeanWriter extends ParentWriter {

	private Table table;
	
	public BeanWriter(Database database, Table table) {
		super(database);
		this.table = table;
		datas.put("table", this.table);
	}
	
	@Override
	protected String getTemplate() {
		String template = "Bean.java.ftl";
		System.out.println("Reading template from file : "+template);
		return template;
	}

	@Override
	protected String getOutputFile() {
		String output = database.getPackageName().replace('.', File.separatorChar)+
				File.separator+DATA_PATH+
				File.separator+BEAN_PATH+
				File.separator+table.getTableBean()+FILE_EXTENSION;
		System.out.println("Output file to : "+output);
		return output;
	}

}
