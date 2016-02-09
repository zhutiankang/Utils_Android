package com.focustech.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;

/**********************************************************
 * @�ļ����ƣ�NetworkUtils.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��8��19�� ����2:50:55
 * @�ļ����������繤����
 * @�޸���ʷ��2014��8��19�մ�����ʼ�汾
 **********************************************************/
public class NetworkUtils
{
	public static final String NET_TYPE_WIFI = "WIFI";
	public static final String NET_TYPE_MOBILE = "MOBILE";
	public static final String NET_TYPE_NO_NETWORK = "no_network";

	private Context mContext = null;

	public NetworkUtils(Context pContext)
	{
		this.mContext = pContext;
	}

	public static final String IP_DEFAULT = "0.0.0.0";

	/**
	 * �Ƿ�������
	 * @param pContext
	 * @return
	 */
	public static boolean isConnectInternet(final Context pContext)
	{
		if (pContext == null)
			return false;
		final ConnectivityManager conManager = (ConnectivityManager) pContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfo = conManager.getActiveNetworkInfo();

		if (networkInfo != null)
		{
			return networkInfo.isAvailable();
		}
		return false;
	}

	/**
	 * �Ƿ�����wifi
	 * @param pContext
	 * @return
	 */
	public static boolean isConnectWifi(final Context pContext)
	{
		ConnectivityManager mConnectivity = (ConnectivityManager) pContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = mConnectivity.getActiveNetworkInfo();
		// �ж������������ͣ�ֻ����3G��wifi�����һЩ���ݸ��¡�
		int netType = -1;
		if (info != null)
		{
			netType = info.getType();
		}
		if (netType == ConnectivityManager.TYPE_WIFI)
		{
			return info.isConnected();
		}
		else
		{
			return false;
		}
	}

	public static String getNetTypeName(final int pNetType)
	{
		switch (pNetType)
		{
		case 0:
			return "unknown";
		case 1:
			return "GPRS";
		case 2:
			return "EDGE";
		case 3:
			return "UMTS";
		case 4:
			return "CDMA: Either IS95A or IS95B";
		case 5:
			return "EVDO revision 0";
		case 6:
			return "EVDO revision A";
		case 7:
			return "1xRTT";
		case 8:
			return "HSDPA";
		case 9:
			return "HSUPA";
		case 10:
			return "HSPA";
		case 11:
			return "iDen";
		case 12:
			return "EVDO revision B";
		case 13:
			return "LTE";
		case 14:
			return "eHRPD";
		case 15:
			return "HSPA+";
		default:
			return "unknown";
		}
	}

	/**
	 * ��ȡ�豸IP��ַ
	 * @return
	 */
	public static String getIPAddress()
	{
		try
		{
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
			{
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
				{
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (VERSION.SDK_INT <= 10)
					{
						if (!inetAddress.isLoopbackAddress())
						{
							return inetAddress.getHostAddress().toString();
						}
					}
					else
					{
						if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address))
						{
							return inetAddress.getHostAddress().toString();
						}
					}
				}
			}
			return IP_DEFAULT;
		}
		catch (final SocketException e)
		{
			return IP_DEFAULT;
		}
	}

	public String getConnTypeName()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null)
		{
			return NET_TYPE_NO_NETWORK;
		}
		else
		{
			return networkInfo.getTypeName();
		}
	}
}
