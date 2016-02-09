package com.focustech.common.module;

/**********************************************************
 * @文件名称：BaseEvent.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年9月14日 下午5:18:35
 * @文件描述：用户行为数据模型
 * @修改历史：2015年9月14日创建初始版本
 **********************************************************/
public class BaseEvent
{
	/**
	 * 该常量字段表示一个页面事件
	 */
	public static final int PAGE_EVENT_TYPE = 1;
	/***
	 * 该常量字段表示一个点击事件
	 */
	public static final int CLICK_EVENT_TYPE = 2;
	/***
	 * 表示该事件的id编号，该编号在数据库中是递增的
	 */
	private int id;
	/** 事件名称 */
	private String eventName;
	/**
	 * 事件发生的时间
	 */
	private String eventTime;
	/***
	 * 事件类型
	 */
	private int eventType;
	/**
	 * 获得事件的类型
	 * 
	 * @return
	 */
	private String params;

	public int getEventType()
	{
		return eventType;
	}

	public void setEventType(int eventType)
	{
		this.eventType = eventType;
	}

	public String getEventTime()
	{
		return eventTime;
	}

	public void setEventTime(String eventTime)
	{
		this.eventTime = eventTime;
	}

	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getParams()
	{
		return params;
	}

	public void setParams(String params)
	{
		this.params = params;
	}
}
