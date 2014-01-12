package com.clouddev.androidgenerator.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class Table extends Element {
	
	private List<Column> columns = new ArrayList<Column>();
	
	@XmlElement(name="column")
	public void setColumn(Column column) {
		columns.add(column);
		column.setTableName(name);
		column.setPosition(columns.size()-1);
	}

	@XmlElement(name="column")
	public List<Column> getColumns() {
		return columns;
	}
	
	public String getTableConstant() {
		return getPrefix()+SEPARATOR+name.toUpperCase();
	}
	
	public String getTableBean() {
		return getClassName()+"Bean";
	}

	@Override
	public String getPrefix() {
		return "TABLE";
	}
}
