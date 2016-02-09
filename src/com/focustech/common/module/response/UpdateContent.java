package com.focustech.common.module.response;

/**********************************************************
 * @�ļ����ƣ�UpdateContent.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��10��23�� ����1:38:05
 * @�ļ�����������������ģ��
 * @�޸���ʷ��2014��10��23�մ�����ʼ�汾
 **********************************************************/
public class UpdateContent
{
	/**
	 * �Ƿ��и���
	 */
	public String updateOrNot;
	/**
	 * ���°汾����
	 */
	public String remarksUpdate;
	/**
	 * ��װ����С
	 */
	public String contentLength;
	/**
	 * ��װ�����ص�ַ
	 */
	public String upgradeUrl;
	/**
	 * ǿ�Ƹ��±�ʾ(1:ǿ�ƣ�2:��ǿ��)
	 */
	public String maxType;

	public String versionInfo;

	public boolean isNewVersion()
	{
		return "1".equals(updateOrNot);
	}

	public boolean isForceUpdate()
	{
		return "1".equals(maxType);
	}
}
