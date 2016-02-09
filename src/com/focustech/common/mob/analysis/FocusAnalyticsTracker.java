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
 * @�ļ����ƣ�FocusAnalyticsTracker.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��6��18�� ����3:17:52
 * @�ļ���������Ϊ�ռ�������
 * @�޸���ʷ��2015��6��18�մ�����ʼ�汾
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
	 * �������ϵ��û���Ϊ���浽���ݿ���
	 * 
	 * @param event һ����Ϊ����
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
	 * @param context ��ʾһ�������Ķ���
	 * @param productName ��Ʒm����
	 * @param productChannel ����
	 * @throws Exception
	 */
	public void sendEvent()
	{
		// �����ݿ��л��event����
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
	 * ����һ����̬�������÷������Խ��¼����������json��ʾ������
	 * @param events һϵ�е��¼�����
	 * @return ����һ��json����
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
	 * ����һ����̬���������Խ������ͱ�ʾʱ��ת���ַ��� "yyyy-MM-dd HH:mm:dd" ���͵����ݱ�ʾ
	 * @param longTime һ���ַ�����ʾ�ĳ���������
	 * @return 
	 */
	public String dataFormart(String longTime)
	{
		Date date = new Date(Long.parseLong(longTime));
		SimpleDateFormat simpleDateFromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		return simpleDateFromat.format(date);
	}

	/**
	 * ������Ϊ�ռ�����
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
	 * ֹͣ��Ϊ�ռ�����
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
	 * �ռ�һ����Ϊ
	 * @param context �����Ķ���
	 * @param eventName ��Ϊ���ƣ� ���硰��¼��
	 */
	public void trackEvent(String eventName)
	{
		BaseEvent event = new BaseEvent();
		event.setEventName(eventName);
		event.setEventTime(Calendar.getInstance().getTimeInMillis() + "");
		saveEvent(event);
	}

	/***
	 * �ռ�һ����Ϊ
	 * @param context �����Ķ��� 
	 * @param eventName ��Ϊ���ƣ� ���硰��¼��
	 * @param params ��Ϊ�ĸ������������� "�û���"
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
	 * ����豸�����еĹ̼��汾
	 * @return
	 */
	private String getDeviceFirmwareVersion()
	{
		String[] versionInfo = MobileUtil.getVersion();
		// ����ֻ�RAM��С
		if (versionInfo != null)
		{
			// ����豸�����еĹ̼��汾
			return versionInfo[1];
		}
		return "";
	}

}
