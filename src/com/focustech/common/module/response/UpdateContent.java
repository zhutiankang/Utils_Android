package com.focustech.common.module.response;

/**********************************************************
 * @文件名称：UpdateContent.java
 * @文件作者：xiongjiangwei
 * @创建时间：2014年10月23日 下午1:38:05
 * @文件描述：检查更新数据模型
 * @修改历史：2014年10月23日创建初始版本
 **********************************************************/
public class UpdateContent
{
	/**
	 * 是否有更新
	 */
	public String updateOrNot;
	/**
	 * 最新版本介绍
	 */
	public String remarksUpdate;
	/**
	 * 安装包大小
	 */
	public String contentLength;
	/**
	 * 安装包下载地址
	 */
	public String upgradeUrl;
	/**
	 * 强制更新标示(1:强制，2:不强制)
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
