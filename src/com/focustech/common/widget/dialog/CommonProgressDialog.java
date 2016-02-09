package com.focustech.common.widget.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;

/**********************************************************
 * @�ļ����ƣ�CommonProgressDialog.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��7��22�� ����4:09:53
 * @�ļ�����������Loading�Ի���
 * @�޸���ʷ��2014��7��22�մ�����ʼ�汾
 **********************************************************/
public class CommonProgressDialog
{
	private ProgressDialog progressDialog;
	private static CommonProgressDialog utils;

	static
	{
		utils = new CommonProgressDialog();
	}

	private CommonProgressDialog()
	{
	}

	public static CommonProgressDialog getInstance()
	{
		return utils;
	}

	/**
	 * ��ʾ�������Ի���(����ȡ��)
	 * @param context
	 * @param msg
	 */
	public void showProgressDialog(Context context, String msg)
	{
		showProgressDialog(context, null, msg);
	}

	/**
	 * ��ʾ�������Ի���(��ȡ��)
	 * @param context
	 * @param msg
	 */
	public void showCancelableProgressDialog(Context context, String msg)
	{
		showCancelableProgressDialog(context, null, msg);
	}

	/**
	 * ��ʾ�������Ի���(����ȡ��)
	 * @param context
	 * @param title
	 * @param msg
	 */
	public void showProgressDialog(Context context, String title, String msg)
	{
		showProgressDialog(context, title, msg, false, null);
	}

	/**
	 * ��ʾ�������Ի���(��ȡ��)
	 * @param context
	 * @param title
	 * @param msg
	 */
	public void showCancelableProgressDialog(Context context, String title, String msg)
	{
		showProgressDialog(context, title, msg, true, null);
	}

	/**
	 * ��ʾ�������Ի���
	 * @param context
	 * @param title
	 * @param msg
	 * @param isCancelable
	 * @param dismissListener
	 */
	public void showProgressDialog(Context context, String title, String msg, boolean isCancelable,
			OnDismissListener dismissListener)
	{
		if (context == null)
			return;
		if (progressDialog == null)
		{
			progressDialog = new ProgressDialog(context);
		}
		progressDialog.setTitle(title);
		progressDialog.setMessage(msg);
		progressDialog.setCancelable(isCancelable);
		if (dismissListener != null)
		{
			progressDialog.setOnDismissListener(dismissListener);
		}
		progressDialog.show();
	}

	/**
	 * �رս������Ի���
	 */
	public void dismissProgressDialog()
	{
		try
		{
			if (progressDialog != null)
			{
				progressDialog.dismiss();
				progressDialog = null;
			}
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (progressDialog != null)
			{
				progressDialog.cancel();
				progressDialog = null;
			}
		}
	}

	/**
	 * �������Ƿ���ʾ
	 * @return
	 */
	public boolean isShowing()
	{
		return progressDialog != null ? progressDialog.isShowing() : false;
	}
}
