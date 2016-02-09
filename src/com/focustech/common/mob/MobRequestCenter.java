package com.focustech.common.mob;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import com.focustech.common.CommonConfigurationHelper;
import com.focustech.common.http.CommonJsonHttpResponseHandler;
import com.focustech.common.http.FocusClient;
import com.focustech.common.listener.DisposeDataListener;
import com.focustech.common.module.Msg;
import com.focustech.common.module.PhoneConfigParams;
import com.focustech.common.module.response.BroadCast;
import com.focustech.common.module.response.MobBaseResponse;
import com.focustech.common.module.response.PersonalMessage;
import com.focustech.common.module.response.Update;
import com.focustech.common.util.MobileUtil;
import com.focustech.common.util.Utils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tencent.android.tpush.XGPushConfig;

/**********************************************************
 * @文件名称：MobRequestCenter.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年5月28日 上午9:13:23
 * @文件描述：公共模块接口请求中心
 * @修改历史：2015年5月28日创建初始版本
 **********************************************************/
public class MobRequestCenter
{
	private static void initCommonParams(RequestParams params)
	{
		params.put("productName", CommonConfigurationHelper.getInstance().getProductName());
		params.put("platformName", MobConstants.PLATFORM_NAME);
		params.put("productChannel", CommonConfigurationHelper.getInstance().getProductChannel());
		params.put("productVersion", Utils.getAppVersionName(CommonConfigurationHelper.getInstance().getContext()));
		//params.put("userPkId", XGPushConfig.getToken(CommonConfigurationHelper.getInstance().getContext()));
		params.put("userPkId", CommonConfigurationHelper.getInstance().getUserPkId());
	}

	private static void initCommonParams(HashMap<String, String> params)
	{
		params.put("productName", CommonConfigurationHelper.getInstance().getProductName());
		params.put("platformName", MobConstants.PLATFORM_NAME);
		params.put("productChannel", CommonConfigurationHelper.getInstance().getProductChannel());
		params.put("productVersion", Utils.getAppVersionName(CommonConfigurationHelper.getInstance().getContext()));
		// params.put("userPkId", XGPushConfig.getToken(CommonConfigurationHelper.getInstance().getContext()));

		params.put("userPkId", CommonConfigurationHelper.getInstance().getUserPkId());
		params.put("pushWay", CommonConfigurationHelper.getInstance().getPushWay());
	}

	/**
	 * 注册设备
	 * @param listener
	 */
	public static void register(DisposeDataListener listener)
	{
		HashMap<String, String> paramsMap = new HashMap<String, String>();
		initCommonParams(paramsMap);
		paramsMap.put("pushType", "1,2,3,4");
		PhoneConfigParams phoneConfigParams = MobileUtil.getMobileInfo(CommonConfigurationHelper.getInstance()
				.getContext());
		if (phoneConfigParams != null)
		{
			paramsMap.putAll(phoneConfigParams.toMap());
		}
		RequestParams params = new RequestParams(paramsMap);
		post(MobUrlConstants.USER_REGISTER, params, new CommonJsonHttpResponseHandler(listener, MobBaseResponse.class));
	}

	/**
	 * 绑定或解绑(推送用)
	 * @param appAccountName
	 * @param companyId
	 * @param operatorId
	 * @param listener
	 */
	public static void boundAccountOrNot(String appAccountName, String companyId, String operatorId,
			DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		initCommonParams(params);
		if (!Utils.isEmpty(appAccountName))
		{
			params.put("appAccountName", appAccountName);
		}
		if (!Utils.isEmpty(companyId))
		{
			params.put("companyId", companyId);
		}
		if (!Utils.isEmpty(operatorId))
		{
			params.put("operatorId", operatorId);
		}
		post(MobUrlConstants.BOUND_ACCOUNT, params, new CommonJsonHttpResponseHandler(listener, MobBaseResponse.class));
	}

	/**
	 * 更新推送设置项状态
	 * @param companyId
	 * @param operatorId
	 * @param pushType
	 * @param pushState
	 * @param listener
	 */
	public static void updatePushSetting(String companyId, String operatorId, String pushType, String pushState,
			DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		initCommonParams(params);
		params.put("companyId", companyId);
		params.put("operatorId", operatorId);
		params.put("pushType", pushType);
		params.put("pushState", pushState);
		post(MobUrlConstants.UPDATE_PUSH_SETTING, params, new CommonJsonHttpResponseHandler(listener,
				MobBaseResponse.class));
	}

	/**
	 * 更新消息状态
	 * @param companyId
	 * @param operatorId
	 * @param messageId
	 * @param listener
	 */
	public static void updateMessageStatus(String companyId, String operatorId, String messageId,
			DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		params.put("companyId", companyId);
		params.put("operatorId", operatorId);
		params.put("messageId", messageId);
		post(MobUrlConstants.UPDATE_MESSAGE_STATUS, params, new CommonJsonHttpResponseHandler(listener,
				MobBaseResponse.class));
	}

	/**
	 * 获取未读消息数量(包含设置项状态)
	 * @param companyId
	 * @param operatorId
	 * @param listener
	 */
	public static void getUnReadMessageNum(String companyId, String operatorId, DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		initCommonParams(params);
		params.put("companyId", companyId);
		params.put("operatorId", operatorId);
		post(MobUrlConstants.GET_UNREAD_MESSAGE_NUM, params, new CommonJsonHttpResponseHandler(listener,
				PersonalMessage.class));
	}

	/**
	 * 获取消息列表
	 * @param companyId
	 * @param operatorId
	 * @param pushType
	 * @param pageIndex
	 * @param pageSize
	 * @param listener
	 */
	public static void getAllMessages(String companyId, String operatorId, String pushType, int pageIndex,
			int pageSize, DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		params.put("companyId", companyId);
		params.put("operatorId", operatorId);
		params.put("productName", CommonConfigurationHelper.getInstance().getProductName());
		params.put("platformName", MobConstants.PLATFORM_NAME);
		params.put("pushInfo", XGPushConfig.getToken(CommonConfigurationHelper.getInstance().getContext()));
		params.put("pushType", pushType);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		post(MobUrlConstants.GET_ALL_MESSAGES, params, new CommonJsonHttpResponseHandler(listener, BroadCast.class));
	}

	/**
	 * 检查更新请求
	 * @param listener
	 */
	public static void checkUpdate(DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		params.put("productName", CommonConfigurationHelper.getInstance().getProductName());
		params.put("productVersion", Utils.getAppVersionName(CommonConfigurationHelper.getInstance().getContext()));
		params.put("productChannel", CommonConfigurationHelper.getInstance().getProductChannel());
		params.put("platformName", MobConstants.PLATFORM_NAME);
		post(MobUrlConstants.CHECK_VERSION, params, new CommonJsonHttpResponseHandler(listener, Update.class));
	}

	/**
	 * 发送反馈信息
	 * @param feedbackContent
	 * @param listener
	 */
	public static void feedback(String feedbackContent, DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		initCommonParams(params);
		params.put("submitType", Msg.TYPE_TEXT);
		params.put("submitInfo", feedbackContent);
		post(MobUrlConstants.SENT_FEEDBACK, params, new CommonJsonHttpResponseHandler(listener, MobBaseResponse.class));
	}

	/**
	 * 免打扰模式设置项状态
	 * @param companyId
	 * @param operatorId
	 * @param notDisturbStatus
	 * @param listener
	 */
	public static void updateNotDisturbSetting(String companyId, String operatorId, String notDisturbStatus,
			DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		initCommonParams(params);
		params.put("companyId", companyId);
		params.put("operatorId", operatorId);
		params.put("notDisturbStatus", notDisturbStatus);
		post(MobUrlConstants.UPDATE_NOTDISTURB_STATUS, params, new CommonJsonHttpResponseHandler(listener,
				MobBaseResponse.class));
	}

	/**
	 * 发送用户行为
	 * @param eventJson
	 * @param listener
	 */
	public static void sendUserCollect(String eventJson, DisposeDataListener listener)
	{
		RequestParams params = new RequestParams();
		initCommonParams(params);
		params.put("userInfoData", eventJson);
		post(MobUrlConstants.USER_ACTION_COLLECT, params, new CommonJsonHttpResponseHandler(listener,
				MobBaseResponse.class));
	}

	/**
	 * 上传异常日志 
	 */
	public static void uploadExceptionFile(DisposeDataListener listener, File file)
	{
		if (!file.exists())
		{
			return;
		}
		RequestParams params = new RequestParams();
		try
		{
			// 临时参数，待确定
			params.put("exceptionFile", file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		// 临时请求路径，待确定
		// FocusClient.post(UrlConstants.UPLOAD_FILE, params, new CommonJsonHttpResponseHandler(listener,
		// BaseResponse.class));
	}

	private static void post(String requestUrl, RequestParams params, AsyncHttpResponseHandler responseHandler)
	{
		params.put("versionCode", Utils.getAppVersionCode(CommonConfigurationHelper.getInstance().getContext()));
		FocusClient.post(requestUrl, params, responseHandler);
	}
}
