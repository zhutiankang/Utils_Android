package com.focustech.common.mob.analysis;

import java.util.ArrayList;
import java.util.List;

import com.focustech.common.module.BaseEvent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**********************************************************
 * @文件名称：EventSqliteOperate.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年8月6日 下午3:38:34
 * @文件描述：事件数据的操作类
 * @修改历史：2015年8月6日创建初始版本
 **********************************************************/
public class EventSqliteOperate extends EventSqliteHelper
{
	public EventSqliteOperate(Context context)
	{
		super(context);
	}

	/**
	 * 将event保存到数据库中
	 * 
	 * @param event
	 */
	public void saveEvent(BaseEvent event)
	{
		SQLiteDatabase db = null;
		try
		{
			db = this.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(COLUM_DESCRIPT, event.getEventName());
			values.put(COLUM_TIME, event.getEventTime());
			values.put(COLUM_TYPE, event.getEventType());
			values.put(CULUM_PARAMS, event.getParams());
			db.insert(TABLE_NAME, null, values);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (db != null)
			{
				db.close();
			}
		}
	}

	/***
	 * 删除数据库中的event
	 */
	public void delEvent(BaseEvent event)
	{
		SQLiteDatabase db = null;
		try
		{
			db = this.getWritableDatabase();
			db.delete(TABLE_NAME, "_id = ?", new String[]
			{ event.getId() + "" });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (db != null)
			{
				db.close();
			}
		}
	}

	/***
	 * 删除数据库中的event
	 */
	public void delEvent(List<BaseEvent> events)
	{
		for (BaseEvent event : events)
		{
			delEvent(event);
		}
	}

	/***
	 * 获得event 数据
	 * 
	 * @return
	 */
	public List<BaseEvent> getEvent()
	{
		List<BaseEvent> events = new ArrayList<BaseEvent>();
		Cursor cursor = null;
		SQLiteDatabase db = null;
		try
		{
			db = this.getReadableDatabase();
			cursor = db.query(TABLE_NAME, new String[]
			{ COLUM_ID, COLUM_DESCRIPT, COLUM_TIME, COLUM_TYPE, CULUM_PARAMS }, null, null, null, null, null);
			while (cursor.moveToNext())
			{
				BaseEvent event = new BaseEvent();
				event.setId(cursor.getInt(cursor.getColumnIndex(COLUM_ID)));
				event.setEventType(cursor.getInt(cursor.getColumnIndex(COLUM_TYPE)));
				event.setEventName(cursor.getString(cursor.getColumnIndex(COLUM_DESCRIPT)));
				event.setEventTime(cursor.getString(cursor.getColumnIndex(COLUM_TIME)));
				event.setParams(cursor.getString(cursor.getColumnIndex(CULUM_PARAMS)));
				events.add(event);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (cursor != null)
			{
				cursor.close();
			}
			if (db != null)
			{
				db.close();
			}
		}
		return events;
	}
}
