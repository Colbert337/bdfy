package com.ylzinfo.model.his.zy.xxcxdy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.ylzinfo.matrix.model.his.zy.xxcxdy.QueryInpatientPrepaymentModelMatrix;

public class QueryZyyjjmxService extends QueryInpatientPrepaymentModelMatrix {
	
	private Logger logger = null;
	
	public QueryZyyjjmxService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	@Override
	public HashMap queryInpatientPrepayment(Map para) throws SSTException {
		Vector<HashMap<String, String>> vt = null;
		try{		
			logger.info("***************סԺԤ������ϸ��Ϣ��ʼ***************");
			long startTime = System.currentTimeMillis();
			logger.info("��ʼʱ�䣺" + startTime);
			
			long endTime = System.currentTimeMillis();
			logger.info("����ʱ�䣺" + endTime);
			logger.info("***************סԺԤ������ϸ��Ϣ����***************");
			HashMap evtBody = new HashMap();
			evtBody.put("retrieve", vt);
			return evtBody;
		}catch (Exception e) {
			logger.error("סԺԤ������ϸ����:" + e.getMessage());
			throw new SSTException("סԺԤ������ϸ����:" + e.getMessage());
		}
	}
}
