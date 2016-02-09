package com.focustech.common.module;

/**********************************************************
 * @文件名称：Msg.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年9月14日 下午5:18:19
 * @文件描述：意见反馈数据模型
 * @修改历史：2015年9月14日创建初始版本
 **********************************************************/
public class Msg
{
	/** 字符串常量，表示信息是文字类型 */
	public static String TYPE_TEXT = "1001";
	/** 字符串常量，表示信息是图片类型 */
	public static String TYPE_IMAGE = "1002";
	/** 字符串常量，表示信息是音频类型 */
	public static String TYPE_AUDIO = "1003";
	/** 字符串常量，表示信息发送成功 */
	public static String STATE_SENDSUCCESS = "1";
	/** 字符串常量，表示信息未发送 */
	public static String STATE_SENDFAIL = "0";
	/** 信息的id */
	private int id;
	/** int 常量，表示用户发送的信息 */
	public static int OUT = 0;
	/** int 常量，表示用户接受的信息 */
	public static int IN = 1;
	/** 信息类型 */
	private String msgType;
	/** 信息内容 */
	private String msgContent;
	/** 是发送出去的，还是接受到的，如果是接收到的 IN，如果是发送出去的 OUT */
	private int out_or_in;
	/** 信息发送的时间 */
	private String time;
	/** 信息发送状态，默认是未发送 */
	private String msgState = STATE_SENDFAIL;

	public Msg(String msgType, String msgContent)
	{
		super();
		this.msgType = msgType;
		this.msgContent = msgContent;
	}

	public Msg()
	{
		super();
	}

	public String getMsgType()
	{
		return msgType;
	}

	public void setMsgType(String msgType)
	{
		this.msgType = msgType;
	}

	public String getMsgContent()
	{
		return msgContent;
	}

	public void setMsgContent(String msgContent)
	{
		this.msgContent = msgContent;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String sendTime)
	{
		this.time = sendTime;
	}

	public int getOut_or_in()
	{
		return out_or_in;
	}

	public void setOut_or_in(int out_or_in)
	{
		this.out_or_in = out_or_in;
	}

	public String getMsgState()
	{
		return msgState;
	}

	public void setMsgState(String msgState)
	{
		this.msgState = msgState;
	}

	public void setMsgState(boolean isSendSuccss)
	{
		if (isSendSuccss)
		{
			this.msgState = STATE_SENDSUCCESS;
		}
		else
		{
			this.msgState = STATE_SENDFAIL;
		}
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
