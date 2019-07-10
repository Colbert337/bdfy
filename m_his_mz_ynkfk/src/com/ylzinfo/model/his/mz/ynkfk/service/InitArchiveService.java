/**
 * 
 */
package com.ylzinfo.model.his.mz.ynkfk.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.start.sst.exception.SSTException;
import com.start.sst.util.DBUtil;
import com.start.sst.util.DateUtil;
import com.start.sst.util.SoapUtil;
import com.ylzinfo.model.his.mz.ynkfk.dao.ZzfkDao;

/**
 * @description: 建档初始化
 * @author wujf
 * @date: 2015-9-9 上午09:46:28
 */
public class InitArchiveService {
	
	private Logger logger = null;
	
	private String errmsg="";
	
	public InitArchiveService() {
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public String mainInitArchive(Map params) throws SSTException{
		Vector<HashMap<String, String>> vt = null;
		Connection ylzConn = null;
		ZzfkDao dao = new ZzfkDao();
		try {
			logger.info("***************院内卡建档初始化开始***************");
			
			//获得连接
			ylzConn = DBUtil.getConnection();
			//开启事务
			ylzConn.setAutoCommit(false);
			
			String jylsh0 = dao.getJylsh(ylzConn);
			logger.info("获取交易流水号..."+jylsh0);
			params.put("jylsh0", jylsh0);
			
			String cshsj0 = DateUtil.getSystemDateTime();// 获取系统时间yyyy-MM-dd HH:mm:ss
			params.put("cshsj0", cshsj0);
			
			params.put("ycmklx", "2");
			params.put("czqx00", "1"); //充值去向 1 院内 2 健康通
		
			logger.info("数据写入预交金账户充值明细表...");
			dao.insertSstYjjZhczmx(ylzConn, params);
			
			String dblsh0 = dao.getDblsh0(ylzConn);
			logger.info("获取单笔流水号..."+dblsh0);
			params.put("dblsh0", dblsh0);
			
			logger.info("数据写入预交金单笔充值明细表...");
			params.put("zzs000", "1");
			params.put("dzjec0", params.get("czje00"));
			params.put("cpxrsj",cshsj0);
			dao.insertSstYjjDbczmx(ylzConn, params);
			
			// 进行建档初始化操作，并获得建档流水号
			String jdlsh0 = dao.getJDLSH(ylzConn);//建档流水号
			logger.info("获取建档流水号..."+jdlsh0);
			
			logger.info("插入建档初始化数据...");
			params.put("jdlsh0",jdlsh0); //建档流水号
			params.put("zcycje", params.get("czje00"));
			params.put("gbfei0", "0");
			params.put("jdkssj", cshsj0); //初始化时间
			dao.doJdcsh(ylzConn, params);
			
			//提交事务
			ylzConn.commit();
			
			logger.info(" 返回建档流水号信息（封装数据）...");
			vt = new Vector<HashMap<String, String>>();
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("jdlsh0", jdlsh0);
			map.put("jylsh0", jylsh0);
			map.put("dblsh0", dblsh0);
			vt.addElement(map);
			logger.info("***************[院内卡建档初始化结束***************");
			return SoapUtil.getSoapResponse(vt);
		} catch (Exception e) {
			DBUtil.rollback(ylzConn);
			errmsg = "发卡出错, 若有疑问请联系管理人员!";
			logger.error(errmsg+e.getMessage()+"本地事务回滚");
			throw new SSTException(errmsg+e.getMessage());
		} finally {
			logger.info("关闭本地数据库连接");
			DBUtil.closeConnection(ylzConn);
		}
	}
}
