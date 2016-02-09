package com.focustech.common.util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;

public abstract class ToastUtils
{
	public static final int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;
	public static final int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;

	private static android.widget.Toast toast;
	private static Handler handler = new Handler();

	private static Runnable run = new Runnable()
	{
		public void run()
		{
			toast.cancel();
		}
	};

	private static void toast(Context ctx, CharSequence msg, int duration)
	{
		handler.removeCallbacks(run);
		// handler��duration����ֱ�Ӷ�ӦToast�ĳ���ʱ�����ڴ����Toast�ĳ�����Ӧ����ʱ��
		switch (duration)
		{
		case LENGTH_SHORT:// Toast.LENGTH_SHORTֵΪ0����Ӧ�ĳ���ʱ����Ϊ1s
			duration = 1000;
			break;
		case LENGTH_LONG:// Toast.LENGTH_LONGֵΪ1����Ӧ�ĳ���ʱ����Ϊ3s
			duration = 3000;
			break;
		default:
			break;
		}
		if (null != toast)
		{
			toast.setText(msg);
		}
		else
		{
			toast = android.widget.Toast.makeText(ctx, msg, duration);
		}
		handler.postDelayed(run, duration);
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		toast.show();
	}

	/**
	 * ����Toast
	 * 
	 * @param ctx
	 *            ����Toast��������
	 * @param msg
	 *            ����Toast������
	 */
	public static void show(Context ctx, CharSequence msg) throws NullPointerException
	{
		show(ctx, msg, LENGTH_SHORT);
	}

	/**
	 * ����Toast
	 * 
	 * @param ctx
	 *            ����Toast��������
	 * @param msg
	 *            ����Toast������
	 * @param duration
	 *            ����Toast�ĳ���ʱ��
	 */
	public static void show(Context ctx, CharSequence msg, int duration) throws NullPointerException
	{
		if (null == ctx)
		{
			throw new NullPointerException("The ctx is null!");
		}
		if (0 > duration)
		{
			duration = LENGTH_SHORT;
		}
		toast(ctx, msg, duration);
	}

	/**
	 * ����Toast
	 * 
	 * @param ctx
	 *            ����Toast��������
	 * @param msg
	 *            ����Toast�����ݵ���ԴID
	 */
	public static void show(Context ctx, int resId) throws NullPointerException
	{
		show(ctx, ctx.getResources().getString(resId), LENGTH_SHORT);
	}

	/**
	 * ����Toast
	 * 
	 * @param ctx
	 *            ����Toast��������
	 * @param msg
	 *            ����Toast�����ݵ���ԴID
	 * @param duration
	 *            ����Toast�ĳ���ʱ��
	 */
	public static void show(Context ctx, int resId, int duration) throws NullPointerException
	{
		if (null == ctx)
		{
			throw new NullPointerException("The ctx is null!");
		}
		if (0 > duration)
		{
			duration = LENGTH_SHORT;
		}
		toast(ctx, ctx.getResources().getString(resId), duration);
	}

	/**
	 * ����Toast
	 * 
	 * @param ctx
	 *            ����Toast��������
	 * @param msg
	 *            ����Toast�����ݵ���ԴID
	 */
	public static void show(Context ctx, Object resObject) throws NullPointerException
	{
		show(ctx, resObject.toString(), LENGTH_SHORT);
	}

	/**
	 * ����Toast
	 * 
	 * @param ctx
	 *            ����Toast��������
	 * @param msg
	 *            ����Toast�����ݵ���ԴID
	 * @param duration
	 *            ����Toast�ĳ���ʱ��
	 */
	public static void show(Context ctx, Object resObject, int duration) throws NullPointerException
	{
		if (null == ctx)
		{
			throw new NullPointerException("The ctx is null!");
		}
		if (0 > duration)
		{
			duration = LENGTH_SHORT;
		}
		toast(ctx, resObject.toString(), duration);
	}

	public static void cancel()
	{
		if (null != toast)
			toast.cancel();
	}

}