package com.focustech.common.http;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.focustech.common.CommonConfigurationHelper;
import com.focustech.common.listener.DisposeDataListener;
import com.focustech.common.util.LogUtil;
import com.loopj.android.http.JsonHttpResponseHandler;

/**********************************************************
 * @�ļ����ƣ�BaseHttpResponseHandler.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2013-12-30 ����12:00:03
 * @�ļ�������������Ϣ�ص��Ļ���
 * @�޸���ʷ��2013-12-30������ʼ�汾
 **********************************************************/
public abstract class BaseHttpResponseHandler extends JsonHttpResponseHandler
{
	protected DisposeDataListener mListener;
	protected Class<?> mClass;

	public BaseHttpResponseHandler(DisposeDataListener listener)
	{
		this.mListener = listener;
	}

	public BaseHttpResponseHandler(DisposeDataListener listener, Class<?> clz)
	{
		this.mListener = listener;
		this.mClass = clz;
	}

	public void onSuccess(JSONObject response)
	{
	}

	public void onSuccess(JSONArray response)
	{
	}

	public void onSuccess(String responseString)
	{
	}

	public void onFailure(Throwable throwable)
	{
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONObject response)
	{
		LogUtil.i("====HttpResponse====", response.toString());
		onSuccess(response);
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, JSONArray response)
	{
		LogUtil.i("====HttpResponse====", response.toString());
		onSuccess(response);
	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, String responseString)
	{
		LogUtil.i("====HttpResponse====", responseString);
		onSuccess(responseString);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
	{
		onFailure(throwable);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse)
	{
		onFailure(throwable);
	}

	@Override
	public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
	{
		onFailure(throwable);
	}

	protected String getString(int resId)
	{
		return CommonConfigurationHelper.getInstance().getContext().getString(resId);
	}
}
