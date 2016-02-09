package com.focustech.common.widget.associatemail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

/**********************************************************
 * @�ļ����ƣ�MailBoxAssociateView.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2014��8��7�� ����3:05:13
 * @�ļ��������Զ�����������ؼ�
 * @�޸���ʷ��2014��8��7�մ�����ʼ�汾
 **********************************************************/
public class MailBoxAssociateView extends MultiAutoCompleteTextView
{
	public MailBoxAssociateView(Context context)
	{
		super(context);
	}

	public MailBoxAssociateView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public MailBoxAssociateView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean enoughToFilter()
	{
		// ����ַ��а���'@'���Ҳ��ڵ�һλ������������
		return getText().toString().contains("@") && getText().toString().indexOf("@") > 0;
	}
}
