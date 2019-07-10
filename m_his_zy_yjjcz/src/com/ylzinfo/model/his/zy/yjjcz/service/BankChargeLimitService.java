/**
 * 
 */
package com.ylzinfo.model.his.zy.yjjcz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.zy.yjjcz.dao.YjjczDao;


/**
 * @description:  银行卡充值金额累计业务层
 * @author fanggx
 * @date: 2016-4-27 上午09:30:50
 */
public class BankChargeLimitService {
	public Logger logger = null;
	
	public BankChargeLimitService(){
		
		logger = Logger.getLogger(this.getClass().getName());
	}
	public String mainBankChargeLimit(Map para) throws SSTException {
		Map ynje= null;
		Map yktje= null;
		YjjczDao dao = new YjjczDao();
		Connection conn = null;
		try {
			conn=DBUtil.getConnection();
			HashMap result = new HashMap();
			String tjrq00 = DateUtil.getDqrq2();
			para.put("tjrq00", "%"+tjrq00+"%");
			ynje=dao.queryBankYnCzje(conn, para);
			result.put("ynje00", ynje.get("ynje00"));
			Vector vt = new Vector();
			vt.add(result);
			return SoapUtil.getSoapResponse(vt);
		} catch (SSTException e) {
			logger.error("统计银行卡充值累积金额失败:"+e.getMessage());
			throw new SSTException("统计银行卡充值累积金额失败:" + e.getMessage());
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
