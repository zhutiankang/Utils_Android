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
package com.focustech.common.mob.update;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.focustech.common.util.LogUtil;

/**********************************************************
 * @�ļ����ƣ�UpdatePhoneStateListener.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��10��28�� ����3:14:15
 * @�ļ������������¼����ֻ�״̬
 * @�޸���ʷ��2014��10��28�մ�����ʼ�汾
 **********************************************************/
public class UpdatePhoneStateListener extends PhoneStateListener
{
	private static final String LOGTAG = UpdatePhoneStateListener.class.getSimpleName();

	private final UpdateService updateService;

	public UpdatePhoneStateListener(UpdateService updateService)
	{
		this.updateService = updateService;
	}

	@Override
	public void onDataConnectionStateChanged(int state)
	{
		super.onDataConnectionStateChanged(state);
		LogUtil.d(LOGTAG, "onDataConnectionStateChanged()...");
		LogUtil.d(LOGTAG, "Data Connection State = " + getState(state));

		if (state == TelephonyManager.DATA_CONNECTED)
		{
			updateService.startDownload();
		}
	}

	private String getState(int state)
	{
		switch (state)
		{
		case 0: // '\0'
			return "DATA_DISCONNECTED";
		case 1: // '\001'
			return "DATA_CONNECTING";
		case 2: // '\002'
			return "DATA_CONNECTED";
		case 3: // '\003'
			return "DATA_SUSPENDED";
		}
		return "DATA_<UNKNOWN>";
	}

}
