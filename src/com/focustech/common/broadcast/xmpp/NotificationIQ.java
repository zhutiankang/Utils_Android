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

/** 
 * This class represents a notifcatin IQ packet.
 *
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public class NotificationIQ implements PacketExtension
{
	public static final String NAME = "msg-param";
	public static final String NAME_SPACE = "com.focustech.mob.message";
	private String mId;

	private String link;

	private String prod;

	private String param;

	public NotificationIQ()
	{
	}

	public String getmId()
	{
		return mId;
	}

	public void setmId(String mId)
	{
		this.mId = mId;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getProd()
	{
		return prod;
	}

	public void setProd(String prod)
	{
		this.prod = prod;
	}

	public String getParam()
	{
		return param;
	}

	public void setParam(String param)
	{
		this.param = param;
	}

	@Override
	public String getElementName()
	{
		return NAME;
	}

	@Override
	public String getNamespace()
	{
		return NAME_SPACE;
	}

	@Override
	public String toXML()
	{
		StringBuilder buf = new StringBuilder();
		buf.append("<").append("msg-param").append(" xmlns=\"").append("com.focustech.mob.message").append("\">");
		if (mId != null)
		{
			buf.append("<mId>").append(mId).append("</mId>");
		}
		if (link != null)
		{
			buf.append("<link>").append(link).append("</link>");
		}
		if (prod != null)
		{
			buf.append("<prod>").append(prod).append("</prod>");
		}
		if (param != null)
		{
			buf.append("<param>").append(param).append("</param>");
		}
		buf.append("</").append("msg-param").append("> ");
		return buf.toString();
	}

}
