package com.ylzinfo.model.his.zy.jbxx.service;

import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.jbxx.dao.ZdpzDao;

/**
 * 
* @Project: m_his_mz_jbxx
* @ClassName: GetZdpzService 
* @Description: ��ȡ�ն�������Ϣҵ���
* @author Administrator
* @date 2015-3-25 ����10:17:41 
*
 */
public class GetZdpzService {
	public Logger logger = null;
	public GetZdpzService(){
		logger = Logger.getLogger(this.getClass().getName());
	}
	public String getZdpz(Map param) throws SSTException{
		ZdpzDao dao = new ZdpzDao();
		Map result = null;
		try{
			logger.info("<<<<<<<<<< getZdp��ȡ�ն�������Ϣ��ʼ>>>>>>>>");
			result = dao.getById(param);
			if(result==null||result.size()==0){
				throw new SSTException("û���ҵ��ն�������Ϣ�������ն˺��Ƿ���ȷ��");
			}
			logger.info("<<<<<<<<<< getZdpz��ȡ�ն�������Ϣ����>>>>>>>>");
			Vector vt = new Vector();
			vt.add(result);
			return SoapUtil.getSoapResponse(vt);
		}catch(Exception e){
			throw new SSTException("��ȡ�ն�������Ϣʧ��");
		}		
	}
}
