package com.focustech.common.module.response;

/**********************************************************
 * @文件名称：BroadCastContent.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年8月6日 下午4:48:38
 * @文件描述：单条推送消息
 * @修改历史：2015年8月6日创建初始版本
 **********************************************************/
public class BroadCastContent
{
	public String sender;
	public String sendTime;
	public String subject;
	public String messageId;
	public String messageParameter;
	public String messageLink;
	// 针对于采购消息跳转到详情部分
	public String messageLink_1;
	public String readState;
}
