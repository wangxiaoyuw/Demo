package com.boke.common;

/**
* @ClassName: CommonSubCode
* @Description: 返回码 码
* @author sunyaming
* @date 2017年1月11日 下午8:58:53
 */
public enum CommonSubCode {
	ISP_UNKNOW_ERROR(20000,"服务暂不可用(业务系统不可用)"),
	AOP_UNKNOW_ERROR(20000,"服务暂不可用(网关自身的未知错误)"),
	AOP_INVALID_AUTH_TOKEN(20001,"无效的访问令牌"),
	AOP_AUTH_TOKEN_TIME_OUT(20001,"访问令牌已过期"),
	AOP_INVALID_APP_AUTH_TOKEN(20001,"无效的应用授权令牌"),
	AOP_APP_AUTH_TOKEN_TIME_OUT(20001,"应用授权令牌已过期"),
	ISV_MISSING_METHOD(40001,"缺少方法名参数"),
	ISV_MISSING_SIGNATURE(40001,"缺少签名参数"),
	ISV_MISSING_SIGNATURE_KEY(40001,"缺少签名配置"),
	ISV_MISSING_APP_ID(40001,"缺少APPID参数"),
	ISV_MISSING_TIMESTAMP(40001,"缺少时间戳参数"),
	ISV_MISSING_VERSION(40001,"缺少版本参数"),
	ISV_INVALID_PARAMETER(40002,"参数无效"),
	ISV_UPLOAD_FAIL(40002,"文件上传失败"),
	ISV_INVALID_FILE_EXTENSION(40002,"文件扩展名无效"),
	ISV_INVALID_FILE_SIZE(40002,"文件大小无效"),
	ISV_INVALID_METHOD(40002,"不存在的方法名"),
	ISV_INVALID_FORMAT(40002,"无效的数据格式"),
	ISV_INVALID_SIGNATURE_TYPE(40002,"无效的签名类型"),
	ISV_INVALID_SIGNATURE(40002,"无效签名"),
	ISV_INVALID_ENCRYPT(40002,"解密异常"),
	ISV_INVALID_APP_ID(40002,"无效的APPID参数"),
	ISV_INVALID_TIMESTAMP(40002,"非法的时间戳参数"),
	ISV_INVALID_CHARSET(40002,"字符集错误"),
	ISV_DECRYPTION_ERROR_NOT_VALID_ENCRYPT_KEY(40002,"解密出错, 未配置加密密钥或加密密钥格式错误"),
	ISV_DECRYPTION_ERROR_UNKNOWN(40002,"解密出错，未知异常"),
	ISV_NOT_SUPPORT_APP_AUTH(40002,"本接口不支持第三方代理调用"),
	ISV_INSUFFICIENT_USER_PERMISSIONS(40006,"用户权限不足"),
	INVALID_PARAM(41000,"参数非法"),
	CODE_OVER_LIMIT(41001,"今日获取验证码已超限"),
	CODE_ERROR(41002,"验证码错误"),
	REG_FAIL(41003,"注册失败"),
	LOGIN_FAIL(41004,"登录失败"),
	INVALID_NICKNAME(41005,"昵称要求1-16位，支持中英文、数字"),
	INVALID_REALNAME(41006,"真实姓名限1-6位中文"),
	INVALID_PASSWORD(41007,"密码必须是字母和数字哟，请确认输入"),
	FAVORITE_LIMIT(41008,"收藏超过上限"),
	CODE_30_LIMIT(41009,"30分钟内获取验证码过于频繁"),
	INVALID_MOBILE(41010,"手机号无效"),
	MOBILE_EXISTS(41011,"手机号已注册，请直接登录"),
	MOBILE_NOT_FOUND(41012,"该账号不存在，请确认后输入"),
	REPEAT_NICKNAME(41013,"昵称已被别人注册，请修改！"),
	REPEAT_MOBILE(41014,"该账号已存在"),
	QQ_LOGIN_ERROR(41015,"QQ登陆失败"),
	WEIXIN_LOGIN_ERROR(41016,"微信登陆失败"),
	WEIBO_LOGIN_ERROR(41017,"微博登陆失败"),
	SENSITIVE_WORDS(41018,"昵称不能包含敏感词汇"),
	SENSITIVE_WORDS_REAL(41019,"姓名不能包含敏感词汇"),
	MOBILE_HAS_BIND(41020,"手机号已被绑定"),
	INVALID_CODE(0,"提示码参数非法");
	private CommonSubCode(int key, String value) {
		this.key=key;
		this.value=value;
	}
	private int key;
	private String value;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public static CommonSubCode get(int  key) {
		for (CommonSubCode dot : CommonSubCode.values()) {
			if (key == dot.getKey()) {
				return dot;
			}
		}
		return INVALID_CODE;
	}


	public static CommonSubCode get(String value) {
		for (CommonSubCode dot : CommonSubCode.values()) {
			if (value.equals(dot.getValue())) {
				return dot;
			}
		}
		return INVALID_CODE;
	}

}
