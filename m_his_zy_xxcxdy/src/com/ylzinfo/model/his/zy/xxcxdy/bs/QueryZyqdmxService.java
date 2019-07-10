package com.ylzinfo.model.his.zy.xxcxdy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.ylzinfo.matrix.model.his.zy.xxcxdy.QueryInpatientListModelMatrix;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class QueryZyqdmxService extends QueryInpatientListModelMatrix {
	
	private Logger logger = null;
	
	public QueryZyqdmxService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public HashMap queryInpatientList(Map param) throws SSTException {
		String errMsg = "סԺ�շ���ϸ��ѯ";
		Vector<HashMap<String, String>> vt = null;
		try {
			logger.info("************" + errMsg + "��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg + "��ʼʱ�䣺" + startTime);
			
//			String qsrq00 = (String)param.get("qsrq00");
//			param.put("qsrq00", qsrq00.substring(0,4) + "-" + qsrq00.substring(4,6) + "-" + qsrq00.substring(6,8));
//			String jzrq00 = (String)param.get("jzrq00");
//			param.put("jzrq00", jzrq00.substring(0,4) + "-" + jzrq00.substring(4,6) + "-" + jzrq00.substring(6,8));

			String qsrq00 = (String)param.get("cxrq00");
			param.put("cxrq00", qsrq00.substring(0,4) + "-" + qsrq00.substring(4,6) + "-" + qsrq00.substring(6,8));

			CallHisService ws = new CallHisService("064");
			Map map = ws.callWs(param);
			
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "��ȡסԺ�շ���ϸ����";
				}
				throw new SSTException(errmsg);
			}
			
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("δ��ѯ��סԺ�շ���ϸ");
				throw new SSTException("δ��ѯ��סԺ�շ���ϸ");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg + "����ʱ�䣺" + endTime);
			logger.info("************" + errMsg + "����************");
			logger.info(errMsg + "���ʱ:" + DateUtil.consumeTime2String(startTime, endTime));
			HashMap evtBody = new HashMap();
			evtBody.put("retrieve", vt);
			return evtBody;
		} catch (Exception e) {
			logger.error("סԺ�շ���ϸ��ѯ����:" + e.getMessage());
			throw new SSTException("סԺ�շ���ϸ��ѯ����:" + e.getMessage());
		}
	}
}
