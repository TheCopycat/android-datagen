package ${package}.${datapath}.${beanpath};

import android.content.ContentValues;
import android.database.Cursor;

import ${package}.${datapath}.${database.className}DatabaseHelper;
import ${package}.${datapath}.${beanpath}.ParentBean;

public class ${table.className}Bean extends ParentBean
{
	<#list table.columns as column>
	public ${column.javatype} ${column.name};
	</#list>
	
	<#assign columnCount = table.columns?size>
	<#assign count = 0 >
	public ${table.className}Bean(<#list table.columns as column>${column.javatype} ${column.name}<#if (count < columnCount-1) >, </#if><#assign count = count + 1 ></#list>)
	{
		<#list table.columns as column>
		this.${column.name} = ${column.name};
		</#list>
	}

	public ${table.className}Bean(Cursor cursor)
	{
		<#list table.columns as column>
		this.${column.name} = cursor.get${column.majJavatype}(cursor.getColumnIndex(${database.className}DatabaseHelper.${column.columnConst}));
		</#list>
	}

	@Override
	public ContentValues write(ContentValues contentValues)
	{
		<#list table.columns as column>
		contentValues.put(${database.className}DatabaseHelper.${column.columnConst}, ${column.name});
		</#list>
		return contentValues;
	}

	@Override
	public String getTableName()
	{
		return ${database.className}DatabaseHelper.${table.tableConstant};
	}

	@Override
	public String toString()
	{
		return ${database.className}DatabaseHelper.${table.tableConstant}+"\n"+
		"{\n"+
		<#list table.columns as column>
		"\t"+${database.className}DatabaseHelper.${column.columnConst}+" = "+${column.name}+"\n"+
		</#list>
		"}";
	}

}
