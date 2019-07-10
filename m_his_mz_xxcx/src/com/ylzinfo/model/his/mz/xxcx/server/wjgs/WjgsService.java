package com.ylzinfo.model.his.mz.xxcx.server.wjgs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;

public class WjgsService {
	
	public Logger logger = null;
	
	public WjgsService(){
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String queryPrice(Map param) throws SSTException{
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[��۹�ʾ��ѯ]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "��ʼ *********");
			long startTime = System.currentTimeMillis();
			
			
			
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
