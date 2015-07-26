package com.clouddev.androidgenerator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "database")
public class Database extends Element {
	
	protected String packageName;
	
	protected List<Table> tables = new ArrayList<Table>();
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public void setName(String name) {
		super.setName(name);
		//DATABASE_NAME = name;
	}
	
	public void addTable(Table table) {
		tables.add(table);
	}
	
	@XmlElement(name="table")
	public List<Table> getTables() {
		return tables;
	}
	
	@Override
	public String getPrefix() {
		return "DATABASE";
	}
	
	@XmlAttribute(name = "package")
	public String getPackageName() {
		return packageName;
	}
}
