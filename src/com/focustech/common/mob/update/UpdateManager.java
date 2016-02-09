package com.focustech.common.mob.update;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.focustech.common.http.download.IDownloadListener;

/**********************************************************
 * @�ļ����ƣ�UpdateManager.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��10��24�� ����10:14:55
 * @�ļ������������¹�����
 * @�޸���ʷ��2014��10��24�մ�����ʼ�汾
 **********************************************************/
public class UpdateManager
{
	private static UpdateManager manager;
	private UpdateDownloadRequest thread;
	private ThreadPoolExecutor threadPool;
	private UpdateDownloadRequest downloadRequest;

	private UpdateManager()
	{
		threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	static
	{
		manager = new UpdateManager();
	}

	public static UpdateManager getInstance()
	{
		return manager;
	}

	public void startDownload(int startPos, String downloadUrl, String localFilePath, long contentLength,
			IDownloadListener downloadListener)
	{
		if (downloadRequest != null && downloadRequest.isDownloading())
		{
			return;
		}
		checkLocalFilePath(localFilePath);
		downloadRequest = new UpdateDownloadRequest(startPos, downloadUrl, localFilePath, contentLength,
				downloadListener);
		Future<?> request = threadPool.submit(downloadRequest);
		new WeakReference<Future<?>>(request);
	}

	private void checkLocalFilePath(String localFilePath)
	{
		File path = new File(localFilePath.substring(0, localFilePath.lastIndexOf("/") + 1));
		File file = new File(localFilePath);
		if (!path.exists())
		{
			path.mkdirs();
		}
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void pause()
	{
		if (thread != null)
		{
			thread.stopDownloading();
		}
	}

	public boolean isDownloading()
	{
		if (thread != null)
		{
			return thread.isDownloading();
		}
		return false;
	}

}
