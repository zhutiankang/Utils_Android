package com.focustech.common.widget.circulatebanner;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.focustech.common.widget.circulatebanner.salvage.RecyclingPagerAdapter;

public class CBPageAdapter<T> extends RecyclingPagerAdapter
{
	protected List<T> mDatas;
	protected CBViewHolderCreator<T> holderCreator;

	public CBPageAdapter(CBViewHolderCreator<T> holderCreator, List<T> datas)
	{
		this.holderCreator = holderCreator;
		this.mDatas = datas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View view, ViewGroup container)
	{
		Holder<T> holder = null;
		if (view == null)
		{
			holder = (Holder<T>) holderCreator.createHolder();
			view = holder.createView(container.getContext());
			view.setTag(holder);
		}
		else
		{
			holder = (Holder<T>) view.getTag();
		}
		if (mDatas != null && !mDatas.isEmpty())
			holder.UpdateUI(container.getContext(), position, mDatas.get(position));
		return view;
	}

	@Override
	public int getCount()
	{
		if (mDatas == null)
			return 0;
		return mDatas.size();
	}

	/**
	 * @param <T> 任何你指定的对象
	 */
	public interface Holder<T>
	{
		View createView(Context context);

		void UpdateUI(Context context, int position, T data);
	}
}
