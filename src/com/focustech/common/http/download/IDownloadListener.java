package com.focustech.common.http.download;

/**********************************************************
 * @�ļ����ƣ�IDownloadListener.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��5��16�� ����10:19:37
 * @�ļ����������ع����и���״̬�Ľӿ�
 * @�޸���ʷ��2014��5��16�մ�����ʼ�汾
 **********************************************************/
public interface IDownloadListener
{
	/**
	 * ��ʼ����Ļص�
	 */
	public void onStarted();

	/**
	 * ����ɹ�������ǰ��׼���ص�
	 * @param contentLength		�ļ�����
	 * @param downloadUrl		���ص�ַ
	 */
	public void onPrepared(long contentLength, String downloadUrl);

	/**
	 * �������أ����½��ȵĻص�
	 * @param progress			��ǰ���ؽ���
	 * @param completeSize		��������ɳ���
	 * @param downloadUrl		���ص�ַ
	 */
	public void onProgressChanged(int progress, String downloadUrl);

	/**
	 * ���ع�������ͣ�Ļص�
	 * @param completeSize
	 * @param downloadUrl
	 */
	public void onPaused(int progress, int completeSize, String downloadUrl);

	/**
	 * ������ɵĻص�
	 */
	public void onFinished(int completeSize, String downloadUrl);

	/**
	 * ����ʧ�ܵĻص�
	 */
	public void onFailure();
}
