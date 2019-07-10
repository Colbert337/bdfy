package com.ylzinfo.model.his.mz.ybjk.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ybjk.dao.YbjkDao;
import com.ylzinfo.service.his.mz.ws.webservice.CallHisService;

public class GetSettleInfoService {

	private Logger log=null;
	
	public GetSettleInfoService() {
		log=Logger.getLogger(this.getClass().getName());
	}
	
	public String GetSettleInfo(Map para) throws SSTException {
		Connection conn = null;
		YbjkDao dao = new YbjkDao();
		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			conn = DBUtil.getConnection();
			log.info("获取医保结算数据开始...");
			long startTime = System.currentTimeMillis();
			log.info("获取医保结算数据开始时间：" + startTime);
			if(((String)para.get("ywlx00")).equals("1")){ //挂号
				conn.setAutoCommit(false);
				para.put("mzh000",(String)para.get("ghlsh0") + "0");
				para.put("ybdjh0","Z" + new SimpleDateFormat("yyMMdd").format(new Date()) + dao.getYbdjh(conn));
				para.put("jbr00", "ZZJ" + ((String)para.get("zdbh00")).substring(10,12));
				long times = new Date().getTime();
				dao.insertKc21(conn, para, new java.sql.Date(times));
				dao.insertKc22(conn, para, new java.sql.Date(times));
				
				hm.put("mzh000", (String)para.get("mzh000"));
				hm.put("username", (String)para.get("jbr00"));
				hm.put("billno", (String)para.get("ybdjh0"));
				conn.commit();
			}if(((String)para.get("ywlx00")).equals("2")){
				
			}
			
			long endTime = System.currentTimeMillis();
			log.info("获取医保结算数据结束...");
			log.info("获取医保结算数据耗时:"
					+ DateUtil.consumeTime2String(startTime, endTime));
			return SoapUtil.getSoapResponse(hm);
		} catch (Exception e) {
			log.info("获取医保结算数据出错:" + e.getMessage());
			throw new SSTException("获取医保结算数据出错:" + e.getMessage());
		}finally{
			log.info("关闭本地数据库连接");
			DBUtil.closeConnection(conn);
		}
	}
}
