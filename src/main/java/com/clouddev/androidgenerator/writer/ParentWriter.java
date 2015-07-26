package com.clouddev.androidgenerator.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.clouddev.androidgenerator.model.Database;

import freemarker.template.Configuration;
import freemarker.template.Template;

public abstract class ParentWriter {

	protected Database database;
	protected Map<String,Object> datas = new HashMap<String,Object>();
	public static final String FILE_EXTENSION = ".java";
	public static final String DATA_PATH = "datas";
	public static final String BEAN_PATH = "beans";
	
	public ParentWriter(Database database) {
		this.database = database;
		datas.put("database",database);
		datas.put("package", database.getPackageName());
		datas.put("tables", database.getTables());
		datas.put("datapath", DATA_PATH);
		datas.put("beanpath", BEAN_PATH);
	}
	
	protected abstract String getTemplate();
	
	protected Map<String,Object> getDatas() {
		return datas;
	}
	
	protected abstract String getOutputFile();
	
	public boolean write(String outputFolder) {
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(ParentWriter.class, "/");
		try {
			Template template = cfg.getTemplate(getTemplate());
			File outputFile = new File(outputFolder+File.separator+getOutputFile());
			if (!outputFile.exists()) {
				outputFile.getParentFile().mkdirs();
				outputFile.createNewFile();
			}
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile));
			template.process(getDatas(), writer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
