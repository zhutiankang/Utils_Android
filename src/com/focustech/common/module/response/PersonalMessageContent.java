package com.focustech.common.module.response;

import java.util.ArrayList;

/**********************************************************
 * @文件名称：PersonalMessageContent.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年8月6日 下午4:49:21
 * @文件描述：未读消息数量和设置项状态
 * @修改历史：2015年8月6日创建初始版本
 **********************************************************/
public class PersonalMessageContent
{
	/**
	 * 消息设置总开关
	 * 1:打开
	 */
	public String isReceive;
	public ArrayList<PersonalMessageStatus> message;
}
