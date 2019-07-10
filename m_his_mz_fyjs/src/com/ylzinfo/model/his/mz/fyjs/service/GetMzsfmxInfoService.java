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
 * @description: 获取门诊收费结算明细
 * @author wujf
 * @date: 2015-8-16 上午08:25:51
 */
public class GetMzsfmxInfoService {
	
	private Logger logger = null;

	
	public GetMzsfmxInfoService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainGetMzsfmxInfo(Map params) throws SSTException{
		Vector vt = new Vector<HashMap<String, String>>();
		String funcTitle = "[获取收费明细]:";
		String className = this.getClass().getName();
		try {
			logger.info("*********" + funcTitle + "开始 *********");
			long startTime = System.currentTimeMillis();
			
			CallHisService ws = new CallHisService("012");
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
