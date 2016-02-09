package com.focustech.common.mob;

/**********************************************************
 * @文件名称：MobUrlConstants.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年5月27日 下午4:05:36
 * @文件描述：公共服务接口请求地址常量
 * @修改历史：2015年5月27日创建初始版本
 **********************************************************/
public class MobUrlConstants
{
	/**
	 * 请求头地址
	 * 正式版Url	:http://mic.s.wfeature.com
	 * P版Url		:http://mic.sp.wfeature.com
	 * 测试版Url	:http://192.168.16.254:5010
	 */
	public static final String BASE_HOST = "http://mic.s.wfeature.com";
	/**
	 * 请求协议名称
	 */
	public static final String HOST_NAME = "/base/";

	/**
	 * 注册设备
	 */
	public static final String USER_REGISTER = BASE_HOST + HOST_NAME + "broadcast/registerEquipment";
	/**
	 * 绑定或解绑账号
	 */
	public static final String BOUND_ACCOUNT = BASE_HOST + HOST_NAME + "fbc/bindAccountOrNot";
	/**
	 * 设置推送开关
	 */
	public static final String UPDATE_PUSH_SETTING = BASE_HOST + HOST_NAME + "pushManager/updatePushSetting";
	/**
	 * 免打扰模式设置开关
	 */
	public static final String UPDATE_NOTDISTURB_STATUS = BASE_HOST + HOST_NAME + "pushManager/updateNotDisturbStatus";
	/**
	 * 设置推送消息已读
	 */
	public static final String UPDATE_MESSAGE_STATUS = BASE_HOST + HOST_NAME + "pushManager/updateMessageStatus";
	/**
	 * 获取未读消息数量
	 */
	public static final String GET_UNREAD_MESSAGE_NUM = BASE_HOST + HOST_NAME + "pushManager/getUnReadMessageNum";
	/**
	 * 获取所有消息列表
	 */
	public static final String GET_ALL_MESSAGES = BASE_HOST + HOST_NAME + "pushManager/getAllMessages";
	/**
	 * 检查更新
	 */
	public static final String CHECK_VERSION = BASE_HOST + HOST_NAME + "product/checkVersion";
	/**
	 * 提交反馈信息
	 */
	public static final String SENT_FEEDBACK = BASE_HOST + HOST_NAME + "fbInterface/submitInfo";
	/**
	 * 用户行为信息
	 */
	public static final String USER_ACTION_COLLECT = BASE_HOST + HOST_NAME + "userActInfo/collect";

}
