package ${package}.${datapath};

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
	
	private static final String TAG = DATABASE_NAME+"-helper";

	private SQLiteDatabase database;
	
	public SQLiteDatabase getDatabase() {
		try {
			if (database == null) {
				database = getWritableDatabase();
			}
		} catch (SQLiteException sqle) {
			Log.w(TAG,"could not get database on first attempt because "+sqle.getMessage()+". Retrying");
			database = getWritableDatabase();
		}
		return database;
		
	}
	
	private static ${database.className}DatabaseHelper _instance;
	
	public static void init(Context context) throws InstanceAlreadyExistsException {
		if (null != _instance) {
			throw new InstanceAlreadyExistsException("Instance is already declared, you must call 'getInstance'.");
		}
		_instance = new ${database.className}DatabaseHelper(context.getApplicationContext());
		
	}
	
	public static ${database.className}DatabaseHelper getInstance() throws InstanceNotFoundException {
		if (null == _instance) {
			throw new InstanceNotFoundException("Instance is not declared. Did you forget to call 'init' first ?"); 
		}
		return _instance;
	}
	
	public static ${database.className}DatabaseHelper getInstance(Context context) {
		if (_instance == null) {
			_instance = new ${database.className}DatabaseHelper(context.getApplicationContext());
		}
		return _instance;
	}

	private ${database.className}DatabaseHelper(Context context)
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
