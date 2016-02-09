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
 * @文件名称：Utils.java
 * @文件作者：xiongjiangwei
 * @创建时间：2014年8月19日 下午2:59:22
 * @文件描述：常用工具类
 * @修改历史：2014年8月19日创建初始版本
 **********************************************************/
public class Utils
{
	private Utils()
	{
	}

	/**
	 * 获得屏幕宽度
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
	 * 获得屏幕高度
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
	 * 返回当前程序版本名  
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
	 * 返回当前程序版本号  
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
	 * 设置输入框光标位置
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
	 * 通过反射取私有变量
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
	 * 设置控件的Selector
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
	 * 设置控件的Selector
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
	 * 修改CheckBox样式
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
	 * 获取配置文件中的数据
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
	 * 判断服务是否正在运行
	 * @param serviceName	true:运行中
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
	 * 判断值是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value)
	{
		return TextUtils.isEmpty(value);
	}

	/**
	 * 判断值是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(ArrayList<String> value)
	{
		return (value == null || (value != null && value.size() == 0)) ? true : false;
	}

	/**
	 * 检查Email是否合法
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
	 * int型像素转换为dp
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
	 * float型像素转换为dp
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
	 * 获取格式转换的语言
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
