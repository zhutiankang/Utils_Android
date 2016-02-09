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
 * @�ļ����ƣ�MicCrashHandler.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��6��11�� ����4:24:57
 * @�ļ�����������ȫ��δ�������쳣���ϴ�������
 * @�޸���ʷ��2015��6��11�մ�����ʼ�汾
 **********************************************************/
public class MicCrashHandler implements UncaughtExceptionHandler
{
	public static final String TAG = "MicCrashHandler";
	/**
	 * ϵͳĬ�ϵ��쳣������
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;

	/**
	 * �Զ����쳣������
	 */
	private static MicCrashHandler instance;

	private Context mContext;
	/**
	 * �洢�쳣��Ϣ
	 */
	private HashMap<String, String> infos = new HashMap<String, String>();
	// �洢�ļ���·��
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
	 * ����ģʽ����ȡ�Զ����쳣������
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
	 * �Զ����쳣����
	 * @param thread
	 * @param ex
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex)
	{
		if (!handleException(ex) && mDefaultHandler != null)
		{
			// ����û�û�д�������ϵͳĬ�ϵ��쳣������������
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
	 * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.
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

		// �ռ��豸������Ϣ
		MobileUtil.collectDeviceInfo(mContext, infos);
		// ������־�ļ�
		saveCrashInfo2File(ex);
		/**
		 * �Ժ�Ҫ���ϴ�����,��Ҫ��������ȥ�ϴ�Ӧ��
		 */
		uploadExceptionFile();
		return true;
	}

	/**
	 * ���������Ϣ���ļ���
	 * @param ex
	 * @return	�����ļ�����,���ڽ��ļ����͵�������
	 */
	private void saveCrashInfo2File(Throwable ex)
	{
		/**
		 * ��װ�쳣��Ϣ
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
	 * ��װ�쳣��Ϣ��������ƴװ��Ҫ�ĸ�ʽ
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
	 * �����ϴ��ļ�����
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
			// �ϴ��ɹ�����Ҫɾ����־�ļ�
			MobRequestCenter.uploadExceptionFile(new SimpleDisposeDataListener(), file);
		}
	}

}
