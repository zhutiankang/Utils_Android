package com.focustech.common.widget;

import java.lang.reflect.Field;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.Scroller;

/**********************************************************
 * @�ļ����ƣ�ControlDurationViewPager.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��7��25�� ����2:13:48
 * @�ļ��������ɿ��ƻ����ٶȵ�ViewPager
 * @�޸���ʷ��2014��7��25�մ�����ʼ�汾
 **********************************************************/
public class ControlDurationViewPager extends ViewPager
{
	private int mDuration = 1500;

	public ControlDurationViewPager(Context context)
	{
		super(context);
	}

	public ControlDurationViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	/**
	 * ���û����ٶ�
	 * @param duration
	 */
	public void setDuration(int duration)
	{
		this.mDuration = duration;
		resetScroller();
	}

	/**
	 * ��ȡ��ǰ�Ļ����ٶ�
	 * @return
	 */
	public int getDuration()
	{
		return mDuration;
	}

	private void resetScroller()
	{
		try
		{
			Field mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			ViewPagerScroller scroller = new ViewPagerScroller(getContext());
			mScroller.set(this, scroller);
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	private final class ViewPagerScroller extends Scroller
	{
		public ViewPagerScroller(Context context)
		{
			super(context);
		}

		@Override
		public void startScroll(int startX, int startY, int dx, int dy, int duration)
		{
			// Ignore received duration, use fixed one instead
			super.startScroll(startX, startY, dx, dy, mDuration);
		}

		@Override
		public void startScroll(int startX, int startY, int dx, int dy)
		{
			// Ignore received duration, use fixed one instead
			super.startScroll(startX, startY, dx, dy, mDuration);
		}
	}

}
