package com.focustech.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**********************************************************
 * @�ļ����ƣ�ControlDurationViewPager.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��7��25�� ����2:13:48
 * @�ļ��������ɿ��ƻ����ٶȵ�ViewPager
 * @�޸���ʷ��2014��7��25�մ�����ʼ�汾
 **********************************************************/
public class WrapContentHeightViewPager extends ViewPager
{

	public WrapContentHeightViewPager(Context context)
	{
		super(context);
	}

	public WrapContentHeightViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int height = 0;
		// �����������child�ĸ߶�
		for (int i = 0; i < getChildCount(); i++)
		{
			View child = getChildAt(i);
			child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			int h = child.getMeasuredHeight();
			if (h > height) // ��������view�ĸ߶ȡ�
				height = h;
		}

		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
