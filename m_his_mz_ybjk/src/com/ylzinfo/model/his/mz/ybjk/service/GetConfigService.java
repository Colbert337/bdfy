package com.ylzinfo.model.his.mz.ybjk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ybjk.dao.YbjkDao;

public class GetConfigService {

	private Logger log=null;
	
	public GetConfigService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String GetConfig(Map para) throws SSTException {
		Vector vt = new Vector<HashMap<String, String>>();
		Connection conn = null;
		try {
			log.info("获取医保配置信息开始...");
			long startTime = System.currentTimeMillis();
			
			conn = DBUtil.getConnection();
			
			YbjkDao dao = new YbjkDao();
			vt = dao.queryYbConfig(conn, para);
			if(vt == null || vt.size() <= 0) {
				log.error("找不到医保配置信息");
				throw new SSTException("找不到医保配置信息");
			}
			
			long endTime = System.currentTimeMillis();
			log.info("获取医保配置信息结束...");
			log.info("获取医保配置信息耗时:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception e) {
			log.info("获取医保配置信息出错:" + e.getMessage());
			throw new SSTException("获取医保配置信息出错:" + e.getMessage());
		}finally{
			log.info("关闭本地数据库连接");
			DBUtil.closeConnection(conn);
		}
	}
}
