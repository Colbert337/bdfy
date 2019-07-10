package com.ylzinfo.model.his.zy.jbxx.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.ylzinfo.his.zy.service.PatientService;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class PatientServiceImpl extends PatientService{
	
	/**
	 * ��־����
	 */
	private Logger logger = null;

	
	public PatientServiceImpl() {
		logger = Logger.getLogger(this.getClass().getName());
	}

	@Override
	public Map getPatientInfo(Map para) throws SSTException {
		String funcTitle = "[��ȡסԺ���˻�����Ϣ]";
		String className = this.getClass().getName();
		Vector<HashMap<String, String>> vt = new Vector<HashMap<String, String>>();
		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			logger.info("*********" + className + funcTitle + "��ʼ *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("019");
			Map map = ws.callWs(para);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "��ȡסԺ���˻�����Ϣ����";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String,String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("��ȡ����סԺ��Ϣ");
				throw new SSTException("��ȡ����סԺ��Ϣ");
			}
			
			for (HashMap<String, String> ele : vt) {
				hm.putAll(ele);
			}

			long endTime = System.currentTimeMillis();
			logger.info(funcTitle + "����ʱ�䣺" + endTime);
			logger.info("************" + funcTitle + "����************");
			logger.info(funcTitle + "���ʱ:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return hm;
		} catch (SSTException e) {
			e.printStackTrace();
			logger.error(funcTitle + "����" + e.getMessage());
			throw new SSTException(funcTitle + "����"  + e.getMessage(), e);
		}
	}
	
	private static String dealName(String obj)
	   {
	      if (obj == null || "".equals(obj))
	      {
	         return "";
	      }
	      if (obj.length() < 2 )
	    	  return (obj.substring(0, 1) + "*");
	    	 
	      // �������⴦��
	      return (obj.substring(0, 1) + "*" + obj.substring(2, obj.length())).trim();
	   }
}
