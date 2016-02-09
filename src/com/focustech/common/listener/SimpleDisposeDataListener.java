package com.focustech.common.listener;

/**********************************************************
 * @�ļ����ƣ�SimpleDisposeDataListener.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��7��29�� ����2:52:48
 * @�ļ�������ʵ���������ݻص�����
 * @�޸���ʷ��2015��7��29�մ�����ʼ�汾
 **********************************************************/
public class SimpleDisposeDataListener implements DisposeDataListener
{
	/**
	 * ����ɹ�
	 * @param obj
	 */
	@Override
	public void onSuccess(Object obj)
	{

	}

	/**
	 * �����쳣
	 * @param anomalyMsg
	 */
	@Override
	public void onDataAnomaly(Object anomalyMsg)
	{
		onFailure(anomalyMsg);
	}

	/**
	 * �����쳣
	 * @param anomalyMsg
	 */
	@Override
	public void onNetworkAnomaly(Object anomalyMsg)
	{
		onFailure(anomalyMsg);
	}

	/**
	 * �쳣��������
	 * @param failedMsg
	 */
	public void onFailure(Object failedMsg)
	{

	}

}
