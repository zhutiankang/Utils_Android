package com.focustech.common.mob.analysis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.focustech.common.util.LogUtil;

/**********************************************************
 * @�ļ����ƣ�EventSqliteHelper.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��8��6�� ����3:37:49
 * @�ļ��������¼��洢�����ݿ�
 * @�޸���ʷ��2015��8��6�մ�����ʼ�汾
 **********************************************************/
public class EventSqliteHelper extends SQLiteOpenHelper
{
	public static final String TABLE_NAME = "event";
	public static final String COLUM_ID = "_id";
	public static final String COLUM_DESCRIPT = "descript";
	public static final String COLUM_TIME = "time";
	public static final String COLUM_TYPE = "type";
	public static final String CULUM_PARAMS = "params";

	public EventSqliteHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}

	public EventSqliteHelper(Context context, String name)
	{
		super(context, name, null, 1);
	}

	public EventSqliteHelper(Context context)
	{
		super(context, "event_analysis", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String sql = "create table event(_id integer primary key autoincrement ,descript text,time text,type integer,params text)";
		LogUtil.i("tag", "������Ϊ�ռ����ݿ�....");
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}
}
