package com.ylzinfo.model.his.zy.yjjcz.config;

public class wxPayConfig {
	
	//΢��֧���̻���ͨ�� ΢�Ż��ṩappid��appsecret���̻���partner
	public static String appid = "wxad96bab515820a09";
	public static String appsecret = "";
	public static String partner = "1313572801";
	//�������partnerkey�����̻���̨���õ�һ��32λ��key,΢���̻�ƽ̨-�˻�����-��ȫ����-api��ȫ
	public static String partnerkey = "20141205beijingdaxueguojiyiyuanA";
	//openId ��΢���û���Թ��ںŵı�ʶ����Ȩ�Ĳ������ﲻ����
	public static String openId = "";
	public static String forwarderUrl = "http://10.193.17.31:7001/hissst/WxForwarderServlet?";
	public static String unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";	
	public static String orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
	public static String closeorderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
	public static String refundqueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
	public static String downloadbillUrl = "https://api.mch.weixin.qq.com/pay/downloadbill";
	public static String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
}
