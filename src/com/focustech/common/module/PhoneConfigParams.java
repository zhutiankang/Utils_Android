package com.focustech.common.module;

import java.util.HashMap;

/**********************************************************
 * @�ļ����ƣ�PhoneConfigParams.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��9��14�� ����5:17:55
 * @�ļ��������ֻ���������ģ��
 * @�޸���ʷ��2015��9��14�մ�����ʼ�汾
 **********************************************************/
public class PhoneConfigParams
{
	/**
	 * �û�Ψһ��ʾ
	 */
	private String userPkId;
	/**
	 * �豸��Ψһ��ϡֵ
	 */
	private String deviceUniqueid = "";
	/***
	 * ����״̬
	 */
	private String netState = "1";
	/**
	 * �豸�����̵�����
	 */
	private String deviceUniqueidName = "";
	/***
	 * �û�������Ӫ�̵�����
	 */
	private String belongedBusiness = "";
	/***
	 * �豸�����еĹ̼��汾
	 */
	private String deviceFirmwareVersion = "";
	/***
	 * �豸�����е�Ӳ���汾
	 */
	private String deviceHardwareVersion = "";
	/**
	 * �豸����
	 */
	private String deiviceName = "";
	/***
	 * �豸 �ĵ���λ��
	 */
	private String devicePosition = "";
	/***
	 * �豸����Ļ�ֱ���
	 */
	private String deviceShowratio = "";
	/***
	 * �豸������RAM��С
	 */
	private String deviceTotalMemory = "";
	/**
	 * �û��Ƿ��Ѿ������豸������Ӳ�����̣�������Ӽ���
	 */
	private String isKeyboardDeployed = "";
	/**
	 * �Ƿ��������Ӳ������
	 */
	private String isKeyboardPersent = "";
	/**
	 * �Ƿ��Ѿ�ӵ��ǰ������ͷ
	 */
	private String isownCamera = "";
	/***
	 * �Ƿ��Ѿ���ӵ�Դ
	 */
	private String powerSource = "";
	/***
	 * �ֻ��绰����
	 */
	private String phoneNumber = "";
	/**
	 * ϵͳ�汾
	 */
	private String systemVersion = "";

	public String getDeviceUniqueid()
	{
		return deviceUniqueid;
	}

	public void setDeviceUniqueid(String deviceUniqueid)
	{
		this.deviceUniqueid = deviceUniqueid;
	}

	public String getNetState()
	{
		return netState;
	}

	public void setNetState(String netState)
	{
		this.netState = netState;
	}

	public String getDeviceUniqueidName()
	{
		return deviceUniqueidName;
	}

	public void setDeviceUniqueidName(String deviceUniqueidName)
	{
		this.deviceUniqueidName = deviceUniqueidName;
	}

	public String getBelongedBusiness()
	{
		return belongedBusiness;
	}

	public void setBelongedBusiness(String belongedBusiness)
	{
		this.belongedBusiness = belongedBusiness;
	}

	public String getDeviceFirmwareVersion()
	{
		return deviceFirmwareVersion;
	}

	public void setDeviceFirmwareVersion(String deviceFirmwareVersion)
	{
		this.deviceFirmwareVersion = deviceFirmwareVersion;
	}

	public String getDeviceHardwareVersion()
	{
		return deviceHardwareVersion;
	}

	public void setDeviceHardwareVersion(String deviceHardwareVersion)
	{
		this.deviceHardwareVersion = deviceHardwareVersion;
	}

	public String getDeiviceName()
	{
		return deiviceName;
	}

	public void setDeiviceName(String deiviceName)
	{
		this.deiviceName = deiviceName;
	}

	public String getDevicePosition()
	{
		return devicePosition;
	}

	public void setDevicePosition(String devicePosition)
	{
		this.devicePosition = devicePosition;
	}

	public String getDeviceShowratio()
	{
		return deviceShowratio;
	}

	public void setDeviceShowratio(String deviceShowratio)
	{
		this.deviceShowratio = deviceShowratio;
	}

	public String getDeviceTotalMemory()
	{
		return deviceTotalMemory;
	}

	public void setDeviceTotalMemory(String deviceTotalMemory)
	{
		this.deviceTotalMemory = deviceTotalMemory;
	}

	public String getIsKeyboardDeployed()
	{
		return isKeyboardDeployed;
	}

	public void setIsKeyboardDeployed(String isKeyboardDeployed)
	{
		this.isKeyboardDeployed = isKeyboardDeployed;
	}

	public String getIsKeyboardPersent()
	{
		return isKeyboardPersent;
	}

	public void setIsKeyboardPersent(String isKeyboardPersent)
	{
		this.isKeyboardPersent = isKeyboardPersent;
	}

	public String getIsownCamera()
	{
		return isownCamera;
	}

	public void setIsownCamera(String isownCamera)
	{
		this.isownCamera = isownCamera;
	}

	public String getPowerSource()
	{
		return powerSource;
	}

	public void setPowerSource(String powerSource)
	{
		this.powerSource = powerSource;
	}

	public String getUserPkId()
	{
		return userPkId;
	}

	public void setUserPkId(String userPkId)
	{
		this.userPkId = userPkId;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getSystemVersion()
	{
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion)
	{
		this.systemVersion = systemVersion;
	}

	public HashMap<String, String> toMap()
	{
		map = new HashMap<String, String>();
		putEntry("belongedBusiness", getBelongedBusiness());
		putEntry("deviceFirmwareVersion", getDeviceFirmwareVersion());
		putEntry("deviceHardwareVersion", getDeviceHardwareVersion());
		putEntry("deviceName", getDeiviceName());
		putEntry("deviceShowratio", getDeviceShowratio());
		putEntry("deviceTotalMemory", getDeviceTotalMemory());
		putEntry("deviceUniqueid", getDeviceUniqueid());
		putEntry("iskeyboardDeployed", getIsKeyboardDeployed());
		putEntry("netState", getNetState());
		putEntry("powerSource", getPowerSource());
		putEntry("isownCamera", getIsownCamera());
		putEntry("phoneNumber", getPhoneNumber());
		return map;
	}

	private void putEntry(String key, String value)
	{
		if (value != null)
		{
			map.put(key, value);
		}
	}

	private HashMap<String, String> map;
}
