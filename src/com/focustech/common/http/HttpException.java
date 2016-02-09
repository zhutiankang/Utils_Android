package com.focustech.common.http;

import android.text.TextUtils;

/**********************************************************
 * @�ļ����ƣ�HttpException.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��5��13�� ����5:29:51
 * @�ļ����������������쳣ö��
 * @�޸���ʷ��2015��5��13�մ�����ʼ�汾
 **********************************************************/
public enum HttpException
{
	SocketTimeoutException("SocketTimeoutException"), ConnectException("ConnectException"), SocketException(
			"SocketException"), UnknownHostException("UnknownHostException"), InterruptedException(
			"InterruptedException"), UnknownError("UnknownError");

	private String value;

	private HttpException(String value)
	{
		this.value = value;
	}

	public static HttpException getValueByTag(String type)
	{
		if (TextUtils.isEmpty(type))
		{
			return UnknownHostException;
		}
		for (HttpException status : HttpException.values())
		{
			if (status.value.equals(type))
			{
				return status;
			}
		}
		return UnknownHostException;
	}

	@Override
	public String toString()
	{
		return value;
	}
}
