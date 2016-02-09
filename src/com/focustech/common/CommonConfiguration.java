package com.focustech.common;

import android.content.Context;

import com.focustech.common.mob.MobConstants;
import com.tencent.android.tpush.XGPushConfig;

/**********************************************************
 * @文件名称：CommonConfiguration.java
 * @文件作者：xiongjiangwei
 * @创建时间：2015年9月14日 下午3:31:26
 * @文件描述：公共模块配置项
 * @修改历史：2015年9月14日创建初始版本
 **********************************************************/
public final class CommonConfiguration
{

	public enum PushWay
	{
		TENCENTXG, GCM
	}

	final Context context;
	final String reLoginAction;
	final String productName;
	final String productChannel;
	final String pushway;
	final String userPkId;

	private CommonConfiguration(Builder builder)
	{
		context = builder.context;
		reLoginAction = builder.reLoginAction;
		productName = builder.productName;
		productChannel = builder.productChannel;
		pushway = builder.pushWay;
		userPkId = builder.userPkId;
	}

	public static class Builder
	{
		private Context context;
		private String reLoginAction;
		private String productName;
		private String productChannel = MobConstants.DEFAULT_PRODUCT_CHANNEL;

		//给出默认值
		private String pushWay = "0";
		private String userPkId ;

		public Builder setPushWay(PushWay pushWay)
		{
			// 指定Android设备的推送方式0:信鸽1:GCM，不指定默认为0
			switch (pushWay)
			{
			case TENCENTXG:
				this.pushWay = "0";
				break;
			case GCM:
				this.pushWay = "1";
				break;
			default:
				break;
			}
			return this;
		}

		public Builder setUserPkId(String userPkId)
		{
			this.userPkId = userPkId;
			return this;
		}

		public Builder(Context context)
		{
			this.context = context.getApplicationContext();
			//给出默认值
			userPkId = XGPushConfig.getToken(context);
		}

		public Builder setReLoginAction(String reLoginAction)
		{
			this.reLoginAction = reLoginAction;
			return this;
		}

		public Builder setProductName(String productName)
		{
			this.productName = productName;
			return this;
		}

		public Builder setProductChannel(String productChannel)
		{
			this.productChannel = productChannel;
			return this;
		}

		public CommonConfiguration build()
		{
			return new CommonConfiguration(this);
		}
	}
}
