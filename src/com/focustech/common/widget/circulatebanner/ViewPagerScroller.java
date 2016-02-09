package com.focustech.common.widget.circulatebanner;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**********************************************************
 * @�ļ����ƣ�ViewPagerScroller.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��9��11�� ����11:39:46
 * @�ļ��������Զ���ViewPager�������ʱ��
 * @�޸���ʷ��2015��9��11�մ�����ʼ�汾
 **********************************************************/
public class ViewPagerScroller extends Scroller
{
	private int mScrollDuration = 1200;

	public ViewPagerScroller(Context context)
	{
		super(context);
	}

	public ViewPagerScroller(Context context, Interpolator interpolator)
	{
		super(context, interpolator);
	}

	public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel)
	{
		super(context, interpolator, flywheel);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration)
	{
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy)
	{
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

	public int getScrollDuration()
	{
		return mScrollDuration;
	}

	public void setScrollDuration(int scrollDuration)
	{
		this.mScrollDuration = scrollDuration;
	}

}