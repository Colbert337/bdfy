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
 * @description: �������
 * @author wujf
 * @date: 2015-8-16 ����08:25:51
 */
public class DoMzJsService {
	
	private Logger logger = null;
	
	public DoMzJsService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainDoMzJs(Map params) throws SSTException{
		Connection conn = null;
		String errMsg = "�������";
		Vector vt = new Vector<HashMap<String, String>>();
		try {
			logger.info("************"+errMsg+"��ʼ ************");
			long startTime = System.currentTimeMillis();
			logger.info(errMsg+"��ʼʱ�䣺" + startTime);
			
			conn = DBUtil.getConnection();
			
			params.put("jylsh0", DateUtil.getNowTime());
			
			params.put("jysj00", DateUtil.getSystemDateTime());
			
			CallHisService ws = new CallHisService("012");
			Map map = ws.callWs(params);
			String result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "δ֪����";
				}
				throw new SSTException(errmsg);
			}
			Vector fymx = (Vector<HashMap<String, String>>)map.get("vt");
			if(fymx == null || fymx.size() <= 0) {
				logger.error("δ��ѯ���շ���ϸ");
				throw new SSTException("δ��ѯ���շ���ϸ");
			}
			
			params.put("fymx", fymx);
			
			ws = new CallHisService("013");
			map = ws.callWs(params);
			result = (String)map.get("result");
			if(!result.equals("1")) {
				String errmsg = (String)map.get("errmsg");
				if(errmsg == null || errmsg.equals("")) {
					errmsg = "δ֪����";
				}
				throw new SSTException(errmsg);
			}
			vt = (Vector<HashMap<String, String>>)map.get("vt");
			
			FyjsDao dao = new FyjsDao();
			dao.insertSstLogSfjs(conn, params);
			
			long endTime = System.currentTimeMillis();
			logger.info(errMsg+"����ʱ�䣺" + endTime);
			logger.info("************"+errMsg+"����************");
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception pe) {
			logger.error(errMsg+"����,ԭ��"+ pe.getMessage());
			throw new SSTException(errMsg+"����,ԭ��"+ pe.getMessage(), pe);
		} finally {
			try {
				DBUtil.closeConnection(conn);
			} catch (SSTException e) {
				e.printStackTrace();
			}
		}
	}
}
