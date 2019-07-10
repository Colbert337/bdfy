package com.ylzinfo.model.his.mz.fyjs.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.fyjs.dao.FyjsDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

/**
 * @description: 门诊结算
 * @author wujf
 * @date: 2015-8-16 上午08:25:51
 */
public class DoMzJsService {
	
	private Logger logger = null;
	
	public DoMzJsService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainDoMzJs(Map params) throws SSTException{
		Connection conn = null;
		String errMsg = "门诊结算";
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+errMsg+"开始 ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg+"开始时间：" + startTime);
			
			conn = DBUtil.getConnection();
			
			params.put("jylsh0", DateUtil.getNowTime());
			
			params.put("jysj00", DateUtil.getSystemDateTime());
			
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
			Vector fymx = (Vector<HashMap<String, String>>)map.get("vt");
			if(fymx == null || fymx.size() <= 0) {
				logger.error("未查询到收费明细");
				throw new SSTException("未查询到收费明细");
			}
			
			params.put("fymx", fymx);
			
			ws = new CallHisService("013");
			map = ws.callWs(params);
			result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "未知错误";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			
			FyjsDao dao = new FyjsDao();
			dao.insertSstLogSfjs(conn, params);
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg+"结束时间：" + endTime);
			logger.info("************"+errMsg+"结束************");
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception pe) {
			logger.error(errMsg+"出错,原因："+ pe.getMessage());
			throw new SSTException(errMsg+"出错,原因："+ pe.getMessage(), pe);
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SSTException e) {
				e.printStackTrace();
			}
		}
	}
}
