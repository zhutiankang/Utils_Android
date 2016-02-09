package com.focustech.common.listener;

/**********************************************************
 * @文件名称：SimpleDisposeDataListener.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年7月29日 下午2:52:48
 * @文件描述：实例化的数据回调监听
 * @修改历史：2015年7月29日创建初始版本
 **********************************************************/
public class SimpleDisposeDataListener implements DisposeDataListener
{
	/**
	 * 请求成功
	 * @param obj
	 */
	@Override
	public void onSuccess(Object obj)
	{

	}

	/**
	 * 数据异常
	 * @param anomalyMsg
	 */
	@Override
	public void onDataAnomaly(Object anomalyMsg)
	{
		onFailure(anomalyMsg);
	}

	/**
	 * 网络异常
	 * @param anomalyMsg
	 */
	@Override
	public void onNetworkAnomaly(Object anomalyMsg)
	{
		onFailure(anomalyMsg);
	}

	/**
	 * 异常公共方法
	 * @param failedMsg
	 */
	public void onFailure(Object failedMsg)
	{

	}

}
