package com.ylzinfo.model.his.mz.xxcx.server.wjgs;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;

public class WjgsService {
	
	public Logger logger = null;
	
	public WjgsService(){
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String queryPrice(Map param) throws SSTException{
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[物价公示查询]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "开始 *********");
			long startTime = System.currentTimeMillis();
			
			
			
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
