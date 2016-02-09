package com.focustech.common.mob;

/**********************************************************
 * @�ļ����ƣ�MobUrlConstants.java
 * @�ļ����ߣ�xiongjiangwei
 * @����ʱ�䣺2015��5��27�� ����4:05:36
 * @�ļ���������������ӿ������ַ����
 * @�޸���ʷ��2015��5��27�մ�����ʼ�汾
 **********************************************************/
public class MobUrlConstants
{
	/**
	 * ����ͷ��ַ
	 * ��ʽ��Url	:http://mic.s.wfeature.com
	 * P��Url		:http://mic.sp.wfeature.com
	 * ���԰�Url	:http://192.168.16.254:5010
	 */
	public static final String BASE_HOST = "http://mic.s.wfeature.com";
	/**
	 * ����Э������
	 */
	public static final String HOST_NAME = "/base/";

	/**
	 * ע���豸
	 */
	public static final String USER_REGISTER = BASE_HOST + HOST_NAME + "broadcast/registerEquipment";
	/**
	 * �󶨻����˺�
	 */
	public static final String BOUND_ACCOUNT = BASE_HOST + HOST_NAME + "fbc/bindAccountOrNot";
	/**
	 * �������Ϳ���
	 */
	public static final String UPDATE_PUSH_SETTING = BASE_HOST + HOST_NAME + "pushManager/updatePushSetting";
	/**
	 * �����ģʽ���ÿ���
	 */
	public static final String UPDATE_NOTDISTURB_STATUS = BASE_HOST + HOST_NAME + "pushManager/updateNotDisturbStatus";
	/**
	 * ����������Ϣ�Ѷ�
	 */
	public static final String UPDATE_MESSAGE_STATUS = BASE_HOST + HOST_NAME + "pushManager/updateMessageStatus";
	/**
	 * ��ȡδ����Ϣ����
	 */
	public static final String GET_UNREAD_MESSAGE_NUM = BASE_HOST + HOST_NAME + "pushManager/getUnReadMessageNum";
	/**
	 * ��ȡ������Ϣ�б�
	 */
	public static final String GET_ALL_MESSAGES = BASE_HOST + HOST_NAME + "pushManager/getAllMessages";
	/**
	 * ������
	 */
	public static final String CHECK_VERSION = BASE_HOST + HOST_NAME + "product/checkVersion";
	/**
	 * �ύ������Ϣ
	 */
	public static final String SENT_FEEDBACK = BASE_HOST + HOST_NAME + "fbInterface/submitInfo";
	/**
	 * �û���Ϊ��Ϣ
	 */
	public static final String USER_ACTION_COLLECT = BASE_HOST + HOST_NAME + "userActInfo/collect";

}
