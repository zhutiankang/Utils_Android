package com.focustech.common.util;

import android.util.Log;

/**********************************************************
 * @�ļ����ƣ�LogUtil.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��3��10�� ����4:54:01
 * @�ļ���������־������
 * @�޸���ʷ��2015��3��10�մ�����ʼ�汾
 **********************************************************/
public class LogUtil
{
	private static final String LOG_TAG = "PullToRefresh";
	private static boolean isOpen = true;

	public static void e(String tag, String msg)
	{
		if (isOpen)
		{
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr)
	{
		if (isOpen)
		{
			Log.e(tag, msg, tr);
		}
	}

	public static void d(String tag, String msg)
	{
		if (isOpen)
		{
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg)
	{
		if (isOpen)
		{
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg)
	{
		if (isOpen)
		{
			Log.w(tag, msg);
		}
	}

	public static void v(String tag, String msg)
	{
		if (isOpen)
		{
			Log.v(tag, msg);
		}
	}

	public static String makeLogTag(Class<?> cls)
	{
		return "Androidpn_" + cls.getSimpleName();
	}

	public static void warnDeprecation(String depreacted, String replacement)
	{
		LogUtil.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}
}
