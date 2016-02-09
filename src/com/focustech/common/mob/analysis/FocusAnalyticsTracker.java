package com.focustech.common.mob.analysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;

import com.focustech.common.CommonConfigurationHelper;
import com.focustech.common.listener.SimpleDisposeDataListener;
import com.focustech.common.mob.MobRequestCenter;
import com.focustech.common.module.BaseEvent;
import com.focustech.common.util.MobileUtil;

/**********************************************************
 * @文件名称：FocusAnalyticsTracker.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年6月18日 下午3:17:52
 * @文件描述：行为收集管理类
 * @修改历史：2015年6月18日创建初始版本
 **********************************************************/
public class FocusAnalyticsTracker
{
	private static FocusAnalyticsTracker instance;
	private EventSqliteOperate eventSqliteOperate;
	private Timer timer;

	private FocusAnalyticsTracker()
	{
		eventSqliteOperate = new EventSqliteOperate(CommonConfigurationHelper.getInstance().getContext());
	}

	public static FocusAnalyticsTracker getInstances()
	{
		if (instance == null)
		{
			instance = new FocusAnalyticsTracker();
		}
		return instance;
	}

	/**
	 * 将界面上的用户行为保存到数据库中
	 * 
	 * @param event 一个行为对象
	 */
	private void saveEvent(BaseEvent event)
	{
		if (event == null)
		{
			return;
		}
		eventSqliteOperate.saveEvent(event);
	}

	/**
	 * 
	 * @param context 表示一个上下文对象
	 * @param productName 产品m名称
	 * @param productChannel 渠道
	 * @throws Exception
	 */
	public void sendEvent()
	{
		// 从数据库中获得event数据
		final List<BaseEvent> evs = eventSqliteOperate.getEvent();
		if (evs != null && evs.size() > 0)
		{
			try
			{
				String json = changeToJson(evs);
				MobRequestCenter.sendUserCollect(json, new SimpleDisposeDataListener()
				{
					@Override
					public void onSuccess(Object obj)
					{
						eventSqliteOperate.delEvent(evs);
					}
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 这是一个静态方法，该方法可以将事件对象解析成json表示的数据
	 * @param events 一系列的事件对象
	 * @return 返回一个json数据
	 * @throws Exception
	 */
	public String changeToJson(List<BaseEvent> events) throws Exception
	{
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < events.size(); i++)
		{
			BaseEvent event = events.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("eventName", event.getEventName());
			jsonObject.put("time", dataFormart(event.getEventTime()));
			jsonObject.put("param", event.getParams() == null ? "" : event.getParams());
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}

	/**
	 * 这是一个静态方法，可以将长整型表示时间转成字符串 "yyyy-MM-dd HH:mm:dd" 类型的数据表示
	 * @param longTime 一个字符串表示的长整型数据
	 * @return 
	 */
	public String dataFormart(String longTime)
	{
		Date date = new Date(Long.parseLong(longTime));
		SimpleDateFormat simpleDateFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		return simpleDateFromat.format(date);
	}

	/**
	 * 启动行为收集服务
	 * @param timeSpace
	 * @param context
	 */
	public void startAnalyticsService(int timeSpace)
	{
		if (timer == null)
		{
			timer = new Timer();
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					handler.sendEmptyMessage(1);
				}
			}, 60000, timeSpace);
		}
	}

	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			sendEvent();
		}
	};

	/**
	 * 停止行为收集服务
	 * @param context
	 */
	public void stopAnalyticsService()
	{
		if (timer != null)
		{
			timer.cancel();
			timer = null;
		}
	}

	/***
	 * 收集一个行为
	 * @param context 上下文对象
	 * @param eventName 行为名称， 例如“登录”
	 */
	public void trackEvent(String eventName)
	{
		BaseEvent event = new BaseEvent();
		event.setEventName(eventName);
		event.setEventTime(Calendar.getInstance().getTimeInMillis() + "");
		saveEvent(event);
	}

	/***
	 * 收集一个行为
	 * @param context 上下文对象 
	 * @param eventName 行为名称， 例如“登录”
	 * @param params 行为的附带参数，例如 "用户名"
	 */
	public void trackEvent(String eventName, String params)
	{
		if (params != null && params.trim().length() > 0)
		{
			params = "android " + getDeviceFirmwareVersion() + "," + params;
		}
		else
		{
			params = "android " + getDeviceFirmwareVersion();
		}
		BaseEvent event = new BaseEvent();
		event.setEventName(eventName);
		event.setEventTime(Calendar.getInstance().getTimeInMillis() + "");
		event.setParams(params);
		saveEvent(event);
	}

	/**
	 * 获得设备上运行的固件版本
	 * @return
	 */
	private String getDeviceFirmwareVersion()
	{
		String[] versionInfo = MobileUtil.getVersion();
		// 获得手机RAM大小
		if (versionInfo != null)
		{
			// 获得设备上运行的固件版本
			return versionInfo[1];
		}
		return "";
	}

}
