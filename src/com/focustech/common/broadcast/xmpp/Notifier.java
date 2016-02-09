package com.focustech.common.broadcast.xmpp;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.widget.Toast;

import com.focustech.common.util.LogUtil;

/**********************************************************
 * @�ļ����ƣ�Notifier.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2013-11-25 ����03:17:23
 * @�ļ�������֪ͨ������
 * @�޸���ʷ��2013-11-25������ʼ�汾
 **********************************************************/
public class Notifier
{
	private static final String LOGTAG = LogUtil.makeLogTag(Notifier.class);

	private static final Random random = new Random(System.currentTimeMillis());

	private Context context;

	private NotificationManager notificationManager;
	private boolean isNotificationEnabled = true;
	private boolean isNotificationToastEnabled = false;
	private boolean isNotificationSoundEnabled = true;
	private boolean isNotificationVibrateEnabled = true;

	public Notifier(Context context)
	{
		this.context = context;
		this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/**
	 * ���������Ƿ����
	 * @param isNotificationEnabled
	 */
	public void setNotificationEnabled(boolean isNotificationEnabled)
	{
		this.isNotificationEnabled = isNotificationEnabled;
	}

	/**
	 * �����Ƿ񵯳���ʾ
	 * @param isNotificationToastEnabled
	 */
	public void setNotificationToastEnabled(boolean isNotificationToastEnabled)
	{
		this.isNotificationToastEnabled = isNotificationToastEnabled;
	}

	/**
	 * ���������Ƿ�������
	 * @param isNotificationSoundEnabled
	 */
	public void setNotificationSoundEnabled(boolean isNotificationSoundEnabled)
	{
		this.isNotificationSoundEnabled = isNotificationSoundEnabled;
	}

	/**
	 * ���������Ƿ���
	 * @param isNotificationVibrateEnabled
	 */
	public void setNotificationVibrateEnabled(boolean isNotificationVibrateEnabled)
	{
		this.isNotificationVibrateEnabled = isNotificationVibrateEnabled;
	}

	/**
	 * ����֪ͨ����Ϣ��
	 * @param smallIcon
	 * @param title
	 * @param body
	 * @param pendingIntent
	 */
	public void notify(int smallIcon, String title, String body, PendingIntent pendingIntent)
	{
		notify(smallIcon, -1, -1, title, body, pendingIntent);
	}

	/**
	 * ����֪ͨ����Ϣ��
	 * @param smallIcon
	 * @param largeIcon
	 * @param title
	 * @param body
	 * @param pendingIntent
	 */
	public void notify(int smallIcon, int largeIcon, String title, String body, PendingIntent pendingIntent)
	{
		notify(smallIcon, largeIcon, -1, title, body, pendingIntent);
	}

	/**
	 * ����֪ͨ����Ϣ��
	 * @param smallIcon
	 * @param largeIcon
	 * @param color
	 * @param title
	 * @param body
	 * @param pendingIntent
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public void notify(int smallIcon, int largeIcon, int color, String title, String body, PendingIntent pendingIntent)
	{
		if (isNotificationEnabled)
		{
			// Show the toast
			if (isNotificationToastEnabled)
			{
				Toast.makeText(context, body, Toast.LENGTH_LONG).show();
			}
			// Notification
			if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB)
			{
				Notification.Builder notification = new Notification.Builder(context);
				notification.setSmallIcon(smallIcon);
				if (largeIcon != -1)
				{
					notification.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), largeIcon));
				}
				if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP && color != -1)
				{
					notification.setColor(color);
				}
				notification.setContentTitle(title);
				notification.setContentText(body);
				int defaultsValue = Notification.DEFAULT_LIGHTS;
				if (isNotificationSoundEnabled)
				{
					defaultsValue |= Notification.DEFAULT_SOUND;
				}
				if (isNotificationVibrateEnabled)
				{
					defaultsValue |= Notification.DEFAULT_VIBRATE;
				}
				notification.setDefaults(defaultsValue);
				notification.setAutoCancel(true);
				notification.setWhen(System.currentTimeMillis());
				notification.setTicker(body);
				notification.setContentIntent(pendingIntent);
				notify(notification.getNotification());
			}
			else
			{

				Notification notification = new Notification();
				notification.icon = smallIcon;
				if (largeIcon != -1)
				{
					notification.largeIcon = BitmapFactory.decodeResource(context.getResources(), largeIcon);
				}
				notification.defaults = Notification.DEFAULT_LIGHTS;
				if (isNotificationSoundEnabled)
				{
					notification.defaults |= Notification.DEFAULT_SOUND;
				}
				if (isNotificationVibrateEnabled)
				{
					notification.defaults |= Notification.DEFAULT_VIBRATE;
				}
				notification.flags = Notification.FLAG_AUTO_CANCEL;
				notification.when = System.currentTimeMillis();
				notification.tickerText = body;
				notification.setLatestEventInfo(context, title, body, pendingIntent);
				notify(notification);
			}
		}
		else
		{
			LogUtil.w(LOGTAG, "Notificaitons disabled.");
		}
	}

	private void notify(Notification notification)
	{
		notificationManager.notify(random.nextInt(), notification);
	}

}
