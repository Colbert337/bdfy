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
			logger.info("***************住院预交金明细信息开始***************");
			long startTime = System.currentTimeMillis();
			logger.info("开始时间：" + startTime);
			
			long endTime = System.currentTimeMillis();
			logger.info("结束时间：" + endTime);
			logger.info("***************住院预交金明细信息结束***************");
			HashMap evtBody = new HashMap();
			evtBody.put("retrieve", vt);
			return evtBody;
		}catch (Exception e) {
			logger.error("住院预交金明细出错:" + e.getMessage());
			throw new SSTException("住院预交金明细出错:" + e.getMessage());
		}
	}
}
