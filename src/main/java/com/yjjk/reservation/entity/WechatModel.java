package com.yjjk.reservation.entity;

import lombok.Data;

/**
 * @author CentreS
 * @Description: 微信返回值
 * @create 2019-06-20
 */
@Data
public class WechatModel {

	/**
	 * 用户唯一标识
	 */
	public String openid;

	/**
	 * 会话密钥
 	 */
	public String session_key;

	/**
	 * 用户在开放平台的唯一标识符
	 */
	public String unionid;

	/**
	 * 错误编号
	 */
	public String errcode;

	/**
	 * 错误内容
	 */
	public String errmsg;

}
