package com.focustech.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.widget.EditText;

/**********************************************************
 * @�ļ����ƣ�Utils.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��8��19�� ����2:59:22
 * @�ļ����������ù�����
 * @�޸���ʷ��2014��8��19�մ�����ʼ�汾
 **********************************************************/
public class Utils
{
	private Utils()
	{
	}

	/**
	 * �����Ļ���
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getWindowWidthPix(Activity context)
	{
		int ver = Build.VERSION.SDK_INT;
		Display display = context.getWindowManager().getDefaultDisplay();
		int width = 0;
		if (ver < 13)
		{
			DisplayMetrics dm = new DisplayMetrics();
			display.getMetrics(dm);
			width = dm.widthPixels;
		}
		else
		{
			Point point = new Point();
			display.getSize(point);
			width = point.x;
		}
		return width;
	}

	/**
	 * �����Ļ�߶�
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static int getWindowHeightPix(Activity context)
	{
		int ver = Build.VERSION.SDK_INT;
		Display display = context.getWindowManager().getDefaultDisplay();
		int height = 0;
		if (ver < 13)
		{
			DisplayMetrics dm = new DisplayMetrics();
			display.getMetrics(dm);
			height = dm.heightPixels;
		}
		else
		{
			Point point = new Point();
			display.getSize(point);
			height = point.y;
		}
		return height;
	}

	/**
	 * ���ص�ǰ����汾��  
	 * @param context
	 * @return
	 */
	public static String getAppVersionName(Context context)
	{
		String versionName = "1.00.00";
		try
		{
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0)
			{
				return versionName;
			}
		}
		catch (Exception e)
		{
			LogUtil.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}

	/**
	 * ���ص�ǰ����汾��  
	 * @param context
	 * @return
	 */
	public static String getAppVersionCode(Context context)
	{
		int versionCode = 1;
		try
		{
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionCode = pi.versionCode;
			return String.valueOf(versionCode);
		}
		catch (Exception e)
		{
			LogUtil.e("VersionInfo", "Exception", e);
		}
		return String.valueOf(versionCode);
	}

	/**
	 * �����������λ��
	 * @param editText
	 */
	public static void setEditTextCursorPosition(EditText editText)
	{
		CharSequence text = editText.getText();
		if (text instanceof Spannable)
		{
			Spannable spanText = (Spannable) text;
			Selection.setSelection(spanText, text.length());
		}
	}

	/**
	 * ͨ������ȡ˽�б���
	 * @param fClass
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getDeclaredField(Class<?> fClass, Object obj, String fieldName)
	{
		Field field = null;
		Object o = null;
		try
		{
			field = fClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			o = field.get(obj);
		}
		catch (Exception e)
		{
		}
		return o;
	}

	/**
	 * ���ÿؼ���Selector
	 * @param on
	 * @param off
	 * @return
	 */
	public static StateListDrawable setImageButtonState(Drawable on, Drawable off, Context context)
	{
		StateListDrawable states = new StateListDrawable();
		states.addState(new int[]
		{ android.R.attr.stateNotNeeded }, on);
		states.addState(new int[]
		{ android.R.attr.state_pressed, android.R.attr.state_enabled }, on);
		states.addState(new int[]
		{ android.R.attr.state_focused, android.R.attr.state_enabled }, off);
		states.addState(new int[]
		{ android.R.attr.state_enabled }, off);
		states.addState(new int[]
		{ -android.R.attr.state_enabled }, on);
		return states;
	}

	/**
	 * ���ÿؼ���Selector
	 * @param on
	 * @param off
	 * @return
	 */
	public static StateListDrawable setImageButtonState(int onResId, int offResId, Context context)
	{
		StateListDrawable states = new StateListDrawable();
		states.addState(new int[]
		{ android.R.attr.stateNotNeeded }, context.getResources().getDrawable(onResId));
		states.addState(new int[]
		{ android.R.attr.state_pressed, android.R.attr.state_enabled }, context.getResources().getDrawable(onResId));
		// states.addState(new int[]
		// { android.R.attr.state_focused, android.R.attr.state_enabled },
		// context.getResources().getDrawable(offResId));
		states.addState(new int[]
		{ android.R.attr.state_enabled }, context.getResources().getDrawable(offResId));
		// states.addState(new int[]
		// { -android.R.attr.state_enabled }, context.getResources().getDrawable(onResId));
		return states;
	}

	/**
	 * �޸�CheckBox��ʽ
	 * @param on
	 * @param off
	 * @param context
	 * @return
	 */
	public static StateListDrawable setCheckBoxState(int onResId, int offResId, Context context)
	{
		StateListDrawable states = new StateListDrawable();
		states.addState(new int[]
		{ android.R.attr.state_checked }, context.getResources().getDrawable(onResId));
		states.addState(new int[]
		{ -android.R.attr.state_checked }, context.getResources().getDrawable(offResId));
		return states;
	}

	/**
	 * ��ȡ�����ļ��е�����
	 * @param context
	 * @return
	 */
	public static String getAppMetaData(Context context, String metaName)
	{
		String msg = "";
		ApplicationInfo appInfo = null;
		try
		{
			appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);
		}
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		if (appInfo != null)
		{
			msg = appInfo.metaData.getString(metaName);
		}
		return msg;
	}

	/**
	 * �жϷ����Ƿ���������
	 * @param serviceName	true:������
	 * @return
	 */
	public static boolean isServiceRunning(Context context, String serviceName)
	{
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo info : am.getRunningServices(Integer.MAX_VALUE))
		{
			if (info.service.getClassName().toString().equals(serviceName))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * �ж�ֵ�Ƿ�Ϊ��
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value)
	{
		return TextUtils.isEmpty(value);
	}

	/**
	 * �ж�ֵ�Ƿ�Ϊ��
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(ArrayList<String> value)
	{
		return (value == null || (value != null && value.size() == 0)) ? true : false;
	}

	/**
	 * ���Email�Ƿ�Ϸ�
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email)
	{
		String regex = "[a-zA-Z0-9-._]{1,50}@[a-zA-Z0-9-.]{1,65}.([a-zA-Z]{2,3}|([a-zA-Z]{2,3}.[a-zA-Z]{2}))";
		return Pattern.matches(regex, email);
	}

	/**
	 * Will throw AssertionError, if expression is not true
	 *
	 * @param expression    result of your asserted condition
	 * @param failedMessage message to be included in error log
	 * @throws java.lang.AssertionError
	 */
	public static void asserts(final boolean expression, final String failedMessage)
	{
		if (!expression)
		{
			throw new AssertionError(failedMessage);
		}
	}

	/**
	 * Will throw IllegalArgumentException if provided object is null on runtime
	 *
	 * @param argument object that should be asserted as not null
	 * @param name     name of the object asserted
	 * @throws java.lang.IllegalArgumentException
	 */
	public static <T> T notNull(final T argument, final String name)
	{
		if (argument == null)
		{
			throw new IllegalArgumentException(name + " should not be null!");
		}
		return argument;
	}

	/**
	 * int������ת��Ϊdp
	 * @param context
	 * @param px
	 * @return
	 */
	public static int toDip(Context context, int px)
	{
		if (context == null)
			return px;
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, context.getResources()
				.getDisplayMetrics());
		return pageMargin;
	}

	/**
	 * float������ת��Ϊdp
	 * @param context
	 * @param px
	 * @return
	 */
	public static int toDip(Context context, float px)
	{
		if (context == null)
			return (int) px;
		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, context.getResources()
				.getDisplayMetrics());
		return pageMargin;
	}

	/**
	 * ��ȡ��ʽת��������
	 * @return
	 */
	public static Locale getLocale()
	{
		return Locale.ENGLISH;
	}

	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs)
	{

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection =
		{ column };

		try
		{
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst())
			{
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		}
		finally
		{
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	public static boolean isDownloadsDocument(Uri uri)
	{
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	public static boolean isMediaDocument(Uri uri)
	{
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	public static boolean isExternalStorageDocument(Uri uri)
	{
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	public static boolean isGooglePhotosUri(Uri uri)
	{
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}
}
