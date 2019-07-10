/**
 * 
 */
package com.ylzinfo.model.his.mz.ynkfk.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * 
 * @description:  ��ȡ������ϵ�绰
 * @author wujf
 * @date: 2015-9-15 ����07:15:37
 */
public class GetArchiveTelService {
	
	private Logger logger = null;
	
	public GetArchiveTelService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainGetArchiveTel(Map params) throws SSTException{
		
		Vector vt = GetArchiveTel(params);
		return SoapUtil.getSoapResponse(vt);
	}
	
	private Vector GetArchiveTel(Map params) throws SSTException{
		String errMsg = "��������ж�";
		Vector vt = new Vector<HashMap<String, String>>();
		try{
			logger.info("************"+errMsg+"��ʼ ************");
			
			String sfbkcz = (String)params.get("sfbkcz");
			if(sfbkcz.equals("0")) {
				String xbie00 = (String)params.get("xbie00");
				if(xbie00.equals("1")) {
					params.put("xbie00", "M");
				} else {
					params.put("xbie00", "F");
				}
				
				String csrq00 = (String)params.get("csrq00");
				params.put("csrq00", csrq00.substring(0, 4) + "-" + csrq00.substring(4, 6) + "-" + csrq00.substring(6, 8));
				
				CallHisService ws = new CallHisService("048");
				Map map = ws.callWs(params);
				String result = (String)map.get("result");
				if(!result.equals("0")) {
					String errmsg = (String)map.get("errmsg");
					if(errmsg == null || errmsg.equals("")) {
						errmsg = "δ֪����";
					}
					throw new SSTException(errmsg);
				}
			} else {
				CallHisService ws = new CallHisService("062");
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
					logger.info("��ȡ������ʧ��");
					throw new SSTException("��ȡ������ʧ��");
				}
			}
			
			logger.info("************"+errMsg+"����************");
			return vt;
		}catch(Exception e){
			logger.error(errMsg + "����" + e.getMessage());
			throw new SSTException(errMsg + "����" +e.getMessage());
		}
	}
}
