package com.focustech.common.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.focustech.common.R;
import com.focustech.common.util.Utils;

/**********************************************************
 * @文件名称：CommonDialog.java
 * @文件作者：xiongjiangwei
 * @创建时间：2014年9月18日 下午7:02:43
 * @文件描述：公用的对话框
 * @修改历史：2014年9月18日创建初始版本
 **********************************************************/
public final class CommonDialog extends Dialog
{
	private LinearLayout twoButtonLayout;
	private LinearLayout oneButtonLayout;

	private ViewGroup contentView;

	private RelativeLayout contentLayout;

	private boolean isOneButton = false;
	private int dialogWidth = 258;
	private int dialogHeight = LayoutParams.WRAP_CONTENT;
	private String cancelBtnText;
	private String confirmBtnText;
	private int cancelBtnTextColor;
	private int confirmBtnTextColor;
	private DialogClickListener cancelDialogClick;
	private DialogClickListener confirmDialogClick;
	private int dialogBtnTextSize;
	private int dialogSplitLineColor;
	private boolean isCancelable = true;
	private boolean confirmBtnCamcel = true;

	public interface DialogClickListener
	{
		public void onDialogClick();
	}

	public CommonDialog(Context context)
	{
		super(context, R.style.customDialog);
		cancelBtnText = context.getString(R.string.cancel);
		confirmBtnText = context.getString(R.string.confirm);
		cancelBtnTextColor = context.getResources().getColor(R.color.common_dialog_text);
		confirmBtnTextColor = context.getResources().getColor(R.color.common_dialog_text);
		dialogBtnTextSize = 14;
		dialogSplitLineColor = context.getResources().getColor(R.color.common_dialog_line);

		setContentView(createDialogView(R.layout.common_dialog));
		contentLayout = (RelativeLayout) findChildViewById(R.id.common_dialog_content_layout);
	}

	/**
	 * 设置对话框的内容View
	 * @param contentView
	 * @return
	 */
	public CommonDialog setDialogContentView(View contentView)
	{
		if (contentView.getParent() == null)
			contentLayout.addView(contentView);
		return this;
	}

	/**
	 * 设置对话框模式
	 * @param isOneButton	true:单个按钮,false:两个按钮
	 * @return
	 */
	public CommonDialog setDialogMode(boolean isOneButton)
	{
		this.isOneButton = isOneButton;
		return this;
	}

	/**
	 * 设置取消按钮文案
	 * @param characterResId
	 * @return
	 */
	public CommonDialog setCancelBtnText(int characterResId)
	{
		return setCancelBtnText(getContext().getString(characterResId));
	}

	/**
	 * 设置确定按钮文案
	 * @param characterResId
	 * @return
	 */
	public CommonDialog setConfirmBtnText(int characterResId)
	{
		return setConfirmBtnText(getContext().getString(characterResId));
	}

	/**
	 * 设置取消按钮文案
	 * @param character
	 * @return
	 */
	public CommonDialog setCancelBtnText(String character)
	{
		this.cancelBtnText = character;
		return this;
	}

	/**
	 * 设置确定按钮文案
	 * @param character
	 * @return
	 */
	public CommonDialog setConfirmBtnText(String character)
	{
		this.confirmBtnText = character;
		return this;
	}

	/**
	 * 设置取消按钮文字颜色
	 * @param colorResId
	 * @return
	 */
	public CommonDialog setCancelBtnTextColor(int colorResId)
	{
		this.cancelBtnTextColor = colorResId;
		return this;
	}

	/**
	 * 设置确定按钮文字颜色
	 * @param colorResId
	 * @return
	 */
	public CommonDialog setConfirmTextColor(int colorResId)
	{
		this.confirmBtnTextColor = colorResId;
		return this;
	}

	/**
	 * 设置确定按钮点击事件
	 * @param confirmClick
	 * @return
	 */
	public CommonDialog setConfirmDialogListener(DialogClickListener confirmClick)
	{
		this.confirmDialogClick = confirmClick;
		return this;
	}

	/**
	 * 设置取消按钮点击事件
	 * @param cancelClick
	 * @return
	 */
	public CommonDialog setCancelDialogListener(DialogClickListener cancelClick)
	{
		this.cancelDialogClick = cancelClick;
		return this;
	}

	/**
	 * 设置对话框是否可以自动关闭
	 * @param isCancelable
	 * @return
	 */
	public CommonDialog setDialogCancelable(boolean isCancelable)
	{
		this.isCancelable = isCancelable;
		return this;
	}

	/**
	 * 设置按钮文字大小
	 * @param size(单位:sp)
	 * @return
	 */
	public CommonDialog setButtonTextSize(int size)
	{
		this.dialogBtnTextSize = size;
		return this;
	}

	/**
	 * 设置对话框分割线颜色
	 * @param colorResId
	 * @return
	 */
	public CommonDialog setDialogSplitLineColor(int colorResId)
	{
		this.dialogSplitLineColor = colorResId;
		return this;
	}

	/**
	 * 设置对话框宽度
	 * @param dialogWidth
	 * @return
	 */
	public CommonDialog setDialogWidth(int dialogWidth)
	{
		this.dialogWidth = dialogWidth;
		return this;
	}

	/**
	 * 设置对话框高度
	 * @param dialogHeight
	 * @return
	 */
	public CommonDialog setDialogHeight(int dialogHeight)
	{
		this.dialogHeight = dialogHeight;
		return this;
	}

	/**
	 * 设置点击确定按钮后是否自动关闭dialog
	 * @param confirmBtnCamcel
	 * @return
	 */
	public CommonDialog setDialogConfirmBtnCamcel(boolean confirmBtnCamcel)
	{
		this.confirmBtnCamcel = confirmBtnCamcel;
		return this;
	}

	/**
	 * 创建一个对话框
	 * @return
	 */
	public CommonDialog build()
	{
		setParams(
				Utils.toDip(getContext(), dialogWidth),
				dialogHeight == LayoutParams.WRAP_CONTENT ? LayoutParams.WRAP_CONTENT : Utils.toDip(getContext(),
						dialogHeight));

		twoButtonLayout = (LinearLayout) findChildViewById(R.id.common_dialog_two_btn_layout);
		oneButtonLayout = (LinearLayout) findChildViewById(R.id.common_dialog_one_btn_layout);
		View line1 = findChildViewById(R.id.common_dialog_line1);
		line1.setBackgroundColor(dialogSplitLineColor);
		if (isOneButton)
		{
			twoButtonLayout.setVisibility(View.GONE);
			oneButtonLayout.setVisibility(View.VISIBLE);
			setCancelable(false);
			setCanceledOnTouchOutside(false);

			Button btnComfirm = (Button) findChildViewById(R.id.common_dialog_btn_sure);
			btnComfirm.setText(confirmBtnText);
			btnComfirm.setTextColor(confirmBtnTextColor);
			btnComfirm.setTextSize(dialogBtnTextSize);
			btnComfirm.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (confirmBtnCamcel)
						dismiss();
					if (confirmDialogClick != null)
						confirmDialogClick.onDialogClick();
				}
			});
		}
		else
		{
			twoButtonLayout.setVisibility(View.VISIBLE);
			oneButtonLayout.setVisibility(View.GONE);
			setCancelable(isCancelable);
			setCanceledOnTouchOutside(isCancelable);

			View line2 = findChildViewById(R.id.common_dialog_line2);
			line2.setBackgroundColor(dialogSplitLineColor);

			Button btnCancel = (Button) findChildViewById(R.id.common_dialog_btn_cancel);
			btnCancel.setText(cancelBtnText);
			btnCancel.setTextColor(cancelBtnTextColor);
			btnCancel.setTextSize(dialogBtnTextSize);
			btnCancel.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					dismiss();
					if (cancelDialogClick != null)
						cancelDialogClick.onDialogClick();
				}
			});

			Button btnComfirm = (Button) findChildViewById(R.id.common_dialog_btn_confirm);
			btnComfirm.setText(confirmBtnText);
			btnComfirm.setTextColor(confirmBtnTextColor);
			btnComfirm.setTextSize(dialogBtnTextSize);
			btnComfirm.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (confirmBtnCamcel)
						dismiss();
					if (confirmDialogClick != null)
						confirmDialogClick.onDialogClick();
				}
			});
		}
		show();
		return this;
	}

	/**
	 * 创建一个简单的对话框
	 * @param simpleTextSize
	 * @param simpleTextColor
	 * @param simpleTextCharacter
	 * @return
	 */
	public CommonDialog buildSimpleDialog(int simpleTextSize, int simpleTextColor, String simpleTextCharacter)
	{
		LayoutInflater.from(getContext()).inflate(R.layout.common_dialog_simple_content, contentLayout);
		TextView contextMsg = (TextView) findChildViewById(R.id.common_dialog_content_msg);
		contextMsg.setText(simpleTextCharacter);
		contextMsg.setTextSize(simpleTextSize);
		contextMsg.setTextColor(getContext().getResources().getColor(simpleTextColor));
		return build();
	}

	/**
	 * 创建一个简单的对话框
	 * @param simpleTextColor
	 * @param simpleTextCharacter
	 * @return
	 */
	public CommonDialog buildSimpleDialog(int simpleTextColor, String simpleTextCharacter)
	{
		return buildSimpleDialog(14, simpleTextColor, simpleTextCharacter);
	}

	/**
	 * 创建一个简单的对话框
	 * @param simpleTextCharacter
	 * @return
	 */
	public CommonDialog buildSimpleDialog(String simpleTextCharacter)
	{
		return buildSimpleDialog(14, R.color.common_dialog_text, simpleTextCharacter);
	}

	private ViewGroup createDialogView(int layoutId)
	{
		contentView = (ViewGroup) LayoutInflater.from(getContext()).inflate(layoutId, null);
		return contentView;
	}

	private void setParams(int width, int height)
	{
		WindowManager.LayoutParams dialogParams = this.getWindow().getAttributes();
		dialogParams.width = width;
		dialogParams.height = height;
		this.getWindow().setAttributes(dialogParams);
	}

	private View findChildViewById(int id)
	{
		return contentView.findViewById(id);
	}

}
