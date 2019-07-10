package com.ylzinfo.model.his.zy.yjjcz.config;

public class wxPayConfig {
	
	//微信支付商户开通后 微信会提供appid和appsecret和商户号partner
	public static String appid = "wxad96bab515820a09";
	public static String appsecret = "";
	public static String partner = "1313572801";
	//这个参数partnerkey是在商户后台配置的一个32位的key,微信商户平台-账户设置-安全设置-api安全
	public static String partnerkey = "20141205beijingdaxueguojiyiyuanA";
	//openId 是微信用户针对公众号的标识，授权的部分这里不解释
	public static String openId = "";
	public static String forwarderUrl = "http://10.193.17.31:7001/hissst/WxForwarderServlet?";
	public static String unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";	
	public static String orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
	public static String closeorderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
	public static String refundqueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
	public static String downloadbillUrl = "https://api.mch.weixin.qq.com/pay/downloadbill";
	public static String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
}
