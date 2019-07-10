package com.ylzinfo.model.his.mz.fyjs.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @description: 获取门诊收费结算信息
 * @author wujf
 * @date: 2015-8-16 上午08:38:54
 */
public class GetMzsfInfoService {
	
	private Logger logger = null;
	
	public GetMzsfInfoService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainGetMzsfInfo(Map params) throws SSTException{
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[获取挂号信息]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + className + funcTitle + "开始 *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("011");
			Map map = ws.callWs(params);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "未知错误";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			if(vt == null || vt.size() <= 0) {
				logger.error("未查询到挂号信息");
				throw new SSTException("未查询到挂号信息");
			}
			
			long endTime = System.currentTimeMillis();
			logger.info(funcTitle + "结束时间：" + endTime);
			logger.info("************" + funcTitle + "结束************");
			logger.info(funcTitle + "额耗时:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception e) {
			logger.error(funcTitle + "出错：" + e.getMessage());
			throw new SSTException(funcTitle + "出错："  + e.getMessage(), e);
		}
	}
	
}
