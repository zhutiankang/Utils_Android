package com.focustech.common.mob;

import java.io.File;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;

import com.focustech.common.R;
import com.focustech.common.listener.CrashListener;
import com.focustech.common.listener.SimpleDisposeDataListener;
import com.focustech.common.util.LogUtil;
import com.focustech.common.util.MobileUtil;
import com.focustech.common.util.NetworkUtils;
import com.focustech.common.util.ToastUtil;

/**********************************************************
 * @文件名称：MicCrashHandler.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年6月11日 下午4:24:57
 * @文件描述：捕获全局未处理到的异常，上传服务器
 * @修改历史：2015年6月11日创建初始版本
 **********************************************************/
public class MicCrashHandler implements UncaughtExceptionHandler
{
	public static final String TAG = "MicCrashHandler";
	/**
	 * 系统默认的异常捕获类
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;

	/**
	 * 自定义异常处理类
	 */
	private static MicCrashHandler instance;

	private Context mContext;
	/**
	 * 存储异常信息
	 */
	private HashMap<String, String> infos = new HashMap<String, String>();
	// 存储文件夹路径
	private static final String path = Environment.getExternalStorageDirectory() + "/focustech/mic/log/";
	private File dirFile;
	private File file;
	private static final String fileName = "mic_crash_exception.log";
	private CrashListener crashListener;

	private MicCrashHandler()
	{
	}

	static
	{
		instance = new MicCrashHandler();
	}

	/**
	 * 单例模式，获取自定义异常处理类
	 */
	public static MicCrashHandler getInstance()
	{
		return instance;
	}

	public void init(Context context, CrashListener crashListener)
	{
		mContext = context;
		this.crashListener = crashListener;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 
	 * 自定义异常处理
	 * @param thread
	 * @param ex
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex)
	{
		if (!handleException(ex) && mDefaultHandler != null)
		{
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		}
		else
		{
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				LogUtil.e(TAG, "error : ", e);
			}
			finally
			{
				if (crashListener != null)
				{
					crashListener.onCrash();
				}
			}
		}
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 */
	private boolean handleException(Throwable ex)
	{
		if (ex == null)
		{
			return false;
		}

		new Thread()
		{
			@Override
			public void run()
			{
				Looper.prepare();
				ToastUtil.toast(mContext, R.string.crash_exception);
				Looper.loop();
			}
		}.start();

		// 收集设备参数信息
		MobileUtil.collectDeviceInfo(mContext, infos);
		// 保存日志文件
		saveCrashInfo2File(ex);
		/**
		 * 以后要加上传功能,需要启动服务去上传应该
		 */
		uploadExceptionFile();
		return true;
	}

	/**
	 * 保存错误信息到文件中
	 * @param ex
	 * @return	返回文件名称,便于将文件传送到服务器
	 */
	private void saveCrashInfo2File(Throwable ex)
	{
		/**
		 * 组装异常信息
		 */
		String exceptionMsg = assembleExceptionMsg(ex);
		try
		{
			if (MobileUtil.isHaveSDcard())
			{
				dirFile = new File(path);
				if (!dirFile.exists())
				{
					dirFile.mkdirs();
				}

				file = new File(path + fileName);
				RandomAccessFile fos = new RandomAccessFile(file, "rw");
				fos.seek(file.length());
				fos.write(exceptionMsg.getBytes());
				fos.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 组装异常信息，在这里拼装想要的格式
	 */
	private String assembleExceptionMsg(Throwable ex)
	{
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : infos.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null)
		{
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result + "\r\n");

		return sb.toString();
	}

	/**
	 * 发送上传文件请求
	 */
	private void uploadExceptionFile()
	{

		if (!NetworkUtils.isConnectInternet(mContext))
		{
			return;
		}
		if (file == null || !file.exists())
		{
			return;
		}
		else
		{
			// 上传成功后需要删除日志文件
			MobRequestCenter.uploadExceptionFile(new SimpleDisposeDataListener(), file);
		}
	}

}
