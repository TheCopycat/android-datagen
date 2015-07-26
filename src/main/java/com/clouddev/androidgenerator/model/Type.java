package com.clouddev.androidgenerator.model;

public enum Type {
	NULL("null","null"),
	INTEGER("integer","int"),
	LONG("long","long"),
	TEXT("text","String"),
	BOOLEAN("tinyint","int");
	
	private String type;
	private String javatype;
	
	private Type(String type, String javatype) {
		this.type=type;
		this.javatype=javatype;
	}
	
	public String getType() {
		return type;
	}

	public String getJavatype() {
		return javatype;
	}
	
	public String getMajJavatype() {
		return javatype.substring(0,1).toUpperCase()+javatype.substring(1);
	}

	public static Type getType(String type) {
		for (Type value : values()) {
			if (value.type.equals(type))
				return value;
		}
		return NULL;
	}
	
	
}
