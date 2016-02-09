package com.focustech.common.module.response;

/**********************************************************
 * @文件名称：PersonalMessageStatus.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年8月6日 下午4:50:03
 * @文件描述：设置项状态和推送消息未读数量
 * @修改历史：2015年8月6日创建初始版本
 **********************************************************/
public class PersonalMessageStatus
{
	/**
	 * 消息类型
	 * 1:询盘消息
	 * 2:采购消息
	 * 3:服务消息
	 */
	public String pushType;
	/**
	 * 消息开关
	 * 1:打开
	 */
	public String pushState;
	public String unreadNum;
}
