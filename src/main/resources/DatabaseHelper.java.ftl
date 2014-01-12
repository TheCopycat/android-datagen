package ${package}.${datapath};

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

<#list tables as table>
import ${package}.${datapath}.${beanpath}.${table.getTableBean()};
</#list>

public class ${database.className}DatabaseHelper extends SQLiteOpenHelper
{

	public static final String DATABASE_NAME = "${database.name}Database";

	public static final int DATABASE_VERSION = 1;

	<#list tables as table>
	public static final String ${table.getTableConstant()} = "${table.name}";
	
	<#list table.columns as column>
	public static final String ${column.getColumnConst()} = "${column.name}";
	</#list>
	
	<#list table.columns as column>
	public static final int ${column.getColumnPosConst()} = ${column.position};
	</#list>
	
	</#list>

	public ${database.className}DatabaseHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		<#list tables as table>
		db.execSQL("CREATE TABLE " + ${table.getTableConstant()} + " ("
		<#list table.columns as column>
			+ ${column.getColumnConst()} + " ${column.type}, "
		</#list>	
			+ " );");
		
		</#list>
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		<#list tables as table>
		db.execSQL("drop table if exists " + ${table.getTableConstant()});
		</#list>
		onCreate(db);
	}

	public String getTableName(Class <?> beanClass)
	{
		<#list tables as table>
		if (${table.getTableBean()}.class == beanClass)
			return ${table.getTableConstant()};
		</#list>
		return "";
	}


}
