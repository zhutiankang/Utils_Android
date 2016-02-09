package com.focustech.common.mob.feedback;

import java.io.File;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnInfoListener;
import android.os.Environment;

/**
 * ¼��������
 * 
 * @author chenkangpeng
 * 
 */
public class AudioRecorder
{
	/** ¼�������ʱ�䣬������ʱ�������¼�� ,Ĭ��ʱ����30�� */
	private int defalutMaxTime = 1000 * 30;
	/** ¼���ļ������·�� */
	private String defaultAudioFilePath = "mnt/sdcard/recorder/tem/";
	private MediaRecorder mediaRecorder;
	private RecorderState state = RecorderState.stop;
	/** ¼���ļ� */
	private File tempAudioFile;
	private String defaultAudioExtensions = ".amr";
	private RecorderListener recorderListener;

	public AudioRecorder()
	{
		super();
	}

	public void start()
	{
		new Thread()
		{
			public void run()
			{
				startRecorder();
			}
		}.start();
	}

	/**
	 * ��ʼ¼��
	 */
	private void startRecorder()
	{
		if (!isSdcardexit())
		{
			return;
		}
		try
		{
			if (state != RecorderState.recording)
			{
				state = RecorderState.recording;
				File dFile = new File(defaultAudioFilePath);
				if (!dFile.exists())
				{
					dFile.mkdirs();
				}
				mediaRecorder = new MediaRecorder();
				// ������Դ����˷����
				mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				// ���������ʽ
				mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
				mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				tempAudioFile = new File(getDefaultAudioFilePath() + System.currentTimeMillis()
						+ defaultAudioExtensions);
				mediaRecorder.setOutputFile(tempAudioFile.getAbsolutePath());
				mediaRecorder.setMaxDuration(defalutMaxTime);
				mediaRecorder.setOnInfoListener(new OnInfoListener()
				{
					@Override
					public void onInfo(MediaRecorder mr, int what, int extra)
					{
						if (what == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED)
						{
							recorderListener.finishRecorder(tempAudioFile);
							state = RecorderState.stop;
						}
					}
				});
				mediaRecorder.prepare();
				mediaRecorder.start();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ֹͣ¼��
	 */
	public void stopRecorder()
	{
		if (state == RecorderState.recording)
		{
			state = RecorderState.stop;
			try
			{
				mediaRecorder.stop();
				mediaRecorder.release();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			mediaRecorder = null;
			if (recorderListener != null)
			{
				recorderListener.finishRecorder(tempAudioFile);
			}
		}
	}

	private boolean isSdcardexit()
	{
		return (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED));
	}

	/***
	 * ��������¼��������״̬
	 * 
	 * @author chenkangpeng
	 * 
	 */
	enum RecorderState
	{
		recording, stop
	}

	/***
	 * 
	 * @return
	 */
	private String getDefaultAudioFilePath()
	{
		if (defaultAudioFilePath.endsWith("/"))
		{
			return defaultAudioFilePath;
		}
		else
		{
			return defaultAudioFilePath + "/";
		}
	}

	public interface RecorderListener
	{
		public void finishRecorder(File file);
	}

	public long getDefalutMaxTime()
	{
		return defalutMaxTime;
	}

	public void setDefalutMaxTime(int defalutMaxTime)
	{
		this.defalutMaxTime = defalutMaxTime;
	}

	public String getDefaultAudioExtensions()
	{
		return defaultAudioExtensions;
	}

	public void setDefaultAudioExtensions(String defaultAudioExtensions)
	{
		this.defaultAudioExtensions = defaultAudioExtensions;
	}

	public RecorderListener getRecorderListener()
	{
		return recorderListener;
	}

	public void setRecorderListener(RecorderListener recorderListener)
	{
		this.recorderListener = recorderListener;
	}

	public void setDefaultAudioFilePath(String defaultAudioFilePath)
	{
		this.defaultAudioFilePath = defaultAudioFilePath;
	}
}
