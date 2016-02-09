package com.focustech.common.broadcast.xmpp;

import java.util.ArrayList;
import java.util.List;

import com.focustech.common.module.AppInfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

/**********************************************************
 * @�ļ����ƣ�PackageManager.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2013-11-25 ����02:03:33
 * @�ļ���������װ��������
 * @�޸���ʷ��2013-11-25������ʼ�汾
 **********************************************************/
public class PackageManager
{
	private static PackageManager manager = null;
	private Context mContext;

	private PackageManager(Context context)
	{
		this.mContext = context;
	}

	public static PackageManager getInstance(Context context)
	{
		if (manager == null)
		{
			manager = new PackageManager(context);
		}
		return manager;
	}

	/**
	 * ��������û���װ��Ӧ����Ϣ
	 * @return
	 */
	public ArrayList<AppInfo> getAllUserPackage()
	{
		ArrayList<AppInfo> appList = new ArrayList<AppInfo>(); // �����洢��ȡ��Ӧ����Ϣ����
		List<PackageInfo> packages = mContext.getPackageManager().getInstalledPackages(0);
		for (int i = 0; i < packages.size(); i++)
		{
			PackageInfo packageInfo = packages.get(i);
			AppInfo tmpInfo = new AppInfo();
			tmpInfo.appName = packageInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString();
			tmpInfo.packageName = packageInfo.packageName;
			tmpInfo.versionName = packageInfo.versionName;
			tmpInfo.versionCode = packageInfo.versionCode;
			tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(mContext.getPackageManager());
			// Only display the non-system app info
			if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
			{
				appList.add(tmpInfo);// �����ϵͳӦ�ã��������appList
			}
		}
		return appList;
	}

	/**
	 * ����Ӧ�ð�����ȡ��Ӧ�õ������Ϣ
	 * @param keyword
	 * @return
	 */
	public AppInfo getAppInfoByKeyword(String keyword)
	{
		ArrayList<AppInfo> appList = getAllUserPackage();
		AppInfo appInfo = null;
		for (int i = 0; i < appList.size(); i++)
		{
			if (appList.get(i).packageName.equals(keyword))
			{
				appInfo = appList.get(i);
				break;
			}
		}
		return appInfo;
	}
}
