package com.focustech.common;

import android.content.Context;

/**********************************************************
 * @文件名称：CommonConfigurationHelper.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年9月14日 下午3:42:48
 * @文件描述：公共模块配置项工具类
 * @修改历史：2015年9月14日创建初始版本
 **********************************************************/
public class CommonConfigurationHelper
{
	private static final String CONFIG_NOT_INIT = "FocusCommon must be init in application with configuration before using";
	private static final String CONTEXT_NOT_INIT = "FocusCommon Context must not null,please check it";
	private static final String PRODUCT_NAME_NOT_INIT = "FocusCommon productName must not null,please init";

	private CommonConfiguration config;
	private volatile static CommonConfigurationHelper instance = null;

	protected CommonConfigurationHelper()
	{

	}

	public static CommonConfigurationHelper getInstance()
	{
		if (instance == null)
		{
			synchronized (CommonConfigurationHelper.class)
			{
				if (instance == null)
				{
					instance = new CommonConfigurationHelper();
				}
			}
		}
		return instance;
	}

	public synchronized void init(CommonConfiguration configuration)
	{
		if (configuration == null)
		{
			throw new IllegalArgumentException();
		}
		if (this.config == null)
		{
			this.config = configuration;
		}
	}

	public Context getContext()
	{
		return config.context;
	}

	public String getReLoginAction()
	{
		return config.reLoginAction;
	}

	public String getProductName()
	{
		return config.productName;
	}

	public String getProductChannel()
	{
		return config.productChannel;
	}

	public String getPushWay()
	{
		return config.pushway;
	}

	public String getUserPkId()
	{
		return config.userPkId;
	}

	public boolean isInited()
	{
		return config != null;
	}

	public void checkConfiguration()
	{
		if (config == null)
		{
			throw new IllegalStateException(CONFIG_NOT_INIT);
		}
		if (config.context == null)
		{
			throw new IllegalStateException(CONTEXT_NOT_INIT);
		}
		if (config.productName == null)
		{
			throw new IllegalStateException(PRODUCT_NAME_NOT_INIT);
		}
	}
}
