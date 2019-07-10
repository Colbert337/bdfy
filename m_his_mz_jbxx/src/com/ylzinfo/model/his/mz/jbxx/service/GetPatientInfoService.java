package com.ylzinfo.model.his.mz.jbxx.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.mz.jbxx.GetPatientInfoModelMatrix;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @describe :��ȡ���˻�����Ϣ
 * @classname:GetPatientInfoService
 * @author :Lan
 * @date :2018-3-29
 */
public class GetPatientInfoService extends GetPatientInfoModelMatrix {

	private Logger logger = null;

	public GetPatientInfoService() {
		logger = Logger.getLogger(this.getClass().getName());
	}

	@Override
	public Vector getPatientInfo(Map para) throws SSTException {
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[��ȡ���˻�����Ϣ]";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + className + funcTitle + "��ʼ *********");
			long startTime = System.currentTimeMillis();

			CallHisService ws = new CallHisService("001");
			Map map = ws.callWs(para);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "δ֪����";
				}
				throw new SSTException(errmsg);
			}
			
			String kpzt00 = (String)map.get("kpzt00");
			if(!kpzt00.equals("1")) {
				throw new SSTException("�þ��￨��Ч");
			}
			
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				throw new SSTException("��ȡ�������˻�����Ϣ��");
			}
			
			String xbiemc = (String)((HashMap<String, String>)vt.get(0)).get("xbiemc");
			if(xbiemc.equals("��")) {
				((HashMap<String, String>)vt.get(0)).put("xbie00", "1");
			} else if(xbiemc.equals("Ů")) {
				((HashMap<String, String>)vt.get(0)).put("xbie00", "0");
			} else {
				((HashMap<String, String>)vt.get(0)).put("xbie00", "9");
			}
			
			para.put("brid00", ((HashMap<String, String>)vt.get(0)).get("brid00"));
			
			ws = new CallHisService("004");
			map = ws.callWs(para);
			
			((HashMap<String, String>)vt.get(0)).put("yjjzh0", (String)map.get("yjjzh0"));
			((HashMap<String, String>)vt.get(0)).put("yjjye0", (String)map.get("yjjye0"));
			
			// ��װbody
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle + "����ʱ�䣺" + endTime);

			logger.info("************" + funcTitle + "����************");
			logger.info(funcTitle + "���ʱ:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return vt;
		} catch (SSTException e) {
			e.printStackTrace();
			logger.error(funcTitle + "����" + e.getMessage());
			throw new SSTException(funcTitle + "����"  + e.getMessage(), e);
		}
	}
	
	private static String dealName(String obj) {
		if (obj == null || "".equals(obj)) {
			return "";
		}
		if (obj.length() < 2)
			return (obj.substring(0, 1) + "*");

		// �������⴦��
		return (obj.substring(0, 1) + "*" + obj.substring(2, obj.length()))
				.trim();
	}
}
