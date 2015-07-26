package com.clouddev.androidgenerator.model;

import javax.xml.bind.annotation.XmlAttribute;

public abstract class Element {

	public static final String SEPARATOR = "_";
	//protected static String DATABASE_NAME;
	
	protected String name;
	
	public String getClassName() {
		return name.substring(0, 1).toUpperCase()+name.substring(1);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	
	public abstract String getPrefix();
	
}
