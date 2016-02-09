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

import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.provider.PacketExtensionProvider;
import org.xmlpull.v1.XmlPullParser;

/** 
 * This class parses incoming IQ packets to NotificationIQ objects.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class NotificationIQProvider implements PacketExtensionProvider
{

	public NotificationIQProvider()
	{
	}

	@Override
	public PacketExtension parseExtension(XmlPullParser parser) throws Exception
	{
		boolean done = false;
		NotificationIQ notification = new NotificationIQ();
		while (!done)
		{
			int eventType = parser.next();

			String name = parser.getName();
			// XML Tab±Í«©
			if (eventType == XmlPullParser.START_TAG)
			{
				if ("mId".equals(name))
				{
					notification.setmId(parser.nextText());
				}
				if ("link".equals(name))
				{
					notification.setLink(parser.nextText());
				}
				if ("prod".equals(name))
				{
					notification.setProd(parser.nextText());
				}
				if ("param".equals(name))
				{
					notification.setParam(parser.nextText());
				}
			}
			if (eventType == XmlPullParser.END_TAG)
			{
				if ("msg-param".equals(name))
				{
					done = true;
				}
			}
		}
		return notification;
	}

}
