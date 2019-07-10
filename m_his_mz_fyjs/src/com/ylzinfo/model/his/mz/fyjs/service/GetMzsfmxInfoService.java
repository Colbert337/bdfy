package com.ylzinfo.model.his.mz.fyjs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @description: ��ȡ�����շѽ�����ϸ
 * @author wujf
 * @date: 2015-8-16 ����08:25:51
 */
public class GetMzsfmxInfoService {
	
	private Logger logger = null;

	
	public GetMzsfmxInfoService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainGetMzsfmxInfo(Map params) throws SSTException{
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[��ȡ�շ���ϸ]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "��ʼ *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("012");
			Map map = ws.callWs(params);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "δ֪����";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("δ��ѯ���շ���ϸ");
				throw new SSTException("δ��ѯ���շ���ϸ");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle + "����ʱ�䣺" + endTime);
			logger.info("************" + funcTitle + "����************");
			logger.info(funcTitle + "���ʱ:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse(vt);
		} catch (SSTException e) {
			logger.error(funcTitle + "����" + e.getMessage());
			throw new SSTException(funcTitle + "����"  + e.getMessage(), e);
		}
	}
	
}
