package com.focustech.common.capturepicture;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**********************************************************
 * @文件名称：BitmapUtil.java
 * @文件作者：xuliucheng
 * @创建时间：2015年6月9日 下午5:05:46
 * @文件描述：图片压缩工具
 * @修改历史：2015年6月9日创建初始版本
 **********************************************************/
public class BitmapUtil
{

	/**
	 * @param pathName 图片路径
	 * @param targetWidth 最大宽度
	 * @param targetHeight 最大高度
	 * @return Bitmap
	 */
	public static Bitmap compressImg(String pathName, int targetWidth, int targetHeight)
	{
		BitmapFactory.Options opts = new BitmapFactory.Options();
		// 不去真的解析图片，只是获取图片的头部信息，包含宽高等；
		opts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		// 得到图片的宽度、高度；
		float imgWidth = opts.outWidth;
		float imgHeight = opts.outHeight;
		// 分别计算图片宽度、高度与目标宽度、高度的比例；取大于等于该比例的最小整数；
		int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
		int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
		opts.inSampleSize = 1;
		if (widthRatio > 1 || heightRatio > 1)
		{
			if (widthRatio > heightRatio)
			{
				opts.inSampleSize = widthRatio;
			}
			else
			{
				opts.inSampleSize = heightRatio;
			}
		}
		// 设置好缩放比例后，加载图片进内容；
		opts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(pathName, opts);
		return bitmap;
	}

	/**
	 * @param bm Bitmap
	 * @param fileName 保存路径
	 * @throws Exception
	 */
	public static Long saveFile(Bitmap bm, String fileName) throws Exception
	{
		File dirFile = new File(fileName);
		// 检测图片是否存在
		if (dirFile.exists())
		{
			dirFile.delete(); // 删除原图片
		}
		File myCaptureFile = new File(fileName);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
		// 100表示不进行压缩，70表示压缩率为30%
		bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
		bos.flush();
		bos.close();
		return dirFile.length();
	}
}
