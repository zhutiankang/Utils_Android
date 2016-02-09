package com.focustech.common.widget.circulatebanner;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;

/**********************************************************
 * @�ļ����ƣ�CBPageChangeListener.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��9��11�� ����11:39:04
 * @�ļ���������ҳָʾ��������
 * @�޸���ʷ��2015��9��11�մ�����ʼ�汾
 **********************************************************/
public class CBPageChangeListener implements ViewPager.OnPageChangeListener
{
	private ArrayList<ImageView> pointViews;
	private int[] page_indicatorId;

	public CBPageChangeListener(ArrayList<ImageView> pointViews, int page_indicatorId[])
	{
		this.pointViews = pointViews;
		this.page_indicatorId = page_indicatorId;
	}

	@Override
	public void onPageScrollStateChanged(int arg0)
	{

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{

	}

	@Override
	public void onPageSelected(int index)
	{
		for (int i = 0; i < pointViews.size(); i++)
		{
			pointViews.get(index).setImageResource(page_indicatorId[1]);
			if (index != i)
			{
				pointViews.get(i).setImageResource(page_indicatorId[0]);
			}
		}
	}
}
