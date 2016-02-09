package com.focustech.common.listener;

/**********************************************************
 * @�ļ����ƣ�DisposeDataListener.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��5��16�� ����3:24:37
 * @�ļ����������ݴ���ص�
 * @�޸���ʷ��2014��5��16�մ�����ʼ�汾
 **********************************************************/
public interface DisposeDataListener
{
	/**
	 * ����ɹ�
	 * @param obj
	 */
	public void onSuccess(Object obj);

	/**
	 * �����쳣
	 * @param anomalyMsg
	 */
	public void onDataAnomaly(Object anomalyMsg);

	/**
	 * �����쳣
	 * @param anomalyMsg
	 */
	public void onNetworkAnomaly(Object anomalyMsg);
}
