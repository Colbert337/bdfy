package com.ylzinfo.model.his.zy.yjjcz.config;

/* *
 *������AlipayConfig
 *���ܣ�����������
 *��ϸ�������ʻ��й���Ϣ������·��
 *�汾��3.3
 *���ڣ�2012-08-10
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
	
 *��ʾ����λ�ȡ��ȫУ����ͺ��������ID
 *1.������ǩԼ֧�����˺ŵ�¼֧������վ(www.alipay.com)
 *2.������̼ҷ���(https://b.alipay.com/order/myOrder.htm)
 *3.�������ѯ���������(PID)��������ѯ��ȫУ����(Key)��

 *��ȫУ����鿴ʱ������֧�������ҳ��ʻ�ɫ��������ô�죿
 *���������
 *1�������������ã������������������������
 *2���������������ԣ����µ�¼��ѯ��
 */
public class AlipayConfig {
	
	 /**
     * ֧�����ṩ���̻��ķ����������URL(��)
     */
    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
    
    //���������ID����2088��ͷ��16λ��������ɵ��ַ���
	public static String partner = "2088711020588244";//�������
	public static String key = "jhreljljheiyi67nzhvceuj8kbe0em38";//�������
	//�տ�֧�����˺ţ�һ��������տ��˺ž���ǩԼ�˺�
	public static String seller_email = "";
	
	public static final String YLZ2ForwarderUrl = "http://10.193.17.31:7001/hissst/ForwarderServlet?";// ��������������������·��
	public static final String Forwarder2YLZUrl= "http://10.2.0.140:7003/hissst/AlipayServlet?";// ��������������������·��
	
	// �����ã�����TXT��־�ļ���·��
	public static String log_path = "C:\\ALIPAY\\";
	
	// �ַ������ʽ Ŀǰ֧�� gbk �� utf-8
	public static String input_charset = "utf-8";
	
	// ǩ����ʽ �����޸�
	public static String sign_type = "MD5";
}
