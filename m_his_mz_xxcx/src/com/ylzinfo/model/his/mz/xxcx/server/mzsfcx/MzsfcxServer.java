package com.ylzinfo.model.his.mz.xxcx.server.mzsfcx;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class MzsfcxServer {
	
	private Logger logger;
	
	public MzsfcxServer() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String getSfjlByCardno(Map param) throws SSTException {
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[收费记录查询]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "开始 *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("");
			Map map = ws.callWs(param);
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("未查询到收费记录");
				throw new SSTException("未查询到收费记录");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle + "结束时间：" + endTime);
			logger.info("************" + funcTitle + "结束************");
			logger.info(funcTitle + "额耗时:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse(vt);
		} catch (SSTException e) {
			logger.error(funcTitle + "出错：" + e.getMessage());
			throw new SSTException(funcTitle + "出错："  + e.getMessage(), e);
		}
	}
	
	public String getSfmxByDjlsh(Map param) throws SSTException {
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[收费明细查询]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "开始 *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("");
			Map map = ws.callWs(param);
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("未查询到收费明细");
				throw new SSTException("未查询到收费明细");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle + "结束时间：" + endTime);
			logger.info("************" + funcTitle + "结束************");
			logger.info(funcTitle + "额耗时:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse(vt);
		} catch (SSTException e) {
			logger.error(funcTitle + "出错：" + e.getMessage());
			throw new SSTException(funcTitle + "出错："  + e.getMessage(), e);
		}
	}
	
}
