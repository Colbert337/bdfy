package com.ylzinfo.model.his.mz.mzyy.bs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class QueryGhfService {
	
	private Logger log=null;
	
	public QueryGhfService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String QueryGhf(Map para) throws SSTException {
//		Vector vt = new Vector<HashMap<String, String>>();
		try {
			log.info("查询挂号费开始...");
			long startTime = System.currentTimeMillis();
			log.info("查询挂号费开始时间：" + startTime);
			
			if(para.get("xbie00").equals("1")){
				para.put("xbie00", "M");
			}else{
				para.put("xbie00", "F");
			}
			
			CallHisService ws = new CallHisService("009");
			Map map = ws.callWs(para);
			
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "获取门诊号失败";
				}
				throw new SSTException(errmsg);
			}
//			vt = (Vector<HashMap<String, String>>)map.get("vt");
//			if(vt == null || vt.size() <= 0) {
//				log.error("获取门诊号失败");
//				throw new SSTException("获取门诊号失败");
//			}
			long endTime = System.currentTimeMillis();
			log.info("获取门诊号结束...");
			log.info("获取门诊号耗时:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse((HashMap<String, String>)map);
		}catch (Exception e) {
			log.info("获取门诊号数据出错:" + e.getMessage());
			throw new SSTException("获取门诊号数据出错:" + e.getMessage());
		}
	}
}
	