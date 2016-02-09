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

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.content.Intent;

import com.focustech.common.util.LogUtil;

/**********************************************************
 * @�ļ����ƣ�NotificationPacketListener.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��8��13�� ����2:53:40
 * @�ļ�������������Ϣ��׽����
 * @�޸���ʷ��2015��8��13�մ�����ʼ�汾
 **********************************************************/
public class NotificationPacketListener implements PacketListener
{
	private static final String LOGTAG = LogUtil.makeLogTag(NotificationPacketListener.class);

	private final XmppManager xmppManager;

	public NotificationPacketListener(XmppManager xmppManager)
	{
		this.xmppManager = xmppManager;
	}

	@Override
	public void processPacket(Packet packet)
	{
		LogUtil.d(LOGTAG, "NotificationPacketListener.processPacket()...");
		LogUtil.d(LOGTAG, "packet.toXML()=" + packet.toXML());

		/***************�˴���ǰʹ�õ���NotificationIQ***********/
		if (packet instanceof Message)
		{
			Message notification = (Message) packet;
			NotificationIQ extension = (NotificationIQ) notification.getExtension(NotificationIQ.NAME_SPACE);

			LogUtil.e("NotificationPacketListener.NotificationIQ", "NotificationIQ=" + extension);

			Intent intent = new Intent(XmppConstants.ACTION_SHOW_NOTIFICATION);
			intent.putExtra(XmppConstants.NOTIFICATION_ID, extension.getmId());
			intent.putExtra(XmppConstants.NOTIFICATION_API_KEY, extension.getProd());
			intent.putExtra(XmppConstants.NOTIFICATION_TITLE, notification.getSubject());
			intent.putExtra(XmppConstants.NOTIFICATION_MESSAGE, notification.getBody());
			intent.putExtra(XmppConstants.NOTIFICATION_LINK, extension.getLink());
			intent.putExtra(XmppConstants.NOTIFICATION_PARAM, extension.getParam());

			xmppManager.getContext().sendBroadcast(intent);
		}
	}

	// �Զ�����Ϣ��ʽ
	// <message id="00130-36" to="352621063363441@openfire.wfeature.com" from="admin@openfire.wfeature.com/Smack">
	// <subject>haiyangtest2015.8.3-1</subject>
	// <body>haiyangtest2015.8.3-1</body>
	// <msg-param xmlns="com.focustech.mob.message">
	// <mId>240</mId>
	// <param>www.made-in-china.com</param>
	// <link>8</link>
	// <prod>supplier</prod>
	// </msg-param>
	// </message>

}
