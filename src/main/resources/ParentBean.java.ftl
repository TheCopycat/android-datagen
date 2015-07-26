package ${package}.${datapath}.${beanpath};

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public abstract class ParentBean
{

	public abstract ContentValues write(ContentValues contentValues);

	public abstract String getTableName();

	public void persist(SQLiteDatabase sqlDb)
	{
		ContentValues contentValues = write(new ContentValues());
		sqlDb.insert(getTableName(), null, contentValues);
	}

}
