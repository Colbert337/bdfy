package com.ylzinfo.model.his.mz.mzyy.bs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class DoLockSignalService {
	private Logger log=null;

	public DoLockSignalService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String DoLockSignal(Map para) throws SSTException {
		try {
			log.info("���ſ�ʼ...");
			long startTime = System.currentTimeMillis();
			log.info("���ſ�ʼʱ�䣺" + startTime);
			
			para.put("yyrq00", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
			CallHisService ws = new CallHisService("041");
			Map map = ws.callWs(para);
			
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "����ʧ��";
				}
				throw new SSTException(errmsg);
			}
			
			long endTime = System.currentTimeMillis();
			log.info("���Ž���...");
			log.info("���ź�ʱ:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse((HashMap<String, String>)map);
		}catch (Exception e) {
			log.info("���ų���:" + e.getMessage());
			throw new SSTException("���ų���:" + e.getMessage());
		}
	}
}
