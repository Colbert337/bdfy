package com.ylzinfo.model.his.mz.xxcx.server.mzsfcx;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class MzsfcxServer {
	
	private Logger logger;
	
	public MzsfcxServer() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String getSfjlByCardno(Map param) throws SSTException {
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[�շѼ�¼��ѯ]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "��ʼ *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("");
			Map map = ws.callWs(param);
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("δ��ѯ���շѼ�¼");
				throw new SSTException("δ��ѯ���շѼ�¼");
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
	
	public String getSfmxByDjlsh(Map param) throws SSTException {
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[�շ���ϸ��ѯ]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "��ʼ *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("");
			Map map = ws.callWs(param);
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
