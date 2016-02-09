package com.focustech.common.http;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;

import android.content.Context;
import android.text.TextUtils;

import com.focustech.common.CommonConfigurationHelper;
import com.focustech.common.util.LogUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

/**********************************************************
 * @文件名称：FocusClient.java
 * @文件作者：xiongjiangwei
 * @创建时间：2013-10-25 上午10:20:43
 * @文件描述：连接池，发送各种请求
 * @修改历史：2013-10-25创建初始版本
 **********************************************************/
public class FocusClient
{
	private static AsyncHttpClient client = new AsyncHttpClient();
	private final static String TAG = "======FocusClient======";

	private static void log(String url, RequestParams params)
	{
		LogUtil.i(TAG, url + (params != null ? "--->" + params.toString() : ""));
		CommonConfigurationHelper.getInstance().checkConfiguration();
	}

	private static void log(String url)
	{
		log(url, null);
		CommonConfigurationHelper.getInstance().checkConfiguration();
	}

	/**
	 * get请求
	 * @param url
	 * @param responseHandler
	 */
	public static void get(String url, AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.get(url, responseHandler);
	}

	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.get(url, params, responseHandler);
	}

	/**
	 * get请求
	 * @param context
	 * @param url
	 * @param responseHandler
	 */
	public static void get(Context context, String url, AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.get(context, url, responseHandler);
	}

	/**
	 * get请求
	 * @param context
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.get(context, url, params, responseHandler);
	}

	/**
	 * get请求
	 * @param context
	 * @param url
	 * @param headers
	 * @param params
	 * @param responseHandler
	 */
	public static void get(Context context, String url, Header[] headers, RequestParams params,
			AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.get(context, url, headers, params, responseHandler);
	}

	/**
	 * post请求
	 * @param url
	 * @param responseHandler
	 */
	public static void post(String url, AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.post(url, responseHandler);
	}

	/**
	 * post请求
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.post(url, params, responseHandler);
	}

	/**
	 * post请求
	 * @param context
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.post(context, url, params, responseHandler);
	}

	/**
	 * post请求
	 * @param context
	 * @param url
	 * @param entity
	 * @param contentType
	 * @param responseHandler
	 */
	public static void post(Context context, String url, HttpEntity entity, String contentType,
			AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.post(context, url, entity, contentType, responseHandler);
	}

	/**
	 * post请求
	 * @param context
	 * @param url
	 * @param headers
	 * @param params
	 * @param contentType
	 * @param responseHandler
	 */
	public static void post(Context context, String url, Header[] headers, RequestParams params, String contentType,
			AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.post(context, url, headers, params, contentType, responseHandler);
	}

	/**
	 * post请求
	 * @param context
	 * @param url
	 * @param headers
	 * @param entity
	 * @param contentType
	 * @param responseHandler
	 */
	public static void post(Context context, String url, Header[] headers, HttpEntity entity, String contentType,
			AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.post(context, url, headers, entity, contentType, responseHandler);
	}

	/**
	 * put请求
	 * @param url
	 * @param responseHandler
	 */
	public static void put(String url, AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.put(url, responseHandler);
	}

	/**
	 * put请求
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.put(url, params, responseHandler);
	}

	/**
	 * put请求
	 * @param context
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void put(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		log(url, params);
		client.put(context, url, params, responseHandler);
	}

	/**
	 * put请求
	 * @param context
	 * @param url
	 * @param entity
	 * @param contentType
	 * @param responseHandler
	 */
	public static void put(Context context, String url, HttpEntity entity, String contentType,
			AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.put(context, url, entity, contentType, responseHandler);
	}

	/**
	 * put请求
	 * @param context
	 * @param url
	 * @param headers
	 * @param entity
	 * @param contentType
	 * @param responseHandler
	 */
	public static void put(Context context, String url, Header[] headers, HttpEntity entity, String contentType,
			AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.put(context, url, headers, entity, contentType, responseHandler);
	}

	/**
	 * delete请求
	 * @param url
	 * @param responseHandler
	 */
	public static void delete(String url, AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.delete(url, responseHandler);
	}

	/**
	 * delete请求
	 * @param context
	 * @param url
	 * @param responseHandler
	 */
	public static void delete(Context context, String url, AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.delete(context, url, responseHandler);
	}

	/**
	 * delete请求
	 * @param context
	 * @param url
	 * @param headers
	 * @param responseHandler
	 */
	public static void delete(Context context, String url, Header[] headers, AsyncHttpResponseHandler responseHandler)
	{
		log(url);
		client.delete(context, url, headers, responseHandler);
	}

	/**
	 * 设置HTTP容器属性
	 * @param id
	 * @param obj
	 */
	public static void setHttpContextAttribute(String id, Object obj)
	{
		client.getHttpContext().setAttribute(id, obj);
	}

	/**
	 * 获取HTTP容器中某项属性
	 * @param id
	 * @return
	 */
	public static Object getHttpContextAttribute(String id)
	{
		return client.getHttpContext().getAttribute(id);
	}

	/**
	 * 删除HTTP容器某项属性
	 * @param id
	 */
	public static void removeHttpContextAttribute(String id)
	{
		client.getHttpContext().removeAttribute(id);
	}

	/**
	 *  设置Cookie到HTTP容器中
	 * @param cookieStore
	 */
	public static void setCookieStore(CookieStore cookieStore)
	{
		client.setCookieStore(cookieStore);
	}

	/**
	 * 删除容器中设置的Cookie
	 */
	public static void removeCookieStore()
	{
		removeHttpContextAttribute(ClientContext.COOKIE_STORE);
	}

	/**
	 * 设置超时时间(此时间会针对使用本模块的所有请求生效)
	 * @param timeout
	 */
	public static void setTimeout(int timeout)
	{
		client.setTimeout(timeout);
	}

	public static void setPersistentCookieStore(Context context)
	{
		PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
		client.setCookieStore(myCookieStore);
	}

	protected static List<Cookie> getCookie(Context context)
	{
		PersistentCookieStore cookieStore = new PersistentCookieStore(context);
		return cookieStore.getCookies();
	}

	/** 
	 * 获取标准 Cookie   
	 */
	public static String getCookieText(Context context)
	{
		List<Cookie> cookies = getCookie(context);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cookies.size(); i++)
		{
			Cookie cookie = cookies.get(i);
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			if (!TextUtils.isEmpty(cookieName) && !TextUtils.isEmpty(cookieValue))
			{
				sb.append(cookieName + "=");
				sb.append(cookieValue + ";");
			}
		}
		return sb.toString();
	}

	public static void clearCookie(Context context)
	{
		PersistentCookieStore cookieStore = new PersistentCookieStore(context);
		cookieStore.clear();
	}
}
