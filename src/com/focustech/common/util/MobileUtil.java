package com.focustech.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.focustech.common.module.PhoneConfigParams;
import com.tencent.android.tpush.XGPushConfig;

public class MobileUtil
{
	/***
	 * ����ֻ��豸�Ĵ���
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context)
	{
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		// Get deviceId
		String deviceId = tm.getDeviceId();
		// If running on an emulator
		if (deviceId == null || deviceId.trim().length() == 0 || deviceId.matches("0+"))
		{
			deviceId = (new StringBuilder("EMU")).append((new Random(System.currentTimeMillis())).nextLong())
					.toString();
		}
		return deviceId;
	}

	/**
	 * ���SIM����Ψһʶ���
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context)
	{
		try
		{
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			String imsiCode = tm.getSubscriberId();
			return imsiCode;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	/***
	 * ����ֻ��ĵ绰����
	 * @param context
	 * @return
	 */
	public static String getPhoneNumber(Context context)
	{
		try
		{
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			return tm.getLine1Number();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * �ж��Ƿ�װ��sdcard
	 * @return
	 */
	public static boolean isHaveSDcard()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // �ж�sd���Ƿ����
	}

	/**
	 * ����ֻ��ڴ��С����ϢRAM��С
	 */
	public static String getTotalMemory()
	{
		ArrayList<String> infos = new ArrayList<String>();
		String str1 = "/proc/meminfo";
		String str2 = "";
		FileReader fr = null;
		BufferedReader localBufferedReader = null;
		try
		{
			fr = new FileReader(str1);
			localBufferedReader = new BufferedReader(fr, 8192);
			while ((str2 = localBufferedReader.readLine()) != null)
			{
				String[] strs = str2.split(":");
				infos.add(strs[1].trim());
			}
			return (infos.size() > 0 ? infos.get(0).trim() : "");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (localBufferedReader != null)
				{
					localBufferedReader.close();
				}
				if (fr != null)
				{
					fr.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * ����ֻ��汾��Ϣ[0] ��ʾ�ں˰汾 ,[1] ��ʾandroid �汾,[2] �ͺ� ,[3] �汾��
	 * @return
	 */
	public static String[] getVersion()
	{
		String[] version =
		{ "null", "null", "null", "null" };
		String str1 = "/proc/version";
		String str2;
		String[] arrayOfString;
		FileReader localFileReader = null;
		BufferedReader localBufferedReader = null;
		try
		{
			localFileReader = new FileReader(str1);
			localBufferedReader = new BufferedReader(localFileReader, 8192);
			str2 = localBufferedReader.readLine();
			arrayOfString = str2.split("\\s+");
			version[0] = arrayOfString[2];// KernelVersion
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			try
			{
				if (localBufferedReader != null)
				{
					localBufferedReader.close();
				}
				if (localFileReader != null)
				{
					localFileReader.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		version[1] = Build.VERSION.RELEASE;// firmware version
		version[2] = Build.MODEL;// model
		version[3] = Build.DISPLAY;// system version
		return version;
	}

	/***
	 * ����豸��Ψһ��ϣֵ
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceUniqueid(Context context)
	{
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();
		return uniqueId;
	}

	/**
	 * ����ֻ����ò��� ��Ҫ��Ȩ�ޣ�READ_PHONE_STATE
	 * @param context
	 * @return
	 */
	public static PhoneConfigParams getMobileInfo(Context context)
	{
		try
		{
			PhoneConfigParams mobileInfo = new PhoneConfigParams();
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			// ����豸��ΨһID
			mobileInfo.setDeviceUniqueid(tm.getDeviceId());
			mobileInfo.setUserPkId(XGPushConfig.getToken(context));
			// �����Ӫ������
			mobileInfo.setBelongedBusiness(tm.getSimOperatorName());
			// �����Ļ�ֱ���
			DisplayMetrics dm = new DisplayMetrics();
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			wm.getDefaultDisplay().getMetrics(dm);
			mobileInfo.setDeviceShowratio(dm.widthPixels + "*" + dm.heightPixels);
			String[] versionInfo = MobileUtil.getVersion();
			// ����ֻ�RAM��С
			mobileInfo.setDeviceTotalMemory(MobileUtil.getTotalMemory());
			if (versionInfo != null)
			{
				// ����豸�����е�Ӳ���汾
				mobileInfo.setDeviceHardwareVersion(versionInfo[0]);
				// ����豸�����еĹ̼��汾
				mobileInfo.setDeviceFirmwareVersion(versionInfo[1]);
				// �����豸����
				mobileInfo.setDeiviceName(versionInfo[2]);
			}
			mobileInfo.setPhoneNumber(MobileUtil.getPhoneNumber(context));
			return mobileInfo;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String getDevicePosition(Context context)
	{
		return context.getSharedPreferences("devicePosition", 0).getString("devicePosition", "����");
	}

	/**
	 * �ռ��豸������Ϣ
	 * @param ctx
	 */
	public static void collectDeviceInfo(Context ctx, HashMap<String, String> infos)
	{
		try
		{
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
			if (pi != null)
			{
				String versionName = pi.versionName == null ? "null" : pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		}
		catch (NameNotFoundException e)
		{
			e.getMessage();
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields)
		{
			try
			{
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
			}
			catch (Exception e)
			{
				e.getMessage();
			}
		}
	}

	/**
	 * ��ȡ�豸Ψһ���к�
	 * @param context
	 * @return
	 */
	public static String getUniqueId(Context context)
	{

		return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);

	}
}
