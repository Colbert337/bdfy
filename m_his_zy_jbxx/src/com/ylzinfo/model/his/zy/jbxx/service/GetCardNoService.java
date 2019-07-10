package com.ylzinfo.model.his.zy.jbxx.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.jbxx.util.MyCardUtil;
/**
 * 
* @Project: m_his_zy_jbxx
* @ClassName: GetCardNoService 
* @Description: ���ܿ���ҵ���
* @author huangjj
* @date 2015-4-3 ����2:16:30 
*
 */
public class GetCardNoService {
	private Logger logger = null;
	
	public GetCardNoService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	public String readCardNo(String encodeCardNo,String zdbh00) throws SSTException{
		String return_cardno = "";
		try {
			for(int i = 0; i<21; i++)
			{
				return_cardno =  MyCardUtil.ICReadCardNo(encodeCardNo); 
				logger.info("���ܷ����ն�ID��"+return_cardno.substring(1, 13).trim());
				logger.info("ǰ̨�����ն�ID��"+zdbh00);
				if(return_cardno.substring(1, 13).trim().equals(zdbh00.trim()))
				{
					logger.info("�ն�ID���,"+"ѭ��������"+i);
					break;
				}				
			}  
			Vector vt = new Vector();
			Map param = new HashMap();
			param.put("cxdm00", return_cardno.substring(13));
			param.put("zdbh00", return_cardno.substring(1,13));
			vt.add(param);
			return SoapUtil.getSoapResponse(vt);
		} 
		catch(Exception e){
			e.printStackTrace();
			throw new SSTException("��ȡ����ʧ��");
		}
	}
}
