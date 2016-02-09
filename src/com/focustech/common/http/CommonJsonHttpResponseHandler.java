package com.focustech.common.http;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;

import com.focustech.common.CommonConfigurationHelper;
import com.focustech.common.R;
import com.focustech.common.listener.DisposeDataListener;

/**********************************************************
 * @文件名称：CommonJsonHttpResponseHandler.java
 * @文件作者：xiongjiangwei
 * @创建时间：2014年6月5日 上午9:05:01
 * @文件描述：请求返回数据处理回调
 * @修改历史：2014年6月5日创建初始版本
 **********************************************************/
public class CommonJsonHttpResponseHandler extends BaseHttpResponseHandler
{
	public CommonJsonHttpResponseHandler(DisposeDataListener listener)
	{
		super(listener);
	}

	public CommonJsonHttpResponseHandler(DisposeDataListener listener, Class<?> cls)
	{
		super(listener, cls);
	}

	@Override
	public void onSuccess(JSONObject response)
	{
		disposeResponseData(response, mListener, mClass);
	}

	@Override
	public void onFailure(Throwable throwable)
	{
		switch (HttpException.getValueByTag(throwable.getClass().getSimpleName()))
		{
		case SocketException:
		case SocketTimeoutException:
			mListener.onNetworkAnomaly(getString(R.string.request_timeout));
			break;
		case ConnectException:
		case UnknownError:
		case UnknownHostException:
			mListener.onNetworkAnomaly(getString(R.string.request_no_internet));
			break;
		case InterruptedException:
			mListener.onNetworkAnomaly(getString(R.string.request_service_interrupted));
			break;
		default:
			mListener.onDataAnomaly(getString(R.string.request_data_exception));
			break;
		}
	}

	/**
	 * 处理服务器返回的数据
	 * @param jsonObj
	 * @param listener
	 * @param cls
	 */
	public void disposeResponseData(JSONObject jsonObj, DisposeDataListener listener, Class<?> cls)
	{
		if (jsonObj == null)
		{
			listener.onDataAnomaly(getString(R.string.request_data_exception));
			return;
		}
		try
		{
			if (jsonObj.has("code"))
			{
				if ("0".equals(jsonObj.getString("code")))
				{
					if (cls == null)
					{
						listener.onSuccess(jsonObj);
					}
					else
					{
						Object obj = ResponseEntityToModule.parseJsonObjectToModule(jsonObj, cls);
						if (obj != null)
						{
							listener.onSuccess(obj);
						}
						else
						{
							listener.onDataAnomaly(getString(R.string.request_data_exception));
						}
					}
				}
				else if ("99999".equals(jsonObj.getString("code")) || "30001".equals(jsonObj.getString("code")))
				{
					if (null != CommonConfigurationHelper.getInstance().getReLoginAction())
					{
						// 发送广播通知
						Intent intent = new Intent(CommonConfigurationHelper.getInstance().getReLoginAction());
						intent.putExtra("code", Integer.parseInt(jsonObj.getString("code")));
						intent.putExtra("reLoginReason", jsonObj.has("err") ? jsonObj.getString("err")
								: getString(R.string.request_data_exception));
						CommonConfigurationHelper.getInstance().getContext().sendBroadcast(intent);
					}
				}
				else
				{
					if (jsonObj.has("err"))
					{
						listener.onDataAnomaly(jsonObj.getString("err"));
					}
					else
					{
						listener.onDataAnomaly(getString(R.string.request_data_exception));
					}
				}
			}
			else
			{
				if (jsonObj.has("err"))
				{
					listener.onDataAnomaly(jsonObj.getString("err"));
				}
				else
				{
					listener.onDataAnomaly(getString(R.string.request_data_exception));
				}
			}
		}
		catch (JSONException e)
		{
			listener.onDataAnomaly(getString(R.string.request_data_exception));
			e.printStackTrace();
		}
	}

}
