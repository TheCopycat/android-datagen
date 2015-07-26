package com.clouddev.androidgenerator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class Root {
	private List<Database> databases = new ArrayList<Database>();

	@XmlElement(name = "database")
	public List<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(List<Database> databases) {
		this.databases = databases;
	}
	
}
