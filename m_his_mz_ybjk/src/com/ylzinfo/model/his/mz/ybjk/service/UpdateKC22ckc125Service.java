package com.ylzinfo.model.his.mz.ybjk.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ybjk.dao.YbjkDao;

public class UpdateKC22ckc125Service {
	
	private Logger log=null;
	
	public UpdateKC22ckc125Service() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String UpdateKC22ckc125(Map para) throws SSTException {
		Connection conn = null;
		YbjkDao dao = new YbjkDao();
		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			conn = DBUtil.getConnection();
			log.info("更新KC22作废标志开始...");
			long startTime = System.currentTimeMillis();
			log.info("获取医保结算数据开始时间：" + startTime);
			
			dao.updateKc22(conn, para);
			
			long endTime = System.currentTimeMillis();
			log.info("更新KC22作废标志结束...");
			log.info("更新KC22作废标志耗时:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			hm.put("result", "1");
			return SoapUtil.getSoapResponse(hm);
		}catch (Exception e) {
			log.info("更新KC22作废标志出错:" + e.getMessage());
			throw new SSTException("更新KC22作废标志出错:" + e.getMessage());
		}finally{
			log.info("关闭本地数据库连接");
			DBUtil.closeConnection(conn);
		}
	}
}
