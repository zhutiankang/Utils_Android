/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.focustech.common.broadcast.xmpp;

import com.focustech.common.CommonConfigurationHelper;

import android.content.Context;
import android.content.Intent;

/** 
 * This class is to manage the notificatin service and to load the configuration.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public final class ServiceManager
{
	private Context context;

	private Intent serviceIntent;

	public ServiceManager(Context context)
	{
		this.context = context;
	}

	public void startService()
	{
		CommonConfigurationHelper.getInstance().checkConfiguration();
		Thread serviceThread = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				serviceIntent = new Intent();
				serviceIntent.setAction(NotificationService.SERVICE_NAME);// 你定义的service的action
				serviceIntent.setPackage(context.getPackageName());// 这里你需要设置你应用的包名
				context.startService(serviceIntent);
			}
		});
		serviceThread.start();
	}

	public void stopService()
	{
		if (serviceIntent != null)
		{
			context.stopService(serviceIntent);
		}
	}
}
