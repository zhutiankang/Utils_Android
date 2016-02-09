package com.focustech.common.listener;

/**********************************************************
 * @文件名称：DisposeDataListener.java
 * @文件作者：xiongjiangwei
 * @创建时间：2014年5月16日 下午3:24:37
 * @文件描述：数据处理回调
 * @修改历史：2014年5月16日创建初始版本
 **********************************************************/
public interface DisposeDataListener
{
	/**
	 * 请求成功
	 * @param obj
	 */
	public void onSuccess(Object obj);

	/**
	 * 数据异常
	 * @param anomalyMsg
	 */
	public void onDataAnomaly(Object anomalyMsg);

	/**
	 * 网络异常
	 * @param anomalyMsg
	 */
	public void onNetworkAnomaly(Object anomalyMsg);
}
