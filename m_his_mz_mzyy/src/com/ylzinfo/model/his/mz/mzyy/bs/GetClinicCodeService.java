package com.ylzinfo.model.his.mz.mzyy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class GetClinicCodeService {
	private Logger log=null;

	public GetClinicCodeService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String GetClinicCode(Map para) throws SSTException {
//		Vector vt = new Vector<HashMap<String, String>>();
		try {
			log.info("��ȡ����ſ�ʼ...");
			long startTime = System.currentTimeMillis();
			log.info("��ȡ����ſ�ʼʱ�䣺" + startTime);
			
//			para.put("jylsh0", DateUtil.getNowTime());
			
			CallHisService ws = new CallHisService("063");
			Map map = ws.callWs(para);
			
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "��ȡ�����ʧ��";
				}
				throw new SSTException(errmsg);
			}
			
//			map.put("jylsh0", para.get("jylsh0"));
//			vt = (Vector<HashMap<String, String>>)map.get("vt");
//			if(vt == null || vt.size() <= 0) {
//				log.error("��ȡ�����ʧ��");
//				throw new SSTException("��ȡ�����ʧ��");
//			}
			long endTime = System.currentTimeMillis();
			log.info("��ȡ����Ž���...");
			log.info("��ȡ����ź�ʱ:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse((HashMap<String, String>)map);
		}catch (Exception e) {
			log.info("��ȡ��������ݳ���:" + e.getMessage());
			throw new SSTException("��ȡ��������ݳ���:" + e.getMessage());
		}
	}
}
