package com.clouddev.androidgenerator.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Column extends Element {
	
	private Type type;
	private String tableName;
	private int position;
	
	
	public String getColumnConst() {
		return getPrefix()+SEPARATOR+tableName.toUpperCase()+SEPARATOR+name.toUpperCase();
	}
	
	public String getColumnPosConst() {
		return "POS"+SEPARATOR+tableName.toUpperCase()+SEPARATOR+name.toUpperCase();
	}
	
	public int getPosition() {
		return position;
	}
	
	public String getType() {
		return type.getType();
	}
	
	public String getJavatype() {
		return type.getJavatype();
	}
	
	public String getMajJavatype() {
		return type.getMajJavatype();
	}
	
	@Override
	public String getPrefix() {
		return "COL";
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@XmlAttribute(name="type")
	public void setType(String type) {
		this.type = Type.getType(type);
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
