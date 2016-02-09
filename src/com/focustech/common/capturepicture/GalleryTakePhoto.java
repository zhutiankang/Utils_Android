package com.focustech.common.capturepicture;

import java.io.File;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.View;

import com.focustech.common.util.MobileUtil;
import com.focustech.common.util.Utils;

/**********************************************************
 * @文件名称：GalleryTakePhoto.java
 * @文件作者：xiongjiangwei
 * @创建时间：2013-11-18 下午02:48:49
 * @文件描述：从相册选择图片
 * @修改历史：2013-11-18创建初始版本
 **********************************************************/
public class GalleryTakePhoto extends TakePhoto
{
	/**
	 * 构造方法
	 * @param activity		页面对象
	 * @param displayView	图片显示对象
	 * @param takePhotoListener	图片回调
	 */
	public GalleryTakePhoto(Activity activity, View displayView, TakePhotoListener takePhotoListener)
	{
		super(activity, displayView, takePhotoListener, false);
	}

	public GalleryTakePhoto(Fragment fragment, View displayView, TakePhotoListener takePhotoListener)
	{
		super(fragment, displayView, takePhotoListener, false);
	}

	/**
	 * @param fragment 
	 * @param displayView
	 * @param takePhotoListener
	 * @param isnotstart 是否启动选取图片，true 不启动，false 启动
	 */
	public GalleryTakePhoto(Fragment fragment, View displayView, TakePhotoListener takePhotoListener, boolean isnotstart)
	{
		super(fragment, displayView, takePhotoListener, isnotstart);
	}

	/**
	 * @param activity
	 * @param displayView
	 * @param takePhotoListener
	 * @param isnotstart 是否启动选取图片，true 不启动，false 启动
	 */
	public GalleryTakePhoto(Activity activity, View displayView, TakePhotoListener takePhotoListener, boolean isnotstart)
	{
		super(activity, displayView, takePhotoListener, isnotstart);
	}

	/**
	 * 构造方法
	 * @param activity	页面对象
	 * @param displayView	图片显示对象
	 * @param aspectX	裁剪宽比例
	 * @param aspectY	裁剪高比例
	 * @param outputX	裁剪宽度
	 * @param outputY	裁剪高度
	 * @param takePhotoListener	图片回调
	 */
	public GalleryTakePhoto(Activity activity, View displayView, int aspectX, int aspectY, int outputX, int outputY,
			TakePhotoListener takePhotoListener)
	{
		super(activity, displayView, aspectX, aspectY, outputX, outputY, takePhotoListener);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode != Activity.RESULT_OK)
		{
			return;
		}
		if (requestCode != PHOTO_REQUEST_GET)
		{
			super.onActivityResult(requestCode, resultCode, data);
		}
		else
		{
			Context tmp = null;
			if (mFragment != null)
			{
				tmp = mFragment.getActivity().getApplicationContext();
			}
			else
			{
				tmp = mActivity.getApplicationContext();
			}
			// String thePath = Utils.getPath(tmp, data.getData());
			String thePath = getPath(tmp, data.getData());

			Uri uri = Uri.fromFile(new File(thePath));

			// mIntent = new Intent("com.android.camera.action.CROP");
			// mIntent.setDataAndType(uri, "image/*");

			// cropImageUri(currentUri);

			compressImageUri(uri);
		}
	}

	@Override
	protected void startSelectImage()
	{
		if (MobileUtil.isHaveSDcard())
		{
			createPhotoDir();
			currentUri = getCurrentUri();
			// mIntent = new Intent(Intent.ACTION_GET_CONTENT);
			if (Build.VERSION.SDK_INT < 19)
			{
				mIntent = new Intent(Intent.ACTION_GET_CONTENT);
				mIntent.setType("image/*");
				// cropImageUri(currentUri);
			}
			else
			{
				mIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
				mIntent.setType("image/*");

			}

			if (mFragment != null)
			{
				mFragment.startActivityForResult(mIntent, PHOTO_REQUEST_GET);
			}
			else
			{
				mActivity.startActivityForResult(mIntent, PHOTO_REQUEST_GET);
			}

		}
		else
		{
			mTakePhotoListener.onFail(TakePhotoFailReason.SDCardNotFound);
		}
	}
	
	public String getPath(final Context context, final Uri uri)
	{

		// final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (Build.VERSION.SDK_INT < 19)
		{
			if ("content".equalsIgnoreCase(uri.getScheme()))
			{

				// Return the remote address
				if (Utils.isGooglePhotosUri(uri))
					return uri.getLastPathSegment();

				return Utils.getDataColumn(context, uri, null, null);
			}
			// File
			else if ("file".equalsIgnoreCase(uri.getScheme()))
			{
				return uri.getPath();
			}

		}
		// MediaStore (and general)
		else
		{
			if (DocumentsContract.isDocumentUri(context, uri))
			{
				// ExternalStorageProvider
				if (Utils.isExternalStorageDocument(uri))
				{
					final String docId = DocumentsContract.getDocumentId(uri);
					final String[] split = docId.split(":");
					final String type = split[0];

					if ("primary".equalsIgnoreCase(type))
					{
						return Environment.getExternalStorageDirectory() + "/" + split[1];
					}

					// TODO handle non-primary volumes
				}
				// DownloadsProvider
				else if (Utils.isDownloadsDocument(uri))
				{
					final String id = DocumentsContract.getDocumentId(uri);
					final Uri contentUri = ContentUris.withAppendedId(
							Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

					return Utils.getDataColumn(context, contentUri, null, null);
				}
				// MediaProvider
				else if (Utils.isMediaDocument(uri))
				{
					final String docId = DocumentsContract.getDocumentId(uri);
					final String[] split = docId.split(":");
					final String type = split[0];

					Uri contentUri = null;
					if ("image".equals(type))
					{
						contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
					}
					else if ("video".equals(type))
					{
						contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
					}
					else if ("audio".equals(type))
					{
						contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
					}

					final String selection = "_id=?";
					final String[] selectionArgs = new String[]
					{ split[1] };

					return Utils.getDataColumn(context, contentUri, selection, selectionArgs);
				}
			}
		}

		return null;
	}
}
