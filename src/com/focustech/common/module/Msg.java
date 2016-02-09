package com.focustech.common.module;

/**********************************************************
 * @�ļ����ƣ�Msg.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��9��14�� ����5:18:19
 * @�ļ������������������ģ��
 * @�޸���ʷ��2015��9��14�մ�����ʼ�汾
 **********************************************************/
public class Msg
{
	/** �ַ�����������ʾ��Ϣ���������� */
	public static String TYPE_TEXT = "1001";
	/** �ַ�����������ʾ��Ϣ��ͼƬ���� */
	public static String TYPE_IMAGE = "1002";
	/** �ַ�����������ʾ��Ϣ����Ƶ���� */
	public static String TYPE_AUDIO = "1003";
	/** �ַ�����������ʾ��Ϣ���ͳɹ� */
	public static String STATE_SENDSUCCESS = "1";
	/** �ַ�����������ʾ��Ϣδ���� */
	public static String STATE_SENDFAIL = "0";
	/** ��Ϣ��id */
	private int id;
	/** int ��������ʾ�û����͵���Ϣ */
	public static int OUT = 0;
	/** int ��������ʾ�û����ܵ���Ϣ */
	public static int IN = 1;
	/** ��Ϣ���� */
	private String msgType;
	/** ��Ϣ���� */
	private String msgContent;
	/** �Ƿ��ͳ�ȥ�ģ����ǽ��ܵ��ģ�����ǽ��յ��� IN������Ƿ��ͳ�ȥ�� OUT */
	private int out_or_in;
	/** ��Ϣ���͵�ʱ�� */
	private String time;
	/** ��Ϣ����״̬��Ĭ����δ���� */
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
