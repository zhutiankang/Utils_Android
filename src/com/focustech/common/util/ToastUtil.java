package com.focustech.common.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**********************************************************
 * @�ļ����ƣ�ToastUtil.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��10��22�� ����11:16:49
 * @�ļ����������ѹ�����
 * @�޸���ʷ��2014��10��22�մ�����ʼ�汾
 **********************************************************/
public class ToastUtil
{
	public static void toast(Context context, Object obj)
	{
		if (context != null && obj != null)
		{
			Toast.makeText(context, obj.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	public static void toast(Context context, int resId)
	{
		if (context != null)
		{
			toast(context, context.getString(resId));
		}
	}

	public static void toastInparentCenter(Context context, Object obj)
	{
		if (context != null && obj != null)
		{
			Toast toast = Toast.makeText(context, obj.toString(), Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}

	public static void toastInparentCenter(Context context, int resId)
	{
		if (context != null)
		{
			toastInparentCenter(context, context.getString(resId));
		}
	}
}
